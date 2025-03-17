package domain.result;

import domain.participant.Participant;

public enum BlackjackResult {

    WIN,
    DRAW,
    LOSE,
    ;

    public static BlackjackResult getPlayerResult(Participant dealer, Participant player) {
        boolean isPlayerBust = player.isBust();
        boolean isDealerBust = dealer.isBust();
        boolean isPlayerBlackjack = player.isBlackjack();
        boolean isDealerBlackjack = dealer.isBlackjack();

        if (isPlayerBust || (!isPlayerBlackjack && isDealerBlackjack)
                || (!isDealerBust && player.getScore() < dealer.getScore())) {
            return LOSE;
        }
        if (isDealerBust || (isPlayerBlackjack && !isDealerBlackjack)
                || player.getScore() > dealer.getScore()) {
            return WIN;
        }
        return DRAW;
    }
}
