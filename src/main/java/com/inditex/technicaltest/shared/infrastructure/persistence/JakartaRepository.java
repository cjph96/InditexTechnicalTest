package com.inditex.technicaltest.shared.infrastructure.persistence;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public abstract class JakartaRepository<T, ID, E extends JakartaEntity<T>> {
    protected final EntityManager entityManager;
    protected final Class<E> entityClass;
    protected final SimpleJpaRepository<E, ID> repository;

    public JakartaRepository(
            EntityManager entityManager,
            Class<E> entityClass
    ) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
        this.repository = new SimpleJpaRepository<>(entityClass, entityManager);
    }
}
