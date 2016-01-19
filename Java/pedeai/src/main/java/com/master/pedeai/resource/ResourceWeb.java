package com.master.pedeai.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.core.resource.ResourceImpl;
import com.master.pedeai.session.Sessao;

public class ResourceWeb extends ResourceImpl {

	private Sessao sessao;
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	public ResourceWeb() {
	}

}
