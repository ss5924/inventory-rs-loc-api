package me.songha.rs.businesslocation;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BusinessLocationService {
    private final BusinessLocationRepository businessLocationRepository;
    private final ModelMapper modelMapper;

    public BusinessLocationDto getBusinessLocationById(Long id) {
        return businessLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found id " + id))
                .toBusinessLocationDto();
    }

//    @Cacheable(value = "getBusinessLocationsByAddress", key = "#addressKeyword", cacheManager = "cacheManager")
    public List<BusinessLocationDto> getBusinessLocationsByAddress(String addressKeyword) {
        return businessLocationRepository.findByBusinessLocationAddressContaining(addressKeyword).stream()
                .map(businessLocation -> modelMapper.map(businessLocation, BusinessLocationDto.class)).toList();
    }

    @Transactional
    public BusinessLocationDto save(BusinessLocationDto businessLocationDto) {
        return businessLocationRepository.save(businessLocationDto.toEntity()).toBusinessLocationDto();
    }

    @Transactional
    public BusinessLocationDto update(Long id, BusinessLocationDto newBusinessLocationDto) {
        return businessLocationRepository.findById(id).map(businessLocation -> {
            businessLocation.setBusinessLocationAddress(newBusinessLocationDto.getBusinessLocationAddress());
            businessLocation.setBusinessLocationType(newBusinessLocationDto.getBusinessLocationType());
            businessLocation.setBusinessLocationName(newBusinessLocationDto.getBusinessLocationName());
            return businessLocationRepository.save(businessLocation).toBusinessLocationDto();
        }).orElseThrow(() -> new ResourceNotFoundException("Not found id " + id));
    }

    @Transactional
    public void deleteById(Long id) {
        if (businessLocationRepository.existsById(id)) {
            businessLocationRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Not found id " + id);
        }
    }

}
