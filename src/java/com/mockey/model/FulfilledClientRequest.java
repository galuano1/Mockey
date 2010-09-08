/*
 * This file is part of Mockey, a tool for testing application 
 * interactions over HTTP, with a focus on testing web services, 
 * specifically web applications that consume XML, JSON, and HTML.
 *  
 * Copyright (C) 2009-2010  Authors:
 * 
 * chad.lafontaine (chad.lafontaine AT gmail DOT com)
 * neil.cronin (neil AT rackle DOT com) 
 * lorin.kobashigawa (lkb AT kgawa DOT com)
 * rob.meyer (rob AT bigdis DOT com)
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 */
package com.mockey.model;

import java.util.Date;

/**
 * Represents the snap-shot of what-just-happened between the Client and Server,
 * response may be a mock scenario/service definition or a real response from
 * the server.
 * 
 * @author chad.lafontaine
 * 
 */
public class FulfilledClientRequest implements PersistableItem {

	private Long id;
	private Long serviceId;
	private String serviceName;
	private String clientRequestBody;
	private String clientRequestHeaders;
	private String clientRequestParameters;
	private String requestorIP;
	private String rawRequest;
	private String comment;
	private String originalUrlBeforeTwisting = null;
	private int serviceResponseType = -1;
	private ResponseFromService responseMessage;
	private Date time = new Date();
	/**
	 * Value of the response type, defining Static, Dynamic, or Proxy response. 
	 * @return non-negative value if set. 
	 * @see com.mockey.model.Service#getServiceResponseType()
	 */
	public int getServiceResponseType() {
		return serviceResponseType;
	}

	/**
	 * 
	 * @param serviceResponseType
	 * @see com.mockey.model.Service#getServiceResponseType()
	 */
	public void setServiceResponseType(int serviceResponseType) {
		this.serviceResponseType = serviceResponseType;
	}


	/**
	 * 
	 * @return optional comment about this request.
	 */
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the rawRequest
	 */
	public String getRawRequest() {
		return rawRequest;
	}

	/**
	 * @param rawRequest
	 *            the rawRequest to set
	 */
	public void setRawRequest(String rawRequest) {
		this.rawRequest = rawRequest;
	}

	public Date getTime() {
		return time;
	}

	public String getRequestorIP() {
		return requestorIP;
	}

	public void setRequestorIP(String ip) {
		String requestIp = ip;
		// on macs sometimes localhost resolves to the IPV6 format IP
		if (requestIp.startsWith("0:0:0:0")) {
			requestIp = "127.0.0.1";
		}
		this.requestorIP = requestIp;
	}

	public String getClientRequestBody() {
		return clientRequestBody;
	}

	public void setClientRequestBody(String clientRequestBody) {
		this.clientRequestBody = clientRequestBody;
	}

	public String getClientRequestHeaders() {
		return clientRequestHeaders;
	}

	public void setClientRequestHeaders(String clientRequestHeaders) {
		this.clientRequestHeaders = clientRequestHeaders;
	}

	public String getClientRequestParameters() {
		return clientRequestParameters;
	}

	public void setClientRequestParameters(String clientRequestParameters) {
		this.clientRequestParameters = clientRequestParameters;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public ResponseFromService getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(ResponseFromService response) {
		this.responseMessage = response;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setOriginalUrlBeforeTwisting(String originalUrlBeforeTwisting) {
		this.originalUrlBeforeTwisting = originalUrlBeforeTwisting;
	}

	public String getOriginalUrlBeforeTwisting() {
		return originalUrlBeforeTwisting;
	}

	
}
