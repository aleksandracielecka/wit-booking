package pl.sdacademy.booking.validator;

import com.mysql.cj.util.StringUtils;
import pl.sdacademy.booking.model.NewEventDto;
import pl.sdacademy.booking.util.TimeNowStub;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewEventDtoValidator {
    public static List<String> validate(NewEventDto newEventDto) {
        List<String> result = new ArrayList<>();
        if (newEventDto == null) {
            result.add("Event is null");
            return result;
        }
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
                result.add("Too long event");
            }

            //date in the future:
            if (newEventDto.getFromTime().isBefore(LocalDateTime.now())) {
                result.add("From is in the past");
            }
            if (newEventDto.getToTime().isBefore(LocalDateTime.now())) {
                result.add("To is in the past");
            }

            //from 8 to 16
            if (newEventDto.getFromTime().getHour() < 8 || newEventDto.getToTime().getHour() > 16) {

                result.add("Event is not during opening hours");
            }
        }
        //item name is null
        if (newEventDto.getItemName() == null || newEventDto.getItemName().isEmpty()) {
            //   if (StringUtils.isBlank(newEventDto.getItemName()))
            result.add("Item name is not set");
        }
        return result;
    }
}
