package domain.player;

import domain.card.Card;
import domain.vo.Name;

import java.util.List;

public class Player {
    private Name name;
    private HandCards handCards = new HandCards();

    public Player(Name name) {
        this.name = name;
    }

    public void addCard(Card card) {
        handCards.addCard(card);
    }

    public int getCardSum(){
        return handCards.calculateCards();
    }

    public String getName() {
        return name.getName();
    }

    public List<String> getCards() {
        return handCards.cardsToString();
    }
}
