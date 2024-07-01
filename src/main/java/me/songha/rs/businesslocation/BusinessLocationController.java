package me.songha.rs.businesslocation;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business-location")
public class BusinessLocationController {
    private final BusinessLocationService businessLocationService;

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<BusinessLocationDto> getBusinessLocationById(@PathVariable Long id) {
        try {
            BusinessLocationDto businessLocationDto = businessLocationService.getBusinessLocationById(id);
            return ResponseEntity.ok(businessLocationDto);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(value = "/address/{addressKeyword}")
    public ResponseEntity<List<BusinessLocationDto>> getBusinessLocations(@PathVariable String addressKeyword) {
        List<BusinessLocationDto> businessLocations = businessLocationService.getBusinessLocationsByAddress(addressKeyword);
        return ResponseEntity.ok(businessLocations);
    }

    @PostMapping
    public ResponseEntity<BusinessLocationDto> saveBusinessLocation(@RequestBody BusinessLocationDto businessLocationDto) {
        BusinessLocationDto createdBusinessLocation = businessLocationService.save(businessLocationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBusinessLocation);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<BusinessLocationDto> updateBusinessLocation(@PathVariable Long id, @RequestBody BusinessLocationDto newBusinessLocationDto) {
        BusinessLocationDto updatedBusinessLocation = businessLocationService.update(id, newBusinessLocationDto);
        return ResponseEntity.ok(updatedBusinessLocation);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteBusinessLocation(@PathVariable Long id) {
        businessLocationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
