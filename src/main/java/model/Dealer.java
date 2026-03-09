package model;

import static constant.GameRule.DEALER_NAME;

import model.dto.PlayerName;

public class Dealer extends Participant{

    public Dealer() {
        super(new PlayerName(DEALER_NAME));
    }
}
