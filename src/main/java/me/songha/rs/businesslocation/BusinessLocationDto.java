package me.songha.rs.businesslocation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class BusinessLocationDto {
    private Long id;
    private String businessLocationName;
    private String businessLocationAddress;
    private String businessLocationType;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createAt;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateAt;

    @Builder
    public BusinessLocationDto(Long id, String businessLocationName, String businessLocationAddress, String businessLocationType, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.businessLocationName = businessLocationName;
        this.businessLocationAddress = businessLocationAddress;
        this.businessLocationType = businessLocationType;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public BusinessLocation toEntity() {
        return BusinessLocation.builder()
                .id(this.id)
                .businessLocationAddress(this.businessLocationAddress)
                .businessLocationName(this.businessLocationName)
                .businessLocationType(this.businessLocationType)
                .build();
    }
}
