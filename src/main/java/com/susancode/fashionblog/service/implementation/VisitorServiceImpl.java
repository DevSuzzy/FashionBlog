package com.susancode.fashionblog.service.implementation;

import com.susancode.fashionblog.constants.AuthorType;
import com.susancode.fashionblog.dto.request.LoginRequestDto;
import com.susancode.fashionblog.dto.request.VisitorRequestDto;
import com.susancode.fashionblog.dto.response.VisitorResponseDto;
import com.susancode.fashionblog.entity.Visitor;
import com.susancode.fashionblog.exceptions.customexceptions.VisitorNotFoundException;
import com.susancode.fashionblog.exceptions.customexceptions.WrongCredentialsException;
import com.susancode.fashionblog.repository.VisitorRepository;
import com.susancode.fashionblog.service.VisitorService;
import com.susancode.fashionblog.utils.ModelMapperUtils;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorRepository;

    @Override
    public VisitorResponseDto createVisitor(VisitorRequestDto visitorRequestDto) {
        visitorRepository.findByEmail(visitorRequestDto.getEmail()).ifPresent(user -> {
            throw new VisitorNotFoundException(visitorRequestDto.getEmail());
        });

        var visitor = Visitor.builder()
                .fullName(visitorRequestDto.getFullName())
                .email(visitorRequestDto.getEmail())
                .password(visitorRequestDto.getPassword())
                .authorType(
                        AuthorType.VISITOR
                )
                .build();
        visitorRepository.save(visitor);

        return ModelMapperUtils.map(visitor, VisitorResponseDto.class);
    }
    @Override
    public VisitorResponseDto updateVisitor(VisitorRequestDto visitorRequestDto, Long id) {
        Visitor visitor = visitorRepository.findById(id).orElseThrow(() -> {
            throw new VisitorNotFoundException(id);
        });

        if (!StringUtils.isBlank(visitorRequestDto.getEmail())) {
            visitor.setEmail(visitorRequestDto.getEmail());
        }

        if (!StringUtils.isBlank(visitorRequestDto.getFullName())) {
            visitor.setFullName(visitorRequestDto.getFullName());
        }

        if (!StringUtils.isBlank(visitorRequestDto.getPassword())) {
            visitor.setPassword(visitorRequestDto.getPassword());
        }

        return ModelMapperUtils.map(visitorRepository.save(visitor), VisitorResponseDto.class);
    }

    @Override
    public Boolean deleteVisitor(Long id) {
        Visitor visitor = visitorRepository.findById(id).orElseThrow(() -> {
            throw new VisitorNotFoundException(id);
        });
        visitorRepository.delete(visitor);
        return true;
    }

    @Override
    public VisitorResponseDto getVisitorByEmail(String email) {
        Visitor visitor = visitorRepository.findByEmail(email).orElseThrow(() -> {
            throw new VisitorNotFoundException(email);
        });
        return ModelMapperUtils.map(visitor, VisitorResponseDto.class);
    }

    @Override
    public VisitorResponseDto fetchVisitor(LoginRequestDto loginRequestDto) {
        Optional<Visitor> visitor = visitorRepository.findByEmail(loginRequestDto.getEmail());
        if (visitor.isPresent()) {
            if (visitor.get().getPassword().equals(loginRequestDto.getPassword())) {
                return ModelMapperUtils.map(visitor.get(), VisitorResponseDto.class);
            } else {
                throw new WrongCredentialsException(loginRequestDto.getEmail());
            }
        } else {
            throw new VisitorNotFoundException(loginRequestDto.getEmail());
        }

    }
}
