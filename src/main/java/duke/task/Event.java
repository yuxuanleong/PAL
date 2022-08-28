package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task {
    private String date;
    private String time;

    public Event(String name, String dateAndTime) {
        super(name);
        this.date = getDateFromInput(dateAndTime);
        this.time = getTimeFromInput(dateAndTime);
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (at: "
                + LocalDate.parse(date).format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " "
                + formatTime(time)
                + ")";
    }

    @Override
    public String toMemoryString() {
        return "E"
                + " | "
                + (this.isDone() ? "1" : "0")
                +  " | "
                + this.getName()
                + " | "
                + this.date
                + " "
                + this.time;
    }

    private String[] splitIntoDateAndTime(String string) {
        String[] token = string.split(" ", 2);
        return token;
    }

    private String getDateFromInput(String input) {
        String[] token = splitIntoDateAndTime(input);
        return token[0];
    }

    private String getTimeFromInput(String input) {
        String[] token = splitIntoDateAndTime(input);
        return token[1];
    }

    private String formatTime(String str) {
        try {
            Date date = new SimpleDateFormat("HHmm").parse(str);
            SimpleDateFormat format = new SimpleDateFormat("h:mm a");
            return format.format(date);
        } catch (ParseException e) {
            System.out.println("There is some problem saving your event");
        }
        return time;
    }
}