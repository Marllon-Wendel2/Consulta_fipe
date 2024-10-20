package com.fife.fipe_app.service;

public interface IConvertData {
    <T> T getData(String json, Class<T> classe);
}
