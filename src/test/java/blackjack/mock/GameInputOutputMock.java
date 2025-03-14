package blackjack.mock;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayerProfit;
import blackjack.domain.io.GameInputOutput;
import blackjack.domain.value.BettingAmount;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameInputOutputMock extends GameInputOutput {

    private List<Card> initialDealerHand;
    private Map<String, List<Card>> initialPlayerHands;
    private List<Card> finalDealerHand;
    private Map<String, List<Card>> finalPlayerHands;
    private List<PlayerProfit> playerProfits;

    public GameInputOutputMock() {
        super(null, null, null,
                null, null, null, null);
    }

    @Override
    public void printInitialHands(Dealer dealer, List<Player> players) {
        initialDealerHand = dealer.getHand().stream().toList();
        initialPlayerHands = new HashMap<>();
        players.forEach(
                player -> initialPlayerHands.put(player.getNickname(), player.getHand().stream().toList()));
    }

    @Override
    public boolean readIngWannaHit(String nickname) {
        return true;
    }

    @Override
    public BettingAmount readBettingAmount(String nickname) {
        return new BettingAmount(10000);
    }

    @Override
    public void printingHitResult(Player player) {
    }

    @Override
    public void printDealerDrawing(int count) {
    }

    @Override
    public void printFinalHands(Dealer dealer, List<Player> players) {
        finalDealerHand = dealer.getHand().stream().toList();
        finalPlayerHands = new HashMap<>();
        players.forEach(
                player -> finalPlayerHands.put(player.getNickname(), player.getHand().stream().toList()));
    }

    @Override
    public void printPlayerProfits(List<PlayerProfit> playerProfits) {
        this.playerProfits = playerProfits;
    }

    public List<Card> getInitialDealerHand() {
        return initialDealerHand;
    }

    public Map<String, List<Card>> getInitialPlayerHands() {
        return initialPlayerHands;
    }

    public List<Card> getFinalDealerHand() {
        return finalDealerHand;
    }

    public Map<String, List<Card>> getFinalPlayerHands() {
        return finalPlayerHands;
    }

    public List<PlayerProfit> getPlayerProfits() {
        return playerProfits;
    }
}
