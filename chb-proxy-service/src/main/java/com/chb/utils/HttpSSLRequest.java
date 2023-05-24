package com.chb.utils;

import java.io.OutputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultManagedHttpClientConnection;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.io.ChunkedOutputStream;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HttpSSLRequest {

	private final int TIMEOUT_TIME = 3000;
	private final String ENCODING = "UTF-8";
	private final String TLS_VERSION = "TLSv1.2";

	public String sendPostRequest(String url, String inputJosn) throws Exception {

		log.info("=========================sendPost=========================");
		log.info("inputJosn size:{}", inputJosn.length());

		StringEntity entity = new StringEntity(inputJosn, ENCODING);
		String returnStr = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse closeableHttpResponse = null;
		HttpPost httpPost = null;

		// SSL setting
		SSLContext sslContext = SSLContext.getInstance(TLS_VERSION);
		X509TrustManager tm = new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		sslContext.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory sslSocketFactory = new SSLSocketFactory(sslContext,
				SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		// HttpClients setting
		ConnectionConfig connConfig = ConnectionConfig.custom().setBufferSize(800).build();
		httpClient = HttpClients.custom().setSSLSocketFactory(sslSocketFactory).setDefaultConnectionConfig(connConfig)
				.build();
		httpPost = new HttpPost(url);
		httpPost.setEntity(entity);
		httpPost.addHeader("Content-Type", "application/json; charset=utf-8");

		log.info("=========================sendHttpRequest=========================");

		// setting timeout
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_TIME)
				.setConnectTimeout(TIMEOUT_TIME).build();
		httpPost.setConfig(requestConfig);

		log.info("Http發送上行電文:{}", inputJosn);
		closeableHttpResponse = httpClient.execute(httpPost);

		HttpEntity responeEntity = closeableHttpResponse.getEntity();
		returnStr = EntityUtils.toString(responeEntity, ENCODING);
		log.info("Http接收下行電文:{}", returnStr);

		return returnStr;
	}

	public static class CustomManagedHttpClientConnection extends DefaultManagedHttpClientConnection {

		private final int chunkSize;

		public CustomManagedHttpClientConnection(final String id, final int buffersize, final int chunkSize) {
			super(id, buffersize);
			this.chunkSize = chunkSize;
		}

		@Override
		protected OutputStream createOutputStream(long len, SessionOutputBuffer outbuffer) {
			if (len == ContentLengthStrategy.CHUNKED) {
				return new ChunkedOutputStream(chunkSize, outbuffer);
			}
			return super.createOutputStream(len, outbuffer);
		}
	}

	public static class CustomManagedHttpClientConnectionFactory extends ManagedHttpClientConnectionFactory {

		private static final AtomicLong COUNTER = new AtomicLong();
		private final int chunkSize;

		public CustomManagedHttpClientConnectionFactory(int chunkSize) {
			this.chunkSize = chunkSize;
		}

		@Override
		public ManagedHttpClientConnection create(HttpRoute route, ConnectionConfig config) {
			final String id = "http-outgoing-" + Long.toString(COUNTER.getAndIncrement());
			return new CustomManagedHttpClientConnection(id, config.getBufferSize(), chunkSize);
		}
	}

	public String httpPostSend(String url, String inputJosn) throws Exception {

		log.info("=========================sendPost=========================");
		log.info("inputJosn size:{}", inputJosn.length());

		String returnStr = null;

		// http SSL X509
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
				.build();
		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
		ConnectionConfig connConfig = ConnectionConfig.custom().setBufferSize(800).build();
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory)
				.setDefaultConnectionConfig(connConfig).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		// Setting timeout
		requestFactory.setConnectTimeout(TIMEOUT_TIME);
		requestFactory.setReadTimeout(TIMEOUT_TIME);

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		org.springframework.http.HttpEntity<String> request = new org.springframework.http.HttpEntity<>(inputJosn,
				headers);

		log.info("Http發送上行電文:{}", inputJosn);
		
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		returnStr = response.getBody();
		
		log.info("Http接收下行電文:{}", returnStr);

		return returnStr;

	}
}
