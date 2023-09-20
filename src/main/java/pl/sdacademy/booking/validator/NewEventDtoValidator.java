package pl.sdacademy.booking.validator;

import pl.sdacademy.booking.model.NewEventDto;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class NewEventDtoValidator {
    public static List<String> validate(NewEventDto newEventDto) {
        List<String> result = new ArrayList<>();
        //date is null or empty
        if (newEventDto.getFromTime() == null) {
            result.add("From is null");
        }
        if (newEventDto.getToTime() == null) {
            result.add("To is null");
        }

        if (newEventDto.getFromTime() != null && newEventDto.getToTime() != null) {
            Duration duration = Duration.between(newEventDto.getFromTime(), newEventDto.getToTime());
            if (duration.isNegative()) {
                result.add("To is before from");
            }
            if (duration.toMinutes() > 30) {
                result.add("to id before event");
            }
        }

            //sesja czas przekracza czas pracy salonu - DO POPRAWY
            if (newEventDto.getFromTime() != null && newEventDto.getToTime() != null) {
                Duration durations = Duration.between(newEventDto.getFromTime(), newEventDto.getToTime());
                if (durations.isNegative()) {
                    result.add("To is before from");
                }
                if (durations.toHours() > 8) {
                    result.add("Sorry, building is closed");
                }
            }

            //sesja bez odwolania sie do produktu
            if (newEventDto.getItemName() == null) {
                result.add("ItemName is null");
            }
            return result;
        }
    }
