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
    void shouldCheckToIsBeforeFrom() {
        NewEventDto input = NewEventDto.builder()
                .itemName(null)
                .fromTime(LocalDateTime.of(2023, 9, 19, 13, 23))
                .toTime(LocalDateTime.of(2023, 9, 19, 11, 57))
                .build();
        List<String> results = NewEventDtoValidator.validate(input);
        assertThat(results).
                hasSize(1)
                .contains("To is before from");
    }

    @Test
    void shouldCheckThatEventIsToLong() {
        NewEventDto input = NewEventDto.builder()
                .itemName("przyklad")
                .fromTime(LocalDateTime.of(2023, 9, 19, 14, 11))
                .toTime(LocalDateTime.of(2023, 9, 19, 15, 57))
                .build();
        List<String> results = NewEventDtoValidator.validate(input);
        assertThat(results).
                hasSize(1)
                .contains("To long event");

    }

    @Test
    void shouldCheckEventIsTooLongAndNullItem() {
        NewEventDto input = NewEventDto.builder()
                .itemName(null)
                .fromTime(LocalDateTime.of(2023, 9, 19, 11, 57))
                .toTime(LocalDateTime.of(2023, 9, 19, 13, 57))
                .build();
        List<String> results = NewEventDtoValidator.validate(input);
        assertThat(results).
                hasSize(2)
                .contains("To long event", "Event without item");
    }

    @Test
    void shouldIsToIsBeforeFromAndItemIsNull() {
        NewEventDto input = NewEventDto.builder()
                .itemName(null)
                .fromTime(LocalDateTime.of(2023, 9, 19, 16, 57))
                .toTime(LocalDateTime.of(2023, 9, 19, 6, 57))
                .build();
        List<String> results = NewEventDtoValidator.validate(input);
        assertThat(results).
                hasSize(2)
                .contains("To is before from", "Event without item");
    }

    @Test
    void shouldIsTooEarlyOrTooLateAndItemIsNull() {
        NewEventDto input = NewEventDto.builder()
                .itemName(null)
                .fromTime(LocalDateTime.of(2023, 9, 19, 6, 23))
                .toTime(LocalDateTime.of(2023, 9, 19, 7, 57))
                .build();
        List<String> results = NewEventDtoValidator.validate(input);
        assertThat(results).
                hasSize(3)
                .contains("To long event", "Event without item", "Too late or too early");
    }

    @Test
    void shouldIsTooLateOrTooEarlyAndItemIsNull() {
        NewEventDto input = NewEventDto.builder()
                .itemName(null)
                .fromTime(LocalDateTime.of(2023, 9, 19, 6, 12))
                .toTime(LocalDateTime.of(2023, 9, 19, 21, 57))
                .build();
        List<String> results = NewEventDtoValidator.validate(input);
        assertThat(results).
                hasSize(3)
                .contains("To long event", "Event without item", "Too late or too early");
    }

    @Test
    void shouldCheckAddItemCorrectly() {
        NewEventDto input = NewEventDto.builder()
                .itemName("przykład")
                .fromTime(LocalDateTime.of(2023, 9, 19, 12, 23))
                .toTime(LocalDateTime.of(2023, 9, 19, 12, 36))
                .build();
        List<String> results = NewEventDtoValidator.validate(input);
        assertThat(results).
                hasSize(0);
    }

    @Test
    void shouldItemIsNullAndFromTimeAndToTime() {
        NewEventDto input = NewEventDto.builder()
                .itemName(null)
                .fromTime(null)
                .toTime(null)
                .build();
        List<String> results = NewEventDtoValidator.validate(input);
        assertThat(results).
                hasSize(3)
                .contains("To is null", "Event without item", "From is null");
    }


}