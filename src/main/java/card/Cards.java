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
        int minimumScore = countMinimumScore();

        if (isAceCardExist()) {
            return convertAceScore(minimumScore);
        }
        return minimumScore;
    }

    private int convertAceScore(int maxResultScore) {
        if (maxResultScore + ADDITIONAL_ACE_CARD_SCORE <= MAX_BLACK_JACK_SCORE) {
            return maxResultScore + ADDITIONAL_ACE_CARD_SCORE;
        }
        return maxResultScore;
    }

    public int countMinimumScore() {
        return cards.stream()
                .mapToInt(Card::getDefaultCardNumber)
                .sum();
    }

    public List<String> getCardsStatus() {
        return cards.stream()
                .map(Card::getCardHand)
                .toList();
    }

    private boolean isAceCardExist() {
        return cards.stream()
                .anyMatch(card -> card.isSameCardNumber(CardNumber.ACE));
    }

    public int countCard() {
        return cards.size();
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

}
