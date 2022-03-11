package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Cards {

    public static final int BLACK_JACK_SCORE = 21;
    private static final int FIRST_RECEIVED_CARD_SIZE = 2;
    private static final int NUMBER_TO_USE_ACE_CARD_WITH_ONE = 10;

    private List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isFirstReceivedCards() {
        return cards.size() == FIRST_RECEIVED_CARD_SIZE;
    }

    public boolean isBlackJack() {
        return isFirstReceivedCards() && getTotalScore() == BLACK_JACK_SCORE;
    }

    public boolean exceedMaxScore() {
        return calculateScore() > BLACK_JACK_SCORE;
    }

    public int calculateScore() {
        int totalScore = getTotalScore();
        int countOfAce = getCountOfAce();
        while (countOfAce-- > 0 && totalScore > BLACK_JACK_SCORE) {
            totalScore -= NUMBER_TO_USE_ACE_CARD_WITH_ONE;
        }
        return totalScore;
    }

    private int getCountOfAce() {
        return (int) cards.stream()
                .filter(Card::isAceCard)
                .count();
    }

    private int getTotalScore() {
        return cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
