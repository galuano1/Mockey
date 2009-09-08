/*
 * Copyright 2002-2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mockey.ui;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mockey.model.Service;
import com.mockey.model.Url;
import com.mockey.storage.IMockeyStorage;
import com.mockey.storage.StorageRegistry;

/**
 * <code>MockHistoryPerServiceByIpServlet</code> manages the display of past
 * requests to a service by calling IP address.
 * 
 * @author Chad Lafontaine (chad.lafontaine)
 * @version $Id: MockHistoryRequestLogServlet.java,v 1.2 2005/05/03 22:28:54
 *          clafonta Exp $
 */
public class HistoryPerServiceByIpServlet extends HttpServlet {

    private static final long serialVersionUID = -2255013290808524662L;
    private static final Logger logger = Logger.getLogger(HistoryPerServiceByIpServlet.class);

    private static IMockeyStorage store = StorageRegistry.MockeyStorage;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String iprequest = req.getParameter("iprequest");
		Long serviceId = new Long(req.getParameter("serviceId"));		
		String action = req.getParameter("action");

		if(action!=null && "delete".equals(action)){
			logger.debug("deleting a single historical request.");
		    // Delete from history
		    Long fulfilledRequestId = new Long(req.getParameter("fulfilledRequestId"));		    
		    store.deleteLoggedFulfilledClientRequest(fulfilledRequestId);
		    // this is a void ajax call.
		    return;
		    
        } else if (action != null && "delete_all".equals(action)) {
            store.deleteAllLoggedFulfilledClientRequestForService(serviceId);

		    // don't allow reloads to re-delete.
            String contextRoot = req.getContextPath();
            resp.sendRedirect(Url.getContextAwarePath("/history/detail?serviceId=" + serviceId + "&iprequest=" + iprequest, contextRoot));
            return;
        }
		
		Service service = store.getServiceById(serviceId);
        req.setAttribute("requests", store.getFulfilledClientRequestsFromIPForService(iprequest, serviceId));
        req.setAttribute("mockservice", service);
        req.setAttribute("iprequest", iprequest);

        RequestDispatcher dispatch = req.getRequestDispatcher("/service_history_ip.jsp");
        dispatch.forward(req, resp);
    }
}