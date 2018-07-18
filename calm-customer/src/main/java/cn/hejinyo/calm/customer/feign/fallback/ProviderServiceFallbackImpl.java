/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package cn.hejinyo.calm.customer.feign.fallback;

import cn.hejinyo.calm.customer.feign.ProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 用户服务的fallback
 */
@Service
public class ProviderServiceFallbackImpl implements ProviderService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String get() {
        logger.error("调用{}异常:{}", "get");
        return "调用{}异常";
    }

    @Override
    public String post() {
        logger.error("调用{}异常:{}", "get");
        return null;
    }

}
