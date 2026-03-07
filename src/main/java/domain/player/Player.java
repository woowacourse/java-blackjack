package domain.player;

import domain.card.Card;
import domain.vo.Name;

public class Player {
    private Name name;
    private HandCards handCards = new HandCards();

    public Player(Name name) {
        this.name = name;
    }

    public void addCard(Card card) {
        handCards.addCard(card);
    }

    public String getName() {
        return name.getName();
    }

}
