package blackjack.domain.game;

import blackjack.domain.card.Cards;

public class ParticipantGameInfo {

    private static final int ACE_ADDITIONAL_VALUE = 10;

    private final int score;
    private final Status status;

    public ParticipantGameInfo(Cards cards) {
        this.score = getScore(cards);
        this.status = Status.findStatus(cards.getCount(), this.score);
    }

    private int getScore(Cards cards) {
        int sum = cards.sum();

        if (canAddAdditionalValue(cards, sum)) {
            sum += ACE_ADDITIONAL_VALUE;
        }

        return sum;
    }

    private boolean canAddAdditionalValue(Cards cards, int sum) {
        return cards.hasAce() && !exceedBust(sum);
    }

    private boolean exceedBust(int sum) {
        return sum + ACE_ADDITIONAL_VALUE > GameResult.BLACKJACK_VALUE;
    }

    public boolean isBlackJack() {
        return this.status == Status.BLACKJACK;
    }

    public int getScore() {
        return score;
    }

    public Status getStatus() {
        return status;
    }
}
