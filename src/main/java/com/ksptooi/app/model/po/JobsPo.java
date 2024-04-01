package com.ksptooi.app.model.po;


import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModelProperty;

@Getter
@Setter
public class JobsPo{


    @ApiModelProperty(value="主键")
    private Long id;

    private String queue;

    private String payload;

    private Integer attempts;

    private Long reservedAt;

    private Long availableAt;

    private Long createdAt;


}

