package services;

import Exceptions.UncorrectDataException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class validator {
    public static boolean isValidDate(String birthday)  {

        LocalDate date;
        Integer [] month_30 = {4, 6, 9, 11};
        int day;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(birthday, formatter);
            day = date.getDayOfMonth();

        } catch (UncorrectDataException e) {
            throw new UncorrectDataException("Неправильно введена дата" + e.getMessage());
        }

        if ((Arrays.asList(month_30).contains(date.getMonthValue()) && day > 30) ||
                (date.isLeapYear() && date.getMonthValue() == 2 && day > 29) ||
                (!date.isLeapYear() && date.getMonthValue() == 2 && day > 28)) {

            throw new UncorrectDataException("Некорректная дата рождения");

        } else
            return true;
    }
}