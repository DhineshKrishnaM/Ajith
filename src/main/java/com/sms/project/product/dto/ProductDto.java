package com.sms.project.product.dto;

import com.sms.project.common.Entity.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends CommonEntity {
    private String productName;
    private String description;
}
