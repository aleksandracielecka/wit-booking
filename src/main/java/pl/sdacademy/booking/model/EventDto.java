package pl.sdacademy.booking.model;


import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pl.sdacademy.booking.data.ItemEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class EventDto {

//    private long id;
//    private ItemEntity item;

    private String name;
    private BigDecimal price;
    private LocalDateTime from;
    private LocalDateTime to;

}
