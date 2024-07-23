package com.inditex.technicaltest.pricelist.infrastructure.persistence;

import com.inditex.technicaltest.pricelist.domain.PriceList;
import com.inditex.technicaltest.pricelist.domain.PriceListId;
import com.inditex.technicaltest.pricelist.domain.PriceListRepository;
import com.inditex.technicaltest.shared.infrastructure.persistence.JakartaRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PriceListJakartaRepository extends JakartaRepository<PriceList, Integer, PriceListJakartaEntity> implements PriceListRepository {

    @Autowired
    public PriceListJakartaRepository(EntityManager entityManager) {
        super(entityManager, PriceListJakartaEntity.class);
    }

    @Override
    @Transactional
    public void save(PriceList priceList) {
        repository.save(new PriceListJakartaEntity().fromDomain(priceList));
    }

    @Override
    public PriceList find(PriceListId id) {
        return repository.getReferenceById(id.value()).toDomain();
    }

}
