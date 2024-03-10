package blackjack.domain.participants;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Hands;
import java.util.List;
import java.util.Map;

public class Participants {

    private static final int DEALER_COUNT = 1;
    private static final int DEALER_BOUNDARY_SCORE = 17;

    private final Player dealer;
    private final Players players;

    public Participants(Player dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void receivePlayerCard(Card card, int playerIndex) {
        players.receiveOnePlayerCard(card, playerIndex);
    }

    public void receiveDealerCard(Card card) {
        dealer.receiveCard(card);
    }

    public void receiveInitialHands(List<Hands> AllHands) {
        Hands dealerHands = extractOneHands(AllHands);
        dealer.receiveHands(dealerHands);

        for (int index = 0; index < players.size(); index++) {
            Hands currentHands = AllHands.get(index);
            players.receiveOnePlayerHands(currentHands, index);
        }
    }

    private Hands extractOneHands(List<Hands> hands) {
        return hands.remove(hands.size() - 1);
    }

    public Map<Player, Boolean> calculateWinOrLose() {
        return players.calculateWinOrLose(dealer.calculateScore());
    }

    public boolean isPlayerNotOver(int playerIndex) {
        return players.isOnePlayerNotOver(playerIndex);
    }

    public boolean isDealerNotOver() {
        return dealer.isNotOver(DEALER_BOUNDARY_SCORE);
    }

    public int count() {
        return countPlayers() + DEALER_COUNT;
    }

    public int countPlayers() {
        return players.size();
    }

    public Name getOnePlayerName(int playerIndex) {
        return players.getOnePlayerName(playerIndex);
    }

    public Player getOnePlayer(int playerIndex) {
        return players.getOnePlayer(playerIndex);
    }

    public Player getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
