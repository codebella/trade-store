package com.sample.trade.command;

public interface Command<T, R> {
    R execute(T view);
}
