package com.mzaru.booking.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@JsonInclude
public class Room {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NaturalId
    @Column(unique = true)
    @NotNull(message = "field is required")
    @Size(min = 1, max = 50)
    private String name;

    @Column
    @Size(max = 256)
    private String location;

    @Column
    @NotNull(message = "field is required")
    @Min(1)
    @Max(100)
    private Integer seats;

    @Column
    private Boolean hasProjector = null;

    @Column
    @Size(max = 100)
    private String phoneNumber;

    public Room() {
    }

    public Room(@NotNull(message = "field is required") @Size(min = 1, max = 50) String name, @Size(max = 256) String location, @NotNull(message = "field is required") @Min(1) @Max(100) Integer seats, boolean hasProjector, @Size(max = 100) String phoneNumber) {
        this.name = name;
        this.location = location;
        this.seats = seats;
        this.hasProjector = hasProjector;
        this.phoneNumber = phoneNumber;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Boolean getHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(Boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
