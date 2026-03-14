package domain.game;

import domain.participant.Participant;

public class BlackjackRule {

    public Outcome judge(Participant player, Participant dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return Outcome.TIE;
        }
        if (player.isBlackjack()) {
            return Outcome.BLACKJACK_WIN;
        }
        if (dealer.isBlackjack()) {
            return Outcome.LOSE;
        }
        if (player.isBust()) {
            return Outcome.LOSE;
        }
        if (dealer.isBust()) {
            return Outcome.WIN;
        }
        if (player.getScore() > dealer.getScore()) {
            return Outcome.WIN;
        }
        if (player.getScore() == dealer.getScore()) {
            return Outcome.TIE;
        }
        return Outcome.LOSE;
    }
}
