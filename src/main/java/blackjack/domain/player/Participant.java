package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK_CARD_COUNT = 2;
    private static final int BLACKJACK_VALUE = 21;

    private String name;
    private Cards cards;
    private int score;

    public Participant(String name) {
        this.name = name;
        cards = new Cards();
    }

    public void draw(Deck deck) {
        score = cards.add(deck.draw());
    }

    public void initializeDraw(Deck deck) {
        draw(deck);
        draw(deck);
    }
    
    public abstract boolean canDrawOneMore();

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_COUNT && score == BLACKJACK_VALUE;
    }

    public boolean isNotBlackjack() {
        return cards.size() != BLACKJACK_CARD_COUNT || score != BLACKJACK_VALUE;
    }

    public boolean isBust() {
        return score > BLACKJACK_VALUE;
    }

    public boolean isNotBust() {
        return score <= BLACKJACK_VALUE;
    }

    public boolean isBiggerScoreThan(Participant participant) {
        return score > participant.getScore();
    }

    public boolean isEqualScore(Participant participant) {
        return score == participant.getScore();
    }

    public int getScore() {
        return score;
    }

    public List<Card> cards() {
        return cards.cards();
    }

    public List<Card> cards(int showCount) {
        return cards.cards(showCount);
    }

    public Card getCard(int index) {
        return cards.getCard(index);
    }

    public String getName() {
        return name;
    }
}
