package ru.netology.tickets;

import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket t1, Ticket t2) {
        if ((t1.getTimeTo() - t1.getTimeFrom()) < (t2.getTimeTo() - t2.getTimeFrom())) { // ваш код
            return -1;
        } else if ((t1.getTimeTo() - t1.getTimeFrom()) > (t2.getTimeTo() - t2.getTimeFrom())) {
            return 1;
        } else {
            return 0;
        }
    }
}