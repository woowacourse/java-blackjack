import bet.BetAmount;
import bet.BetCenter;
import game.Deck;
import participant.Dealer;
import participant.Player;
import participant.Players;
import strategy.setting.DeckShuffleStrategy;
import strategy.winning.BlackJackWinStrategy;
import strategy.winning.DealerBlackJackStrategy;
import strategy.winning.DrawStrategy;
import strategy.winning.LoseStrategy;
import strategy.winning.NormalWinStrategy;
import strategy.winning.WinningStrategy;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlackJackManager {
    private static final String HIT_COMMAND = "y";
    private static final List<WinningStrategy> strategies = List.of(
            new BlackJackWinStrategy(),
            new DealerBlackJackStrategy(),
            new NormalWinStrategy(),
            new DrawStrategy(),
            new LoseStrategy()
    );

    private final Deck deck;
    private final Players players;
    private final Dealer dealer;
    private final BetCenter betCenter;

    public BlackJackManager(List<String> names, Function<Players, Map<Player, BetAmount>> betProvider) {
        this.deck = new Deck(new DeckShuffleStrategy());
        this.players = Players.registerPlayers(names, deck);
        this.dealer = new Dealer(deck);
        this.betCenter = new BetCenter(betProvider.apply(players), strategies);
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

    public void performDealerTurn(Consumer<Dealer> onDealerHitAction) {
        while (dealer.shouldDealerHit()) {
            dealer.addOneCard(deck.drawOneCard());
            onDealerHitAction.accept(dealer);
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
