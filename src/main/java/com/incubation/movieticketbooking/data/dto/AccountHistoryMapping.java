package com.incubation.movieticketbooking.data.dto;

import java.util.List;

public class AccountHistoryMapping {
    private int amount;
    private int totalSeatsBooked;
    private List<BookingHistory> bookingHistoryList;

    public AccountHistoryMapping(int amount, int totalSeatsBooked, List<BookingHistory> bookingHistoryList) {
        this.amount = amount;
        this.totalSeatsBooked = totalSeatsBooked;
        this.bookingHistoryList = bookingHistoryList;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotalSeatsBooked() {
        return totalSeatsBooked;
    }

    public void setTotalSeatsBooked(int totalSeatsBooked) {
        this.totalSeatsBooked = totalSeatsBooked;
    }

    public List<BookingHistory> getBookingHistoryList() {
        return bookingHistoryList;
    }

    public void setBookingHistoryList(List<BookingHistory> bookingHistoryList) {
        this.bookingHistoryList = bookingHistoryList;
    }
}
