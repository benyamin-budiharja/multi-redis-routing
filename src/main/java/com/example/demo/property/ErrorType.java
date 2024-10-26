package com.example.demo.property;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorType {

    /* GENERICS */
    APPROVED_OK("EC0000000", "APPROVED/OK"),
    REST_CONTROLLER_ERROR("EC0000996", "Invalid Request"),
    REST_CONTROLLER_CUSTOM_MESSAGE("EC0000996", "Required request parameter '%s' for method parameter type %s is not present"),
    INVALID_HEADER_PARAM("EC0000997", "Permintaan tidak dapat dipenuhi. Silahkan coba lagi"),
    INVALID_BODY_PARAM("EC0000999", "Permintaan tidak sesuai. Silahkan coba lagi"),
    ERROR_CONNECTIONS("EC0009888", "Koneksi bermasalah. Silakan coba lagi nanti"),
    INTERNAL_SERVER_ERROR("EC0009999", "Ada yang salah, silakan coba lagi"),
    /* GENERICS */

    /* COIN MATCHING ENGINE */
    COIN_MATCHING_ENGINE_MUTEX_ERROR("EC0008600", "this transaction still locked, please try it again"),
    COIN_MATCHING_ENGINE_SYSTEM_PARAMETER_NOT_FOUND("EC0008601", "system parameter not found"),
    COIN_MATCHING_ENGINE_ORDER_ID_NOT_ASSIGN_YET("EC0008602", "order id not assigned yet"),
    COIN_MATCHING_ENGINE_ORDER_ID_ALREADY_ASSIGNED("EC0008603", "order id already assigned"),
    COIN_MATCHING_ENGINE_TRADE_ID_ALREADY_ASSIGNED("EC0008604", "trade id already assigned"),
    COIN_MATCHING_ENGINE_TRADE_ID_NOT_ASSIGN_YET("EC0008605", "trade id not assigned yet"),
    COIN_MATCHING_ENGINE_TRADE_ITEM_ID_NOT_ASSIGN_YET("EC0008605", "trade item id not assigned yet"),
    COIN_MATCHING_ENGINE_ASSET_NOT_FOUND("EC0008606", "asset not found"),
    COIN_MATCHING_ENGINE_INVALID_ORDER_TYPE("EC0008607", "invalid order type"),
    COIN_MATCHING_ENGINE_ORDER_NOT_FOUND("EC0008608", "order not found"),
    COIN_MATCHING_ENGINE_ORDER_ALREADY_CLOSED("EC0008609", "order already closed"),
    COIN_MATCHING_ENGINE_ORDER_ALREADY_EXIST("EC0008610", "order already exist"),
    COIN_MATCHING_ENGINE_DUPLICATE_CLIENT_ORDER_ID("EC0008611", "duplicate client order id"),
    COIN_MATCHING_ENGINE_MAPPER_PROCESSING_ERROR("EC0008612", INTERNAL_SERVER_ERROR.getDescription()),
    COIN_MATCHING_ENGINE_INVALID_BUCKET_ORDER("EC0008613", "invalid bucket orders"),
    COIN_MATCHING_ENGINE_INVALID_BUCKET_ORDER_GROUP("EC0008614", "invalid bucket orders group"),
    COIN_MATCHING_ENGINE_FAILED_GET_ORDER_BOOK("EC0008615", "Failed to get Order Book"),
    COIN_MATCHING_ENGINE_UNSUPPORTED_INSTRUCTION("EC0008616", "unsupported instruction"),
    COIN_MATCHING_ENGINE_INVALID_INSTRUCTION_DATA("EC000817", "invalid instruction data"),
    COIN_MATCHING_ENGINE_INSTRUCTION_ID_ALREADY_ASSIGNED("EC000818", "instruction id already assigned"),
    COIN_MATCHING_ENGINE_INVALID_ASSET_STATUS("EC0008619", "invalid asset status"),
    COIN_MATCHING_ENGINE_ASSET_STATUS_IS_HALTED("EC0008620", "asset status is halted"),
    COIN_MATCHING_ENGINE_BUCKET_NOT_FOUND("EC0008621", "order book bucket not found"),
    COIN_MATCHING_ENGINE_ASSET_STATUS_IS_PAUSED("EC0008622", "asset status is paused"),
    COIN_MATCHING_ENGINE_ORDER_AND_BUCKET_NOT_SYNCED("EC0008622", "order and bucket not synced"),
    COIN_MATCHING_ENGINE_CANCELLATION_REJECTED("EC0008623", "cancellation rejected"),
    COIN_MATCHING_ENGINE_PREVENTED_MATCH_ID_ALREADY_ASSIGNED("EC0008624", "prevented match id already assigned"),
    COIN_MATCHING_ENGINE_PREVENTED_MATCH_ID_NOT_ASSIGN_YET("EC0008625", "prevented match id not assigned yet"),
    COIN_MATCHING_ENGINE_INVALID_ORDER_SYMBOL_GROUP("EC0008614", "invalid orders symbol group"),
    /* COIN MATCHING ENGINE */;

    @Getter
    private final String value;

    @Getter
    private final String description;

}