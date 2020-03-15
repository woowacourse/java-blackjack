package blackjack.domain;

import static blackjack.domain.participants.Result.*;

import java.util.List;

import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;

public class Rule {

    public static void judge(final Participants participants) {
        Participant dealer = participants.getDealer();
        List<Participant> players = participants.getPlayers();
        for (Participant player : players) {
            decideWinner(dealer, player);
        }
    }

    private static void decideWinner(final Participant dealer, final Participant player) {
        if (isBusted(player) || isBusted(dealer)) {
            decideWhenBusted(dealer, player);
            return;
        }
        decideWhenNotBusted(dealer, player);
    }

    private static void decideWhenBusted(final Participant dealer, final Participant player) {
        if (isBusted(player)) {
            player.set(LOSE);
            dealer.set(WIN);
        }

        if (!isBusted(player) && isBusted(dealer)) {
            player.set(WIN);
            dealer.set(LOSE);
        }
    }

    private static void decideWhenNotBusted(final Participant dealer, final Participant player) {
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

    private static boolean isBusted(Participant participant) {
        return participant.score() > 21;
    }
}
