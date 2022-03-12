package blackjack.domain;

import blackjack.domain.participant.Participant;

public enum Rule {

    INSTANCE;

    public Outcome judgeOutcome(Participant participant, Participant target) {
        if (!participant.isBust() && target.isBust() || participant.isBlackJack() && !target.isBlackJack()) {
            return Outcome.WIN;
        }
        if (participant.isBlackJack() && target.isBlackJack()) {
            return Outcome.DRAW;
        }
        if (participant.isBust() || !participant.isBlackJack() && target.isBlackJack()) {
            return Outcome.LOSE;
        }
        return judgeOutcomeByScore(participant.getScore(), target.getScore());
    }

    private Outcome judgeOutcomeByScore(int score, int target) {
        if (score > target) {
            return Outcome.WIN;
        }
        if (score == target) {
            return Outcome.DRAW;
        }
        return Outcome.LOSE;
    }
}
