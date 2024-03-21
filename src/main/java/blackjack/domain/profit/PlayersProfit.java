package blackjack.domain.profit;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerName;
import blackjack.domain.participant.PlayerNames;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlayersProfit {

    private static final Profit INITIAL_PROFIT = new Profit(new BigDecimal(0));

    private final Map<Player, Profit> profits;

    public PlayersProfit(Map<Player, Profit> profits) {
        this.profits = profits;
    }

    public static PlayersProfit create(PlayerNames playerNames, Function<PlayerName, String> receiveBetAmount) {
        Map<Player, Profit> playerBetAmounts = playerNames.names().stream()
                .map(name -> {
                    BetAmount betAmount = new BetAmount(receiveBetAmount.apply(name));
                    return Map.entry(new Player(name), new Profit(betAmount));
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (existing, replacement) -> existing, LinkedHashMap::new));

        return new PlayersProfit(playerBetAmounts);
    }

    public void deal(CardDeck cardDeck) {
        profits.keySet().forEach(player -> player.deal(cardDeck));
    }

    public void draw(Consumer<Player> drawToPlayer) {
        profits.keySet().forEach(drawToPlayer);
    }

    public void calculateProfit(Dealer dealer) {
        profits.forEach((player, betAmount) -> {
            double multiplier = PlayerResult.determineMultiplier(player, dealer);
            Profit profit = betAmount.multiply(multiplier);
            profits.put(player, profit);
        });
    }

    public Profit dealerProfit() {
        return playersProfit().reverseSign();
    }

    private Profit playersProfit() {
        return profits.values().stream()
                .reduce(INITIAL_PROFIT, Profit::add);
    }

    public List<Player> players() {
        return profits.keySet().stream().toList();
    }

    public Map<Player, Profit> getProfits() {
        return Map.copyOf(profits);
    }
}
