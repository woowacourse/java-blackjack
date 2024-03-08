package blackjack.domain.rule;

import blackjack.domain.Dealer;
import blackjack.domain.DealerGameResult;
import blackjack.domain.player.Hand;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class Judge {

    private static final int BLACK_JACK = 21;
    private static final int ACE_WEIGHT = 10;
    private static final int DEALER_HIT_THRESHOLD = 17;

    public int calculateBestScore(Hand hand) {
        int aceCount = hand.countAce();
        int sum = hand.sum();
        while (aceCount > 0 && (sum + ACE_WEIGHT) <= BLACK_JACK) {
            sum += ACE_WEIGHT;
            aceCount--;
        }
        return sum;
    }

    public boolean isBustedHand(Hand hand) {
        return BLACK_JACK < calculateBestScore(hand);
    }

    public boolean canDealerHit(Dealer dealer) {
        return calculateBestScore(dealer.getHand()) < DEALER_HIT_THRESHOLD;
    }

    public boolean isPlayerWin(Dealer dealer, Player player) {
        if (isBustedHand(player.getHand())) {
            return false;
        }
        if (isBustedHand(dealer.getHand())) {
            return true;
        }
        return calculateBestScore(player.getHand()) > calculateBestScore(dealer.getHand());
    }

    public DealerGameResult calculateDealerResult(Dealer dealer, Players players) {
        int dealerLoseCount = (int) players.getPlayers().stream()
                .filter(player -> isPlayerWin(dealer, player))
                .count();
        return new DealerGameResult(players.countPlayer() - dealerLoseCount, dealerLoseCount);
    }
}
