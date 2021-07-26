package com.stockmarket.stock.query.repo.intf;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.stockmarket.stock.query.entity.StockEntity;
import com.stockmarket.stock.query.entity.StockPrimaryKey;

public interface StockQueryRepository extends CassandraRepository<StockEntity, StockPrimaryKey> {

}
