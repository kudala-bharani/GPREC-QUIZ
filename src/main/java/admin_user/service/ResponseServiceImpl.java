package admin_user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin_user.model.Response;
import admin_user.repositories.ResponseRepository;

@Service
public class ResponseServiceImpl implements ResponseService {
	@Autowired
	private ResponseRepository responseRepository;
	
	public Response saveResponse(Response response) {
        return responseRepository.save(response);
    }
	
}
