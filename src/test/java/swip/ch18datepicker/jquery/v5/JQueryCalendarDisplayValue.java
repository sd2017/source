package swip.ch18datepicker.jquery.v5;

import swip.framework.Browser;

import java.time.Month;
import java.util.function.Function;

import static swip.locators.JQueryByClassName.MONTH;
import static swip.locators.JQueryByClassName.YEAR;
import static swip.locators.JQueryById.CALENDAR;

public enum JQueryCalendarDisplayValue implements Function<Browser, Integer> {

    /**
     * Locate the integer value representing displayed year on a calendar
     */
    DISPLAY_YEAR {
        @Override
        public Integer apply(Browser browser) {
            String text = browser.untilFound(CALENDAR).getText(YEAR);
            return Integer.parseInt(text);
        }
    },

    /**
     * Locate the integer value representing displayed month on a calendar
     */
    DISPLAY_MONTH {
        @Override
        public Integer apply(Browser browser) {
            String text = browser.untilFound(CALENDAR).getUpperText(MONTH);
            return Month.valueOf(text).ordinal();
        }
    }
}
