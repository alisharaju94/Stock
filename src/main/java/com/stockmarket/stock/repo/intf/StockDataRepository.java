package com.stockmarket.stock.repo.intf;

import com.stockmarket.stock.entity.StockEntity;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

@EnableScan
public interface StockDataRepository extends DynamoDBCrudRepository<StockEntity, String> {
}