package model;

import java.util.ArrayList;
import java.util.List;

public class Player extends User {
    private Result result;

    public Player(String name, CardHand cardHand) {
        super(name, cardHand);
    }
}
