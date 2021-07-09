package com.stockmarket.stock.repo.impl;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.expression.spel.ast.StringLiteral;
import org.springframework.stereotype.Component;

import com.datastax.oss.driver.api.querybuilder.Literal;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.Raw;
import com.datastax.oss.driver.api.querybuilder.select.Selector;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.stockmarket.stock.entity.StockDetails;
import com.stockmarket.stock.entity.StockEntity;
import com.stockmarket.stock.repo.intf.StockRepository;

@Component
public class StockRepoCommonImpl {

	@Autowired
	private StockRepository stockCommonOpRepo;

	@Autowired
	private CassandraOperations template;

	public StockDetails insertStock(StockDetails stockDetails) {
		long count = stockCommonOpRepo.count() + 1;
		for (StockEntity entity : stockDetails.getStocks()) {
			entity.setSequenceNo(++count);
		}
		List<StockEntity> entities = stockCommonOpRepo.saveAll(stockDetails.getStocks());
		stockDetails.setStocks(entities);
		checkForBackUp(count);
		return stockDetails;

	}

	private void checkForBackUp(long count) {
		if (count % 10000 == 0) {
			//QueryBuilder.selectFrom("stock").all().whereColumn("sequence_no").build(">", QueryBuilder.literal(count-1000)).;
			String slctQuery = "SELECT * FROM stock WHERE sequence_no > " + (count - 1000) + " and sequence_no <= "
					+ count + " ALLOW FILTERING";
			List<StockEntity> entities = template.select(slctQuery, StockEntity.class);
			try {
				Writer writer = Files
						.newBufferedWriter(Paths.get("/data/Stock_backup_" + Timestamp.from(Instant.now())));
				StatefulBeanToCsv<StockEntity> beanToCsv = new StatefulBeanToCsvBuilder<StockEntity>(writer).build();
				beanToCsv.write(entities);
				writer.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
