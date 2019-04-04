package me.loda.java.reflection.annotations;

import java.time.LocalDateTime;

import me.loda.java.reflection.Person;

@JsonName(value = "super_man")
public class SuperMan extends Person {
    private String name;

    @JsonName("date_of_birth")
    private LocalDateTime dateOfBirth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public int getOutfit() {
        return NAKED;
    }
}
