package domain;

import java.util.List;

import meesage.OutputMessage;

public class Dealer {

    private final List<Card> cards;

    private Dealer(List<Card> cards) {
        this.cards = cards;
    }

    public static Dealer of(List<Card> cards) {
        return new Dealer(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int cardScore = calculateRawScore();
        int aceCount = countAce();

        for (int i = 0; i < aceCount; i++) {
            cardScore = adjustForAce(cardScore);
        }

        return cardScore;
    }

    private int calculateRawScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int countAce() {
        return (int) cards.stream().filter(Card::isAce).count();
    }

    private int adjustForAce(int cardScore) {
        if (isBust(cardScore)) {
            cardScore -= Policy.ACE_HIGH_LOW_DIFF;
        }
        return cardScore;
    }

    public boolean isBust(int cardScore) {
        return cardScore > Policy.BUST_THRESHOLD;
    }

    public int getScoreOrZeroIfBust() {
        int score = calculateScore();
        if (isBust(score)) {
            return Policy.BUST_SCORE;
        }
        return score;
    }

    public String getDealerInfo() {
        return OutputMessage.DEALER_CARD_INFO.format(OutputMessage.DELIMITER.join(getCardsInfo()));
    }

    public List<String> getCardsInfo() {
        return cards.stream()
                .map(Card::getName)
                .toList();
    }

    public String getDealerInitialInfo() {
        return OutputMessage.DEALER_CARD_INFO.format(getCardsInfo().getFirst());
    }

    public String getDealerScoreInfo() {
        return OutputMessage.RESULT_TEXT.format(getDealerInfo(), calculateScore());
    }

    public boolean shouldHit() {
        return calculateScore() < Policy.DEALER_HIT_THRESHOLD;
    }
}