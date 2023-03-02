package blackjack.domain;

import java.util.List;

public class Player {
    List<Card> cards ;

    public Player(List<Card> cards) {
        this.cards = cards;
    }

    public void receiveCard(Card card){
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }
}
