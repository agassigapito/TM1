package my.com.tm.portal.leasing.logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import my.com.tm.portal.leasing.utils.MapperUtil;

public class LoggingRestTemplate implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingRestTemplate.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        return traceResponse(response);
    }

    private void traceRequest(HttpRequest request, byte[] body) {
        if (!LOGGER.isDebugEnabled()) {
            return;
        }
        request.getHeaders().add("Source", "Portal");
        request.getHeaders().add("RequestID", UUID.randomUUID().toString());
        LOGGER.debug("==========request begin=============");
        LOGGER.debug("URI: {}", request.getURI());
        LOGGER.debug("Method: {}", request.getMethod());
        LOGGER.debug("Headers: {}", request.getHeaders());
        LOGGER.debug("Request body: {}", MapperUtil.getJsonString(body));
        LOGGER.debug("==========request end===============");
    }

    private ClientHttpResponse traceResponse(ClientHttpResponse response) throws IOException {
        if (!LOGGER.isDebugEnabled()) {
            return response;
        }
        final ClientHttpResponse responseWrapper = new BufferingClientHttpResponseWrapper(response);
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(responseWrapper.getBody(), "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        LOGGER.debug("==========response begin=============");
        LOGGER.debug("Status code: {}", responseWrapper.getStatusCode());
        LOGGER.debug("Status text: {}", responseWrapper.getStatusText());
        LOGGER.debug("Headers: {}", responseWrapper.getHeaders());
        String responseBody = inputStringBuilder.toString();
        LOGGER.debug("Response body: {}", responseBody);
        LOGGER.debug("==========response end=============");
        return responseWrapper;
    }

}
