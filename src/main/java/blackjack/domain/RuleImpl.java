package blackjack.domain;

import static blackjack.domain.participants.Result.*;

import java.util.List;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;

public class RuleImpl implements Rule {

    private static void decideWinner(final Participant dealer, final Participant player) {
        if (player.isBusted() || dealer.isBusted()) {
            decideWhenBusted(dealer, player);
            return;
        }
        decideWhenNotBusted(dealer, player);
    }

    private static void decideWhenBusted(final Participant dealer, final Participant player) {
        if (player.isBusted()) {
            player.set(LOSE);
            dealer.set(WIN);
        }

        if (!player.isBusted() && dealer.isBusted()) {
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

    @Override
    public void judgeBasic(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            RuleImpl.decideWinner(dealer, player);
        }
    }
}
