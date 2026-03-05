package model;

import model.dto.Card;
import model.dto.PlayerResult;

public class Dealer {

    private final Participant dealer;

    public Dealer() {
        this.dealer = new Participant(new PlayerName("딜러"));
    }

    public PlayerResult getResult() {
        return dealer.getResult();
    }

    public void addCard(Card card) {
        dealer.addCard(card);
    }

    public void addScore(Integer score) {
        dealer.addScore(score);
    }

}
