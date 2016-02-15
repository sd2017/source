package swip.ch17datepicker.datepicker;


import swip.ch15pageflow.framework.Browser;

import java.util.function.Function;

/**
 * A general purpose DatePicker can be used to pick a given date from
 * the calendar flyout provided by JavaScript framework.
 *
 * @author Yujun Liang
 * @since 0.1
 */
public class Calendar {

    private final Browser browser;        //<1>
    private final Function<Browser, Void> trigger;   //<2>

    /**
     * Constructor of the DatePicker which taking a Calendar interface.
     *
     * @param browser
     * @param trigger
     */
    public Calendar(Browser browser, Function<Browser, Void> trigger) { //<6>
        this.browser = browser;
        this.trigger = trigger;
    }

    /**
     * Display the calendar
     */
    public void show() {  //<7>
        trigger.apply(browser);      //<9>
    }
}