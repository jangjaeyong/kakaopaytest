package com.test.kakaopaytest.test;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseTemplate {
    private boolean result;
    private Object data;
}
