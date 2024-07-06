package com.builtlab.identity_service.mapper;

import com.builtlab.identity_service.dto.request.PermissionRequest;
import com.builtlab.identity_service.dto.response.PermissionResponse;
import com.builtlab.identity_service.entity.Permission;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}