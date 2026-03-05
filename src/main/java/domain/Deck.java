package domain;

import common.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    private static final int INIT_DECK_SIZE = 2;
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck createDeck(CardCreationStrategy strategy) {
        List<Card> cards = strategy.create();
        return new Deck(cards);
    }

    public static Deck createParticipantDeck(Deck deck) {
        return new Deck(deck.drawCard(INIT_DECK_SIZE));
    }

    public List<Card> drawCard(int count) {

        if (count > cards.size() || count < 1) {
            throw new IllegalArgumentException(ErrorMessage.DRAW_CARD_OUT_OF_RANGE.getMessage());
        }

        ArrayList<Card> selectedCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            selectedCards.add(cards.removeFirst());
        }
        return selectedCards;
    }

    public int calculateCardScoreSumExceptAce() {
        int sum = 0;
        for (Card card : cards) {
            sum = addCardScoreExceptAce(card, sum);
        }

        return sum;
    }

    private int addCardScoreExceptAce(Card card, int sum) {
        if (!card.isAce()) {
            sum += card.getCardContents().getScore();
        }
        return sum;
    }
}
