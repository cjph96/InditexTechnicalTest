package com.inditex.technicaltest.shared.application;

public interface UseCase<I extends Request, O extends Response> {
    O handle(I request);
}
