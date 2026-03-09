package blackjack.domain;

public class Hand {

    protected static final int BUSTED_SCORE = 21;

    private final PlayingCards cards;

    public Hand() {
        cards = new PlayingCards();
    }

    public String getCardSnapshot() {
        return cards.getSnapshot();
    }

    public String getFirstSnapshot() {
        if (cards.isCardRemain()) {
            return cards.getFirstSnapshot();
        }
        throw new IllegalArgumentException("남은 카드가 없습니다.");
    }

    public String addCard(PlayingCards receivedCards) {
        cards.add(receivedCards);
        return getCardSnapshot();
    }

    public boolean isDrawable() {
        int scoreSum = calculateScoreSum();
        if (scoreSum < BUSTED_SCORE) {
            return true;
        }
        return !isBusted();
    }

    public boolean isBusted() {
        int totalScore = getTotalScore();
        return totalScore >= BUSTED_SCORE;
    }

    public int getTotalScore() {
        boolean busted = isBusted();
        int scoreSum = calculateScoreSum();
        if (busted) {
            return calculateWithAce(scoreSum);
        }
        return scoreSum;
    }

    public int getScoreSum() {
        return calculateScoreSum();
    }

    private int calculateScoreSum() {
        return cards.getCards()
                .stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int calculateWithAce(int scoreSum) {
        int aceCount = countAce();
        return calculateAce(scoreSum, aceCount);
    }

    private int countAce() {
        return (int) cards.getCards()
                .stream()
                .filter(Card::isAce)
                .count();
    }

    private int calculateAce(int scoreSum, int aceCount) {
        if (aceCount == 0) {
            return scoreSum;
        }
        int calculatedScore = scoreSum - 10;
        if (calculatedScore <= BUSTED_SCORE) {
            return calculatedScore;
        }
        return calculateAce(calculatedScore, aceCount - 1);
    }
}
