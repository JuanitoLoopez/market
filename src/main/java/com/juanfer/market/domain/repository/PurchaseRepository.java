package com.juanfer.market.domain.repository;

import com.juanfer.market.domain.Product;
import com.juanfer.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String clientId);
    Purchase save(Purchase purchase);


}
