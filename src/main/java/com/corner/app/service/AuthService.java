package com.corner.app.service;

import com.corner.app.dto.SignupRequest;
import com.corner.app.entity.Users;

public interface AuthService {
    Users createUser(SignupRequest signupRequest);
}
