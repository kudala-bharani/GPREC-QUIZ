package admin_user.service;

import org.springframework.stereotype.Service;

import admin_user.model.Response;

@Service
public interface ResponseService {
	Response saveResponse(Response response);
}
