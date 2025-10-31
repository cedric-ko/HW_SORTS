package ru.netology.tickets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AviaSoulsTest {

    // Тестовые данные
    AviaSouls manager = new AviaSouls();
    TicketTimeComparator comparator = new TicketTimeComparator();

    Ticket ticket1 = new Ticket("Москва", "Оренбург", 5000, 8, 12);
    Ticket ticket2 = new Ticket("Москва", "Оренбург", 6000, 9, 14);
    Ticket ticket3 = new Ticket("Москва", "Оренбург", 5000, 10, 15);
    Ticket ticket4 = new Ticket("Москва", "Оренбург", 10000, 8, 9);
    Ticket ticket5 = new Ticket("Москва", "Оренбург", 7000, 9, 12);
    Ticket ticket6 = new Ticket("Оренбург", "Москва", 8000, 9, 11);
    Ticket ticket7 = new Ticket("Рязань", "Оренбург", 6000, 9, 13);

    // Тесты метода compareTo
    @Test
    public void shouldCompareAndGiveMinusOne() {

        Assertions.assertEquals(-1, ticket1.compareTo(ticket2));
    }

    @Test
    public void shouldCompareAndGiveZero() {

        Assertions.assertEquals(0, ticket1.compareTo(ticket3));
    }

    @Test
    public void shouldCompareAndGiveOne() {

        Assertions.assertEquals(1, ticket2.compareTo(ticket1));
    }

    // Тесты метода поиска search  с сортировкой по цене билета
    @Test
    public void shouldSearchAndSortByPriceFiveTickets() { // поиск и сортировка по возрастанию цены всех билетов заданного маршрута

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);

        Ticket[] expected = {ticket1, ticket3, ticket2, ticket5, ticket4};
        Ticket[] actual = manager.search("Москва", "Оренбург");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTheOnlyTicket() { // поиск и выдача единственного билета

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);

        Ticket[] expected = {ticket7};
        Ticket[] actual = manager.search("Рязань", "Оренбург");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGiveEmptyArrayWithNoDeparturePoint() { // пункт вылета отсутствует

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);

        Ticket[] expected = {};
        Ticket[] actual = manager.search("Мурманск", "Оренбург");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGiveEmptyArrayWithNoDestinationPoint() { // пункт прибытия отсутствует

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);

        Ticket[] expected = {};
        Ticket[] actual = manager.search("Москва", "Орел");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGiveEmptyArrayWithEmptyQuery() { // пустой запрос

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);

        Ticket[] expected = {};
        Ticket[] actual = manager.search("", "");

        Assertions.assertArrayEquals(expected, actual);
    }

    // тесты метода с компоратором
    @Test
    public void shouldSearchAndSortByTime() { // поиска маршрута и сортировка по продолжительности

        manager.add(ticket1); // 4 часа
        manager.add(ticket2); // 5 часов
        manager.add(ticket3); // 5 часов
        manager.add(ticket4); // 1 час
        manager.add(ticket5); // 3 часа
        manager.add(ticket6); // 2 часа
        manager.add(ticket7); // 4 часа

        Ticket[] expected = {ticket4, ticket5, ticket1, ticket2, ticket3};
        Ticket[] actual = manager.searchAndSortBy("Москва", "Оренбург", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTheOnlyTicketSortByTime() { // поиск и выдача единственного билета

        manager.add(ticket1); // 4 часа
        manager.add(ticket2); // 5 часов
        manager.add(ticket3); // 5 часов
        manager.add(ticket4); // 1 час
        manager.add(ticket5); // 3 часа
        manager.add(ticket6); // 2 часа
        manager.add(ticket7); // 4 часа

        Ticket[] expected = {ticket7};
        Ticket[] actual = manager.searchAndSortBy("Рязань", "Оренбург", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGiveEmptyArrayWithEmptyQuerySortByTime() { // пустой запрос

        manager.add(ticket1); // 4 часа
        manager.add(ticket2); // 5 часов
        manager.add(ticket3); // 5 часов
        manager.add(ticket4); // 1 час
        manager.add(ticket5); // 3 часа
        manager.add(ticket6); // 2 часа
        manager.add(ticket7); // 4 часа

        Ticket[] expected = {};
        Ticket[] actual = manager.searchAndSortBy("", "", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    // не повторяю тесты, аналогичные проверкам метода compareTo,
    // поскольку в них та же логика, а компаратор фактически не будет задействован
    // (не введён город вылета, город прилёта и т. д).
}