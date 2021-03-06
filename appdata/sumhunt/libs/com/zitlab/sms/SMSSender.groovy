package com.zitlab.sms;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.utils.URIBuilder;

import com.zitlab.palmyra.api2db.script.ScriptHelper
import com.zitlab.palmyra.api2db.exception.ScriptValidationException;


import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j 

@Slf4j
@CompileStatic
public class SMSSender {	
	private static CloseableHttpClient httpclient = getHttpClient();
	private ScriptHelper helper = null;

	public SMSSender(ScriptHelper helper){
		this.helper = helper;
	}

	public String sendSMS(String templateId, String message, String phone) throws IOException {
		
		if(1 == 1){
			return "not sent by SMSSender";
		}

		int timeout = getTimetout() * 30 * 1000;
		log.error(" setting timeout " + timeout);
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeout)
				.setConnectTimeout(timeout).setSocketTimeout(timeout).build();
		HttpEntity entity = null;
		CloseableHttpResponse response = null;
		try {			
			URIBuilder builder = new URIBuilder(getTargetURL());
			builder.setParameter("userid", getUsername())
				.setParameter("password", getPassword())
				.setParameter("sendername", getSenderName())
				.setParameter("sendernumber", getSenderNumber())
				.setParameter("category", getCategory())
				.setParameter("peid", getPEid())
				.setParameter("templateid", templateId)
				.setParameter("mobileno", phone)
				.setParameter("message", message);

			URI target = builder.build();
			log.error("Target " + target);
			HttpGet httpGet = new HttpGet(builder.build());
			httpGet.addHeader("content-type", "application/x-ww-form-urlencoded");
			httpGet.setConfig(requestConfig);
			
			response = httpclient.execute(httpGet);
			entity = processHttpCode(response);
			return processResponseBody(entity);			
		} catch(URISyntaxException ue){
			throw new IOException("Invalid URL", ue);
		}
		catch (ConnectException ce) {
			throw new IOException("Server Connection refused !! Please check server reachability", ce);
		} catch (ClientProtocolException e1) {
			throw new IOException("Invalid protocol", e1);
		}

		return "not processed";
	}

	private HttpEntity processHttpCode(HttpResponse response) throws IOException {
		StatusLine status = response.getStatusLine();
		int code = status.getStatusCode();
		HttpEntity entity = response.getEntity();
		if (code == HttpStatus.SC_OK)
			return entity;

		String message = EntityUtils.toString(entity);
		if (null == message || message.length() == 0) {
			message = status.getReasonPhrase();
		}
		log.error("Error while sending SMS, Gateway response :{}", message )
		throw new RuntimeException("SMS Gateway Response Code:" + code);
	}

	private String processResponseBody(HttpEntity entity){
		String response = EntityUtils.toString(entity);
		log.debug("sms sent response :'{}'", response);
		String reference = getLast(response, 24);
		EntityUtils.consume(entity);
		return reference;
	}

	

	private static String getLast(String text, int expectedLength) {
		if(text.startsWith("Failed"))
			return text;

		int i = text.length();
		if(i <= expectedLength)
			return text;
		return text.substring(i-expectedLength);
	}

	private String getTargetURL() {
		String url = helper.getAppConfig("sms.http.url");		
		if(null != url)
		 	return url;
		throw new ScriptValidationException(500, 3001,"sms.http.url must be configured in application.properties");
	}

	private String getUsername() {
		return helper.getAppConfig("sms.http.userid");
	}

	private String getPassword() {
		return helper.getAppConfig("sms.http.password");
	}

	private String getSenderName() {
		return helper.getAppConfig("sms.http.sendername");
	}

	private String getSenderNumber() {
		return helper.getAppConfig("sms.http.sendernumber");
	}

	private String getPEid(){
		return helper.getAppConfig("sms.http.peid");
	}

	private String getCategory() {
		return helper.getAppConfig("sms.http.category");
	}

	private int getTimetout() {
		int result = 2;
		String timeout = helper.getAppConfig("sms.http.timeout");

		if (null != timeout) {
			try {
				result =  Integer.parseInt(timeout);
				if(result > 30)
					result = 30;
			} catch (Throwable e) {

			}
		}
		return result;
	}

	public void close() {
		try {
			httpclient.close();
		} catch (Throwable e) {
		}
	}

	private static CloseableHttpClient getHttpClient() {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(128);
		cm.setDefaultMaxPerRoute(128);

		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
		return httpClient;
	}
}
