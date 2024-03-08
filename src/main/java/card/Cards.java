package card;

import java.util.List;

public class Cards {

    private static final int ADDITIONAL_ACE_CARD_SCORE = CardNumber.ACE.getAdditionalScore();
    private static final int MAX_BLACK_JACK_SCORE = 21;
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int countMaxScore() {
        int aceCardCount = countAceCard();
        int minResultScore = countRoundScore();

        for (int aceCard = 0; aceCard < aceCardCount; aceCard++) {
            minResultScore = convertAceScore(minResultScore);
        }
        return minResultScore;
    }

    private int convertAceScore(int minResultScore) {
        if (minResultScore + ADDITIONAL_ACE_CARD_SCORE <= MAX_BLACK_JACK_SCORE) {
            minResultScore += ADDITIONAL_ACE_CARD_SCORE;
        }
        return minResultScore;
    }

    public int countRoundScore() {
        return cards.stream()
                .mapToInt(Card::getFirstCardNumber)
                .sum();
    }

    public List<String> getCardsStatus() {
        return cards.stream()
                .map(Card::getCard)
                .toList();
    }

    private int countAceCard() {
        return (int) cards.stream()
                .filter(card -> card.isSameCardNumber(CardNumber.ACE))
                .count();
    }

    public int countCard() {
        return cards.size();
    }
}
