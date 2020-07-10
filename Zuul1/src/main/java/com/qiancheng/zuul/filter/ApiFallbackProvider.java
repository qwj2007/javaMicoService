package com.qiancheng.zuul.filter;
import com.netflix.zuul.context.RequestContext;

import io.micrometer.core.instrument.util.JsonUtils;

import org.bouncycastle.asn1.ocsp.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSONObject;

/**
 * 作者：qiwj
 * 时间：2020/7/10
 * 在 Spring Cloud 中，Zuul 默认整合了 Hystrix，当后端服务异常时可以为 Zuul 添加回退功能，返回默认的数据给客户端。
 实现回退机制需要实现 FallbackProvider 接口，代码如下所示。
 */
@Component
public class ApiFallbackProvider implements FallbackProvider {
    private static final Logger log = LoggerFactory.getLogger(ApiFallbackProvider.class);
    /**
     * '*'表示对所有服务进行回退操作，如果只想对某个服务进行回退，
     *那么就返回需要回退的服务名称，这个名称一定要是注册到 Eureka 中的名称。
     * @return
     */
    @Override
    public String getRoute() {
        return "*";
    }
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }
            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }
            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }
            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                if (cause != null) {
                    log.error("", cause.getCause());
                }
                RequestContext ctx = RequestContext.getCurrentContext();
                JSONObject r = new JSONObject();
                r.put("state",  ctx.getResponseStatusCode());
                r.put("msg", "系统错误，请求失败");
                return new ByteArrayInputStream(r.toJSONString().getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mt);
                return headers;
            }
        };
    }
}
