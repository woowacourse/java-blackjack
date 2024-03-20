package card;

import java.util.List;

public class Hand {

    private static final int ADDITIONAL_ACE_CARD_SCORE = CardNumber.calculatePlusAceCardScore();
    private static final int MAX_BLACK_JACK_SCORE = 21;
    private static final int INIT_CARD_SETTING_COUNT = 2;


    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackJack() {
        return countMaxScore() == MAX_BLACK_JACK_SCORE
                && countCard() == INIT_CARD_SETTING_COUNT;
    }

    public boolean isBust() {
        return countMaxScore() > MAX_BLACK_JACK_SCORE;
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

    private int countMinimumScore() {
        return cards.stream()
                .mapToInt(Card::getDefaultCardScore)
                .sum();
    }

    public List<String> getCardsFeatures() {
        return cards.stream()
                .map(Card::getCardFeature)
                .toList();
    }

    private boolean isAceCardExist() {
        return cards.stream()
                .anyMatch(Card::isAceCard);
    }

    public int countCard() {
        return cards.size();
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

}
