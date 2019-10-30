package com.mzaru.booking.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull(message = "field is required")
    @Size(min = 1, max = 50)
    private String name;

    @Column
    @NotNull(message = "field is required")
    @Size(min = 1, max = 100)
    private String surname;

    @NaturalId
    @Column(unique = true)
    @NotNull(message = "field is required")
    @Size(min = 1, max = 100)
    private String login;

    @Column
    @NotNull(message = "field is required")
    @Size(min = 6, max = 50)
    private String password;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Booking> bookings;

    public User() {
    }

    public User(long id, @NotNull(message = "field is required") @Size(min = 1, max = 50) String name, @NotNull(message = "field is required") @Size(min = 1, max = 100) String surname, @NotNull(message = "field is required") @Size(min = 1, max = 100) String login) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
    }

    public User(@NotNull(message = "field is required") @Size(min = 1, max = 50) String name, @NotNull(message = "field is required") @Size(min = 1, max = 100) String surname, @NotNull(message = "field is required") @Size(min = 1, max = 100) String login, @NotNull(message = "field is required") @Size(min = 6, max = 50) String password, List<Booking> bookings) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.bookings = bookings;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setUser(this);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        booking.setUser(null);
    }

    @Override
    public String toString() {
        return String.format("Name: %s ; Surname: %s ; Id: %d", name, surname, id);
    }
}
