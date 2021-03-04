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

    public int draw(Deck deck, int index) {
        cards.add(deck.getCard(index++));
        return index;
    }

    public int initializeDraw(Deck deck, int index) {
        draw(deck, index++);
        return draw(deck, index);
    }
    
    public abstract boolean canDrawOneMore(int score);

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_COUNT && cards.getScore() == BLACKJACK_VALUE;
    }

    public int getScore() {
        return cards.getScore();
    }

    public Cards getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }
}
