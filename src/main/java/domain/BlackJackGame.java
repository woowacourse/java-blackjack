package domain;

import domain.deck.Deck;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Hand;
import domain.gamer.Player;
import domain.profit.Profit;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class BlackJackGame {

    private static final int INITIAL_CARD_COUNT = 2;
    private final Deck deck;

    public BlackJackGame(final Deck deck) {
        this.deck = deck;
    }

    public void dealInitialCards(final Gamer gamer) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            gamer.hit(deck.drawCard());
        }
    }

    public void hit(final Gamer gamer, final Function<Gamer, Boolean> wantHit, final BiConsumer<Gamer, Hand> onHit) {
        while (gamer.isRunning() && wantHit.apply(gamer)) {
            gamer.hit(deck.drawCard());
            onHit.accept(gamer, gamer.getHand());
        }
        if (gamer.isRunning()) {
            gamer.stay();
        }
    }

    public void hitUntilUnder16(final Gamer gamer, final Runnable onHit) {
        while (gamer.isRunning()) {
            gamer.hit(deck.drawCard());
            onHit.run();
        }
    }

    public Map<String, Double> calculatePlayersProfit(final Dealer dealer, final List<Player> players) {
        final Map<String, Double> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player.getDisplayName(), calculateProfit(dealer, player)));
        return result;
    }

    private Double calculateProfit(final Dealer dealer, final Player player) {
        final Profit profit = dealer.evaluateResult(player);
        return player.calculateProfit(profit);
    }

    public double calculateDealerProfit(final Map<String, Double> playersProfit) {
        return playersProfit.values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
