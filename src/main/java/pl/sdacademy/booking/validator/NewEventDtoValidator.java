package pl.sdacademy.booking.validator;

import com.mysql.cj.util.StringUtils;
import pl.sdacademy.booking.model.NewEventDto;
import pl.sdacademy.booking.repository.EventRepository;
import pl.sdacademy.booking.util.TimeNowStub;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewEventDtoValidator {


        public static List<String> validate(NewEventDto newEventDto, TimeNowStub newTimeNowStub) {
        List<String> listOfMessages = new ArrayList<>();

        if (newEventDto == null) {
            String message = "Event is null";
            listOfMessages.add(message);

        }
        //date is null or empty
        if (newEventDto.getFromTime() == null) {
            String message = "From is null";
            listOfMessages.add(message);
        }
        if (newEventDto.getToTime() == null) {
            String message = "To is null";
            listOfMessages.add(message);
        }

        if (newEventDto.getFromTime() != null && newEventDto.getToTime() != null) {
            Duration duration = Duration.between(newEventDto.getFromTime(), newEventDto.getToTime());
            if (duration.isNegative()) {
                String message = "To is before from";
                listOfMessages.add(message);
            }
            if (duration.toMinutes() > 30) {
                String message = "Too long event";
                listOfMessages.add(message);
            }

            //date in the future:
            if (newEventDto.getFromTime().isBefore(LocalDateTime.now())) {
                String message = "From is in the past";
                listOfMessages.add(message);
            }
            if (newEventDto.getToTime().isBefore(LocalDateTime.now())) {
                String message = "To is in the past";
                listOfMessages.add(message);
            }

            //from 8 to 16
            if (newEventDto.getFromTime().getHour() < 8 || newEventDto.getToTime().getHour() > 16) {

                String message = "Event is not during opening hours";
                listOfMessages.add(message);
            }
        }
        //item name is null
        if (newEventDto.getItemName() == null || newEventDto.getItemName().isEmpty()) {
            //   if (StringUtils.isBlank(newEventDto.getItemName()))
            String message = "Item name is not set";
            listOfMessages.add(message);
        }
        return listOfMessages;
    }
}
