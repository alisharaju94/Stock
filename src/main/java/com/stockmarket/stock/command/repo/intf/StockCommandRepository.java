package com.stockmarket.stock.command.repo.intf;

import org.springframework.data.repository.CrudRepository;

import com.stockmarket.stock.command.entity.StockEntity;
import com.stockmarket.stock.command.entity.StockPrimaryKey;

public interface StockCommandRepository extends CrudRepository<StockEntity, StockPrimaryKey> {

}
