package com.stockmarket.stock.repo.intf;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.stockmarket.stock.entity.StockEntity;

public interface StockRepository extends CassandraRepository<StockEntity, Long> {

}
