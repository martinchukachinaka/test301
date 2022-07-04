package com.cc.test301.common.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppResponse {

	private String message;

	private String status;

	private Throwable throwable;
}
