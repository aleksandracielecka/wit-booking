package pl.sdacademy.booking.util;

import java.time.LocalDateTime;

public class TimeNowStub {

    public LocalDateTime now() {
        System.out.println("calling facade class ");
        return LocalDateTime.of(2023, 9, 20, 15, 45, 00);
    }
}
