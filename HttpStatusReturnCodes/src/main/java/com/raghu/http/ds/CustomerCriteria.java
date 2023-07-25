package com.raghu.http.ds;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerCriteria {
    private String firstNameLike;

    @SuppressWarnings("unused")
    public CustomerCriteria() {
    }
}
