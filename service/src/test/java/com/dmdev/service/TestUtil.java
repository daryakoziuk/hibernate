package com.dmdev.service;

import com.dmdev.service.entity.Car;
import com.dmdev.service.entity.CarCharacteristic;
import com.dmdev.service.entity.PersonalInfo;
import com.dmdev.service.entity.Role;
import com.dmdev.service.entity.Status;
import com.dmdev.service.entity.Tariff;
import com.dmdev.service.entity.TariffType;
import com.dmdev.service.entity.TypeFuel;
import com.dmdev.service.entity.User;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@UtilityClass
public class TestUtil {

    public static final Long EXAMPLE_LONG_ID = 1L;
    public static final Integer EXAMPLE_INTEGER_ID = 1;
    public static final LocalDateTime EXAMPLE_DATE_REQUEST = LocalDateTime
            .of(2012, 12, 12, 12, 12);
    public static final LocalDateTime EXAMPLE_DATE_RETURN = LocalDateTime
            .of(2012, 12, 13, 12, 12);
    public static final BigDecimal PRICE_FOR_CHANGE = new BigDecimal(40);
    public static final String LASTNAME_FOR_UPDATE = "Irishkova";

    public static Tariff getTariff() {
        return Tariff.builder()
                .price(new BigDecimal(12))
                .tariffType(TariffType.HOURLY)
                .build();
    }

    public static User getUser() {
        return User.builder()
                .username("olya@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .lastname("Pypkova")
                        .firstname("Olya")
                        .build())
                .password("1234")
                .role(Role.USER)
                .build();
    }

    public static Car getCar() {
        return Car.builder()
                .model("Audi")
                .status(Status.FREE)
                .build();
    }

    public static CarCharacteristic getCarCharacteristic() {
        return CarCharacteristic.builder()
                .car(getCar())
                .engineVolume(1900)
                .typeFuel(TypeFuel.DIESEL)
                .build();
    }
}
