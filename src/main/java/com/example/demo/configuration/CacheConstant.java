package com.example.demo.configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheConstant {

    public static final String SEPARATOR = ":";
    public static final String LOCK_KEY_PREFIX = "lock";
    public static final String SYSTEM_PARAMETER = "system-parameter";
    public static final String LOCK_ORDER_BOOK_TRANSACTION = "order-book-transaction";
    public static final String ORDER_BOOK_BUCKET_ORDER = "order-book-bucket-order";
    public static final String ORDER = "order";
    public static final String LATEST_PRICE = "latest-price";
    public static final String TRADE_ITEM = "trade-item";
    public static final String PREVENTED_MATCH = "prevented-match";
    public static final String INSTRUCTION = "instruction";
    public static final String ORDER_ID_SEQUENCER = "order-id-sequencer";
    public static final String TRADE_ID_SEQUENCER = "trade-id-sequencer";
    public static final String PREVENTED_MATCH_ID_SEQUENCER = "prevented-match-id-sequencer";
    public static final String TRADE_ITEM_ID_SEQUENCER = "trade-item-id-sequencer";
    public static final String INSTRUCTION_ID_SEQUENCER = "instruction-id-sequencer";
    public static final String ORDER_BOOK_BUCKET = "order-book-bucket";
    public static final String ORDER_BOOK_STREAM = "order-book-stream";
    public static final String ASSET = "asset";
    public static final String CLIENT = "client";

    public static String keys(String... name) {
        return String.join(SEPARATOR, name);
    }

    public static String keyByPattern(String... name) {
        return "*" + keys(name) + "*";
    }

}