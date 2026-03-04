package model;

import java.util.List;
import model.dto.Card;
import model.dto.PlayerResult;

public class Dealer {

    private final Player dealer;

    public Dealer() {
        this.dealer = new Player("딜러");
    }

    public PlayerResult getResult() {
        return dealer.getResult();
    }

    public void addCard(Card card) {
        dealer.addCard(card);
    }

}
