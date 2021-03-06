package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

public abstract class Participant {

    private static final int BLACKJACK_CARD_COUNT = 2;
    private static final int BLACKJACK_VALUE = 21;

    private String name;
    private Cards cards;

    public Participant(String name) {
        this.name = name;
        cards = new Cards();
    }

    public void draw(Deck deck) {
        cards.add(deck.draw());
    }

    public void initializeDraw(Deck deck) {
        draw(deck);
        draw(deck);
    }
    
    public abstract boolean canDrawOneMore(int score);

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_COUNT && cards.calculateScore() == BLACKJACK_VALUE;
    }

    public int getScore() {
        return cards.calculateScore();
    }

    public Cards getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }
}
