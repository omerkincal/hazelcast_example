package com.example.hazelcast_example.library.utils;

@FunctionalInterface
public interface Callable<T,R> {
    T call(R r);
}
