package com.inditex.technicaltest.shared.infrastructure.persistence;

public interface JakartaEntity<T> {
    T toDomain();

    JakartaEntity<T> fromDomain(T domain);
}
