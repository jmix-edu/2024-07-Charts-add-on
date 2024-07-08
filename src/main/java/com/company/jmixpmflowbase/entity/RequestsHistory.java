package com.company.jmixpmflowbase.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.JmixEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@JmixEntity
public class RequestsHistory {
    @JmixGeneratedValue
    @JmixId
    private UUID id;

    private LocalDateTime timestamp;

    private Integer requestsNumber;

    public Integer getRequestsNumber() {
        return requestsNumber;
    }

    public void setRequestsNumber(Integer requestsNumber) {
        this.requestsNumber = requestsNumber;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}