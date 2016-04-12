package swip.ch18datepicker.jquery.v3;

import swip.framework.Browser;

import java.time.Month;
import java.util.function.Function;

import static swip.locators.JQueryByClassName.MONTH;
import static swip.locators.JQueryById.CALENDAR;

public class DisplayMonth implements Function<Browser, Integer> {

    @Override
    public Integer apply(Browser browser) {
        String text = browser.untilFound(CALENDAR).getUpperText(MONTH);
        return Month.valueOf(text).ordinal();
    }
}
