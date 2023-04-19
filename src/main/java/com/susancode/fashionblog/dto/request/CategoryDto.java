package com.susancode.fashionblog.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class CategoryDto {
    private String title;

   public CategoryDto (String title) {
       this.title = title.toUpperCase();
   }

}
