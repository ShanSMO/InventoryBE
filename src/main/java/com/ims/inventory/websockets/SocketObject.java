package com.ims.inventory.websockets;

import com.ims.inventory.enums.SocketResponseType;

import java.util.List;

public class SocketObject {
    private String message;
    private String status;
    private Object object;
    private List<?> itemList;
    private SocketResponseType type;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<?> getItemList() {
        return itemList;
    }

    public void setItemList(List<?> itemList) {
        this.itemList = itemList;
    }

    public SocketResponseType getType() {
        return type;
    }

    public void setType(SocketResponseType type) {
        this.type = type;
    }
}
