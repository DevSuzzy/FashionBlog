package com.susancode.fashionblog.service.implementation;

import com.susancode.fashionblog.dto.request.AdminRequestDto;
import com.susancode.fashionblog.dto.request.LoginRequestDto;
import com.susancode.fashionblog.dto.response.AdminResponseDto;
import com.susancode.fashionblog.entity.Admin;
import com.susancode.fashionblog.exceptions.customexceptions.AdminNotFoundException;
import com.susancode.fashionblog.exceptions.customexceptions.EmailAddressAlreadyExistException;
import com.susancode.fashionblog.exceptions.customexceptions.WrongCredentialsException;
import com.susancode.fashionblog.repository.AdminRepository;
import com.susancode.fashionblog.service.AdminService;
import com.susancode.fashionblog.utils.ModelMapperUtils;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public AdminResponseDto createUser(AdminRequestDto adminRequestDto) {
        adminRepository.findByEmail(adminRequestDto.getEmail()).ifPresent(user -> {
            throw new EmailAddressAlreadyExistException(adminRequestDto.getEmail());
        });
        return ModelMapperUtils.map(adminRepository.save(Admin.builder()
                .name(adminRequestDto.getName())
                .password(adminRequestDto.getPassword())
                .email(adminRequestDto.getEmail())
                .build()), AdminResponseDto.class);
    }
    @Override
    public AdminResponseDto updateAdmin(AdminRequestDto adminRequestDto, Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> {
            throw new AdminNotFoundException(id);
        });

        if (!StringUtils.isBlank(adminRequestDto.getEmail())) {
            admin.setEmail(adminRequestDto.getEmail());
        }

        if (!StringUtils.isBlank(adminRequestDto.getName())) {
            admin.setName(adminRequestDto.getName());
        }

        if (!StringUtils.isBlank(adminRequestDto.getPassword())) {
            admin.setPassword(adminRequestDto.getPassword());
        }

        return ModelMapperUtils.map(adminRepository.save(admin), AdminResponseDto.class);
    }

    @Override
    public Boolean deleteAdmin(Long id) {

        Admin admin = adminRepository.findById(id).orElseThrow(() -> {
            throw new AdminNotFoundException(id);
        });
        adminRepository.delete(admin);
        return true;
    }

    @Override
    public AdminResponseDto fetchAdmin(LoginRequestDto loginRequestDto) {

        Optional<Admin> admin = adminRepository.findByEmail(loginRequestDto.getEmail());
        if (admin.isPresent()) {
            if (admin.get().getPassword().equals(loginRequestDto.getPassword())) {
                return ModelMapperUtils.map(admin.get(), AdminResponseDto.class);
            } else {
                throw new WrongCredentialsException(loginRequestDto.getEmail());
            }
        } else {
            throw new AdminNotFoundException(loginRequestDto.getEmail());
        }


}

}
