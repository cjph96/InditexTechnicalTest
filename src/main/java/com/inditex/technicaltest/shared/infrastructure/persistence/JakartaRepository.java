package com.inditex.technicaltest.shared.infrastructure.persistence;

import com.inditex.technicaltest.pricelist.infrastructure.persistence.JakartaCriteriaConverter;
import com.inditex.technicaltest.shared.domain.criteria.Criteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;

public abstract class JakartaRepository<T, ID, E extends JakartaEntity<T>> {
    protected final EntityManager entityManager;
    protected final Class<E> entityClass;
    protected final SimpleJpaRepository<E, ID> repository;
    protected final JakartaCriteriaConverter<E> criteriaConverter;

    public JakartaRepository(
            EntityManager entityManager,
            Class<E> entityClass
    ) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
        this.repository = new SimpleJpaRepository<>(entityClass, entityManager);
        this.criteriaConverter = new JakartaCriteriaConverter<>(entityManager.getCriteriaBuilder());
    }

    protected List<T> byCriteria(Criteria criteria) {
        CriteriaQuery<E> criteriaQuery = criteriaConverter.convert(criteria, entityClass);

        return entityManager.createQuery(criteriaQuery).getResultList()
                .stream().map(JakartaEntity::toDomain).toList();
    }
}
