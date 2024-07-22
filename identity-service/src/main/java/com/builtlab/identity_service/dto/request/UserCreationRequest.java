package com.builtlab.identity_service.dto.request;

import com.builtlab.identity_service.entity.Role;
import com.builtlab.identity_service.exception.ErrorCode;
import com.builtlab.identity_service.validator.DobConstraint;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    String username;
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    String firstName;
    String lastName;
    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate dob;
    Set<Role> roles;


    //@Email
    //@NotBlank
    //@NotEmpty
    //@NotNull
}
