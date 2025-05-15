package info.thanhnd.demo.service;


import info.thanhnd.demo.dto.request.AuthenticationRequest;
import info.thanhnd.demo.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
