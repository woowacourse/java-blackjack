package blackjack.domain;

import static blackjack.domain.Result.*;

import java.util.List;

public class Rule {

    public static void judge(final Participants participants) {

        Participant dealer = participants.getDealer();
        List<Participant> players = participants.getPlayers();

        for (Participant player : players) {
            decideWinner(dealer, player);
        }
    }

    private static void decideWinner(final Participant dealer, final Participant player) {
        if (player.isBusted()) {
            player.set(LOSE);
            dealer.set(WIN);
            return;
        }

        if (!player.isBusted() && dealer.isBusted()) {
            player.set(WIN);
            dealer.set(LOSE);
            return;
        }

        if (player.score() == dealer.score()) {
            player.set(DRAW);
            dealer.set(DRAW);
        }

        if (player.score() > dealer.score()) {
            player.set(WIN);
            dealer.set(LOSE);
        }

        if (player.score() < dealer.score()) {
            player.set(LOSE);
            dealer.set(WIN);
        }
    }
}
