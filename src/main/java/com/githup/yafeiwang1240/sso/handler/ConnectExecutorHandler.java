package com.githup.yafeiwang1240.sso.handler;

public interface ConnectExecutorHandler<M, P> {
    void invoke(M method, P... params);
}
