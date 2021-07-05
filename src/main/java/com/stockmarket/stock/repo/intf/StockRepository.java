package com.stockmarket.stock.repo.intf;

import org.springframework.data.repository.CrudRepository;

import com.stockmarket.stock.entity.Stock;

public interface StockRepository  extends CrudRepository<Stock, Long>{

}
