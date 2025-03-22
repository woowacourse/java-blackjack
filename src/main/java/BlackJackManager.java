import bet.BetCenter;
import game.Deck;
import participant.Dealer;
import participant.Player;
import participant.Players;
import strategy.setting.DeckShuffleStrategy;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlackJackManager {
    public static final String HIT_COMMAND = "y";

    private final Deck deck;
    private final Players players;
    private final Dealer dealer;
    private final BetCenter betCenter;

    public BlackJackManager(List<String> names, Function<Players, BetCenter> betCenterProvider) {
        this.deck = new Deck(new DeckShuffleStrategy());
        this.players = Players.registerPlayers(names, deck);
        this.dealer = new Dealer(deck);
        this.betCenter = betCenterProvider.apply(players);
    }

    public void performPlayerTurn(Function<Player, String> hitDecisionProvider, Consumer<Player> onHitAction) {
        for (Player player : players.getPlayers()) {
            selectHitOrStand(player, hitDecisionProvider, onHitAction);
            onHitAction.accept(player);
        }
    }

    private void selectHitOrStand(Player player,
                                  Function<Player, String> hitDecisionProvider,
                                  Consumer<Player> onHitAction
    ) {
        while (hitDecisionProvider.apply(player).equals(HIT_COMMAND) && player.addOneCard(deck.drawOneCard())) {
            onHitAction.accept(player);
        }
    }

    public void performDealerTurn(Runnable onDealerHitAction) {
        while (dealer.shouldDealerHit()) {
            dealer.addOneCard(deck.drawOneCard());
        }
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public BetCenter getBetCenter() {
        return betCenter;
    }
}
