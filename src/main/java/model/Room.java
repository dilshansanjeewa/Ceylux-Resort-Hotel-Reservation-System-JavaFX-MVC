package model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Room {

    private int roomNumber;
    private String roomType;
    private String description;
    private String mealStatus;
    private double pricePerNight;
    private String roomStatus;

}
