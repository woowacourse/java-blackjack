package domain.user;

import static domain.money.GameResult.LOSE;
import static domain.money.GameResult.WIN;

import domain.Deck;
import domain.money.GameResult;
import domain.money.PlayersMoney;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameUsers {
    private final PlayersMoney playersMoney;
    private final Dealer dealer;

    public GameUsers(PlayersMoney playersMoney, Deck deck) {
        this.playersMoney = playersMoney;
        this.dealer = new Dealer();
        addStartCards(deck);
    }

    private void addStartCards(Deck deck) {
        playersMoney.addStartCards(deck);
        dealer.addStartCards(deck);
    }

    public Map<Player, GameResult> generatePlayersResult() {
        return playersMoney.getPlayers()
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        this::generatePlayerResult,
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
    }

    private GameResult generatePlayerResult(Player player) {
        if (player.busted() || isDealerBlackjackOnly(player)) {
            return LOSE;
        }
        if (dealer.busted() || playerBlackjackOnly(player)) {
            return WIN;
        }
        return GameResult.compare(player.sumHand(), dealer.sumHand());
    }

    private boolean isDealerBlackjackOnly(Player player) {
        return !player.isBlackjack() && dealer.isBlackjack();
    }

    private boolean playerBlackjackOnly(Player player) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    public List<Player> getPlayers() {
        return playersMoney.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
