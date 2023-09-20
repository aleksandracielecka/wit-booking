package pl.sdacademy.booking.validator;

import org.junit.jupiter.api.Test;
import pl.sdacademy.booking.model.NewEventDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class NewEventDtoValidatorTest {
    @Test

    void shouldCheckThatFromIsNull() {
        NewEventDto input = NewEventDto.builder()
                .itemName("przyklad")
                .fromTime(LocalDateTime.of(2023, 9, 19, 19, 57))
                .toTime(LocalDateTime.of(2023, 9, 19, 19, 57))
                .build();
        List<String> result = NewEventDtoValidator.validate(input);
        assertThat(result).
                hasSize(1)
                .contains("From is null");
    }
    //kolejny test
    @Test
    void shouldCheckThatToIsNull() {
        NewEventDto input = NewEventDto.builder()
                .itemName("przyklad")
                .toTime(null)
                .fromTime(LocalDateTime.of(2023, 9, 19, 19, 57))
                .build();
        List<String> result = NewEventDtoValidator.validate(input);
        assertThat(result).
                hasSize(1)
                .contains("To is null");
    }

    @Test
    void shouldCheckThatToandFromIsNull() {
        NewEventDto input = NewEventDto.builder()
                .itemName("przyklad")
                .toTime(null)
                .fromTime(null)
                .build();
        List<String> result = NewEventDtoValidator.validate(input);
        assertThat(result)
                .hasSize(2)
                .contains("To and from is null");
    }
    @Test
    void shouldCheckThatEventIsNull(){
        NewEventDto input = NewEventDto.builder()
                .itemName(null)
                .fromTime(LocalDateTime.of(2023, 9, 19, 19, 57))
                .toTime(LocalDateTime.of(2023, 9, 19, 19, 57))
                .build();
        List<String> results = NewEventDtoValidator.validate(input);
        assertThat(results).
                hasSize(1)
                .contains("Event name is null");

    }
}