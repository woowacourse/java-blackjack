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

    public void drawCard(Card card) {
        cards.add(card);
    }

    public int calculateTotal(){
        return this.cards.calculateTotalScore();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
