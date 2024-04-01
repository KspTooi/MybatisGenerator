package com.ksptooi.app.model.po;



public class JobsPo{


    //主键
    private Long id;

    private String queue;

    private String payload;

    private Integer attempts;

    private Long reservedAt;

    private Long availableAt;

    private Long createdAt;

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getQueue() {
        return this.queue;
    }
    public void setQueue(String queue) {
        this.queue = queue;
    }
    public String getPayload() {
        return this.payload;
    }
    public void setPayload(String payload) {
        this.payload = payload;
    }
    public Integer getAttempts() {
        return this.attempts;
    }
    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }
    public Long getReservedAt() {
        return this.reservedAt;
    }
    public void setReservedAt(Long reservedAt) {
        this.reservedAt = reservedAt;
    }
    public Long getAvailableAt() {
        return this.availableAt;
    }
    public void setAvailableAt(Long availableAt) {
        this.availableAt = availableAt;
    }
    public Long getCreatedAt() {
        return this.createdAt;
    }
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

}

