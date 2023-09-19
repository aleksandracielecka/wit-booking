package pl.sdacademy.booking.model;


import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import pl.sdacademy.booking.data.ItemEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@Data
public class EventDto {

    private long id;
    private String itemName;
    private BigDecimal itemPrice;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;
}
