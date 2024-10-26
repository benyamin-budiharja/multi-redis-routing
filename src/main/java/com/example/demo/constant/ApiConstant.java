package com.example.demo.constant;

public class ApiConstant {

    public final static String V1_ROOT = "/v1/coin-matching-engine";
    public final static String V1_ROOT_INTERNAL = "/v1/internal/coin-matching-engine";

    public final static String V1_INTERNAL_SYSTEM_PARAMETER = V1_ROOT_INTERNAL + "/system-parameters";

    public final static String V1_INTERNAL_ASSET = V1_ROOT_INTERNAL + "/assets";
    public final static String V1_INTERNAL_REPUBLISH = V1_ROOT_INTERNAL + "/republish";

    public static final String V1_INTERNAL_ORDER_HISTORIES = V1_ROOT_INTERNAL + "/order-histories";
    public static final String V1_INTERNAL_TRADE_HISTORIES = V1_ROOT_INTERNAL + "/trade-histories";
    public static final String V1_INTERNAL_ORDER = V1_ROOT_INTERNAL + "/orders";
    public final static String V1_INTERNAL_ORDER_BOOK = V1_ROOT_INTERNAL + "/order-book";
    public static final String V1_INTERNAL_ORDER_BOOK_STREAM = V1_INTERNAL_ORDER_BOOK + "/stream";
    public static final String V1_INTERNAL_CLIENT = V1_ROOT_INTERNAL + "/clients";
    public static final String V1_INTERNAL_CACHE = V1_ROOT_INTERNAL + "/cache";
}