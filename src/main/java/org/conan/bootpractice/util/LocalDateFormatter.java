package org.conan.bootpractice.util;

import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class LocalDateFormatter implements Formatter<LocalDate> {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate parse(String text, Locale locale) {
        return LocalDate.parse(text, FORMATTER);
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        return FORMATTER.format(localDate);
    }
}
