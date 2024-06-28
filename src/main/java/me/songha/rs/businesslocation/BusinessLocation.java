package me.songha.rs.businesslocation;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "business_location")
public class BusinessLocation implements Serializable {
    @Serial
    private static final long serialVersionUID = 6723632367027356068L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String businessLocationName;
    private String businessLocationAddress;
    private String businessLocationType;

    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime updateAt;

    @Builder
    public BusinessLocation(Long id, String businessLocationName, String businessLocationAddress, String businessLocationType) {
        this.id = id;
        this.businessLocationName = businessLocationName;
        this.businessLocationAddress = businessLocationAddress;
        this.businessLocationType = businessLocationType;
    }

    public BusinessLocationDto toBusinessLocationDto() {
        return BusinessLocationDto.builder()
                .id(this.id)
                .businessLocationName(this.businessLocationName)
                .businessLocationAddress(this.businessLocationAddress)
                .businessLocationType(this.businessLocationType)
                .createAt(this.createAt)
                .updateAt(this.updateAt)
                .build();
    }

    public void setBusinessLocationName(String businessLocationName) {
        this.businessLocationName = businessLocationName;
    }

    public void setBusinessLocationAddress(String businessLocationAddress) {
        this.businessLocationAddress = businessLocationAddress;
    }

    public void setBusinessLocationType(String businessLocationType) {
        this.businessLocationType = businessLocationType;
    }
}
