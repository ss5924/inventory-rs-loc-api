package me.songha.rs.businesslocation;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessLocationConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(BusinessLocation.class, BusinessLocationDto.class).addMappings(mapper -> {
            mapper.map(BusinessLocation::getId, BusinessLocationDto::setId);
            mapper.map(BusinessLocation::getBusinessLocationName, BusinessLocationDto::setBusinessLocationName);
            mapper.map(BusinessLocation::getBusinessLocationType, BusinessLocationDto::setBusinessLocationType);
            mapper.map(BusinessLocation::getBusinessLocationAddress, BusinessLocationDto::setBusinessLocationAddress);
            mapper.map(BusinessLocation::getCreateAt, BusinessLocationDto::setCreateAt);
            mapper.map(BusinessLocation::getUpdateAt, BusinessLocationDto::setUpdateAt);
        });
        return modelMapper;
    }
}
