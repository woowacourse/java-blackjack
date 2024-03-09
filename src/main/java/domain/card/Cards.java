package domain.card;

import java.util.List;

public class Cards {

    private static final int ADDITIONAL_ACE_CARD_SCORE = CardNumber.ACE.getAdditionalAceScore();
    private static final int MAX_BLACK_JACK_SCORE = 21;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int countMaxScore() {
        return convertAceScore(countMatchScore());
    }

    private int convertAceScore(int maxResultScore) {
        if (maxResultScore + ADDITIONAL_ACE_CARD_SCORE <= MAX_BLACK_JACK_SCORE) {
            maxResultScore += ADDITIONAL_ACE_CARD_SCORE;
        }
        return maxResultScore;
    }

    public int countMatchScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public int countCard() {
        return cards.size();
    }
}
