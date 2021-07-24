package com.stockmarket.stock.repo.intf;

import com.stockmarket.stock.entity.StockEntity;
import com.stockmarket.stock.entity.StockPrimaryKey;

import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

@EnableScan
public interface StockRepository extends DynamoDBCrudRepository<StockEntity, StockPrimaryKey> {
}