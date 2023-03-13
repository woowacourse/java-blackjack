package model.user;

import java.util.ArrayList;
import java.util.List;
import model.card.Deck;
import model.money.Bet;

public class Participants {

    private final List<Player> players;
    private final Dealer dealer;

    private Participants(final List<Player> playersName, final Dealer dealer) {
        this.players = playersName;
        this.dealer = dealer;
    }

    public static Participants of(final List<Name> playersName, final List<Bet> bets, final Dealer dealer) {
        return new Participants(createPlayers(playersName, bets), dealer);
    }

    private static List<Player> createPlayers(List<Name> playersName, List<Bet> bets) {
        final List<Player> players = new ArrayList<>();

        for (int i = 0, size = playersName.size(); i < size; i++) {
            players.add(new Player(playersName.get(i), bets.get(i)));
        }

        return players;
    }

    public void receiveInitialCards(final Deck deck) {
        players.forEach(player -> player.receiveInitialCards(deck));
        dealer.receiveInitialCards(deck);
    }

    public long getTotalProfits() {
        return players.stream()
                .mapToLong(this::createTotalProfit)
                .sum();
    }

    private long createTotalProfit(final Player player) {
        final GameState gameState = player.judgeResult(dealer);
        return gameState.calculateMoney(player.getBetMoney());
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public Dealer getDealer() {
        return this.dealer;
    }
}
