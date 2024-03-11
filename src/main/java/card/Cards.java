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
        int maxResultScore = countMatchScore();

        if (aceCardCount > 0) {
            return convertAceScore(maxResultScore);
        }
        return maxResultScore;
    }

    private int convertAceScore(int maxResultScore) {
        if (maxResultScore + ADDITIONAL_ACE_CARD_SCORE <= MAX_BLACK_JACK_SCORE) {
            return maxResultScore + ADDITIONAL_ACE_CARD_SCORE;
        }
        return maxResultScore;
    }

    public int countMatchScore() {
        return cards.stream()
                .mapToInt(Card::getDefaultCardNumber)
                .sum();
    }

    public List<String> getCardsStatus() {
        return cards.stream()
                .map(Card::getCardHand)
                .toList();
    }

    public int countAceCard() {
        return (int) cards.stream()
                .filter(card -> card.isSameCardNumber(CardNumber.ACE))
                .count();
    }

    public int countCard() {
        return cards.size();
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

}
