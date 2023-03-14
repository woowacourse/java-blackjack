package domain.service;

import domain.model.Dealer;
import domain.model.Participant;
import domain.model.Player;
import domain.model.Players;
import domain.vo.Profit;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final CardDistributor cardDistributor;
    private final ProfitCalculator profitCalculator;
    private final Dealer dealer;
    private Players players;

    public BlackJackGame(final CardDistributor cardDistributor, final ProfitCalculator profitCalculator) {
        this.cardDistributor = cardDistributor;
        this.profitCalculator = profitCalculator;
        dealer = Dealer.withEmptyCards();
    }

    public void makePlayers(final List<String> names, final List<Double> bets) {
        this.players = Players.from(names, bets);
    }

    public void giveInitCards() {
        cardDistributor.giveInitCards(dealer);
        cardDistributor.giveInitCards(players);
    }

    public boolean giveCard(final Participant participant) {
        if (participant.canReceiveCard()) {
            cardDistributor.giveCard(participant);
            return true;
        }
        return false;
    }

    public List<Profit> calculatePlayersProfit(final List<Player> players, final Dealer dealer) {
        return players.stream()
            .map(player -> profitCalculator.calculatePlayerProfit(player, dealer))
            .collect(Collectors.toList());
    }

    public Profit calculateDealerProfit(final List<Profit> playerProfits) {
        return Profit.makeDealerProfitFrom(playerProfits);
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<String> getPlayerNames() {
        return players.getNames();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setPlayers(final int index, final Player player) {
        players.set(index, player);
    }
}
