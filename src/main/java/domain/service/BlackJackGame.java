package domain.service;

import domain.model.Dealer;
import domain.model.Player;
import domain.model.Players;
import domain.vo.Profit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BlackJackGame {

    private final CardDistributor cardDistributor;
    private final ProfitCalculator profitCalculator;

    public BlackJackGame(final CardDistributor cardDistributor, final ProfitCalculator profitCalculator) {
        this.cardDistributor = cardDistributor;
        this.profitCalculator = profitCalculator;
    }

    public Players makePlayers(final List<String> names, final List<Double> bets) {
        return Players.from(names, bets);
    }

    public boolean giveCard(final Player player) {
        if (player.canReceiveCard()) {
            cardDistributor.giveCard(player);
            return true;
        }
        return false;
    }

    public boolean giveCard(final Dealer dealer) {
        if (dealer.canReceiveCard()) {
            cardDistributor.giveCard(dealer);
            return true;
        }
        return false;
    }

    public void giveInitCards(final Dealer dealer, final Players players) {
        cardDistributor.giveInitCards(dealer);
        cardDistributor.giveInitCards(players);
    }

    public void setPlayers(final Players players, final Player player, final int index) {
        players.set(index, player);
    }

    public List<Profit> calculatePlayersProfit(final Players players, final Dealer dealer) {
        return IntStream.range(0, players.count())
            .mapToObj(index -> profitCalculator.calculatePlayerProfit(players.get(index), dealer))
            .collect(Collectors.toList());
    }

    public Profit calculateDealerProfit(final List<Profit> playerProfits) {
        return Profit.makeDealerProfitFrom(playerProfits);
    }
}
