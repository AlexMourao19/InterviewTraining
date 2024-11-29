package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class IncrementalGenerator implements IdGenerator {
    private AtomicInteger counter;

    public IncrementalGenerator() {
        this.counter = new AtomicInteger(0);
    }

    public String generateId() {
        return String.valueOf(this.counter.getAndIncrement());
    }
}
