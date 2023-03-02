package blackjack.domain.model;

import blackjack.domain.vo.Name;

import java.util.List;

public class Player {
    private final Name name;
    private final Cards cards;
    public Player(Name name, Cards cards){
        this.name = name;
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public void drawCard(Card card) {
        cards.add(card);
    }
}
