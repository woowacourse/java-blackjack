package domain.result;

import domain.card.Card;
import domain.participant.Participant;

public enum BlackjackResult {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private static final int BLACKJACK_MIN_CARD_COUNT = 2; // 블랙잭일 때 최소한의 카드 개수

    private final String value;

    BlackjackResult(String value) {
        this.value = value;
    }

    public static BlackjackResult getPlayerResult(Participant dealer, Participant player) {
        boolean isPlayerBust = isBust(player);
        boolean isDealerBust = isBust(dealer);
        boolean isPlayerBlackjack = isBlackjack(player);
        boolean isDealerBlackjack = isBlackjack(dealer);

        if (isPlayerBust || (!isPlayerBlackjack && isDealerBlackjack)
                || (!isDealerBust && isPlayerScoreLowerThenDealer(dealer, player))) {
            return LOSE;
        }
        if (isDealerBust || (isPlayerBlackjack && !isDealerBlackjack)
                || !isPlayerScoreLowerThenDealer(dealer, player)) {
            return WIN;
        }
        return DRAW;
    }

    private static boolean isPlayerScoreLowerThenDealer(Participant dealer, Participant player) {
        return player.getScore() < dealer.getScore();
    }

    private static boolean isBust(Participant participant) {
        return participant.getScore() > Card.BLACKJACK_SCORE;
    }

    private static boolean isBlackjack(Participant participant) {
        return participant.getScore() == Card.BLACKJACK_SCORE && participant.getCardCount() == BLACKJACK_MIN_CARD_COUNT;
    }

    public String getValue() {
        return value;
    }
}
