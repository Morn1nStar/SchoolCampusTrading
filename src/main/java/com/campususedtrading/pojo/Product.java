package com.campususedtrading.pojo;

import com.campususedtrading.anno.State;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Product {
    private int id;
    @NotEmpty
    private String name;
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private int categoryId;
    private int ownerId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
    @State
    private String state;// 发布状态 已上架|待审核|未通过|已售出
    private String deliveryMethod;
    private List<ProductImage> productImages;
}
