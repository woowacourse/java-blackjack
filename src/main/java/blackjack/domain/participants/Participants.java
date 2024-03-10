package blackjack.domain.participants;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Hands;
import java.util.List;
import java.util.Map;

public class Participants {

    private static final int DEALER_COUNT = 1;

    private final Dealer dealer;
    private final Players players;

    public Participants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void receiveDealerCard(Card card) {
        dealer.hit(card);
    }

    public void receiveInitialHands(List<Hands> allHands) {
        Hands dealerHands = extractOneHands(allHands);
        dealer.receiveHands(dealerHands);
        players.distributeHands(allHands);
    }

    private Hands extractOneHands(List<Hands> hands) {
        return hands.remove(hands.size() - 1);
    }

    public Map<Player, Boolean> calculateWinOrLose() {
        return players.calculateWinOrLose(dealer.calculateScore());
    }

    public boolean isDealerNotOver() {
        return dealer.canHit();
    }

    public int count() {
        return countPlayers() + DEALER_COUNT;
    }

    public int countPlayers() {
        return players.size();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
