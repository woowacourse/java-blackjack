package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.result.ProfitResult;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {
    private static final String YES = "y";
    private static final String NO = "n";

    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    public BlackjackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
        this.deck = new Deck();
    }

    public static BlackjackGame generateByUser(List<String> names) {
        Dealer dealer = new Dealer();
        Players players = Players.of(names);
        return new BlackjackGame(dealer, players);
    }

    public void handOutInitialCards() {
        dealer.initializeCards(deck.popToInitialCards());
        players.getPlayers()
                .forEach(player -> player.initializeCards(deck.popToInitialCards()));
    }

    public void betByPlayer(String name, long moneyValue) {
        players.setUpBettingMoney(name, moneyValue);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public List<String> getPlayersName() {
        return players.getPlayers()
                .stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(dealer);
        users.addAll(players.getPlayers());
        return users;
    }

    public void proceedPlayersRound(String answer) {
        validateAnswer(answer);
        Player currentPlayer = players.getCurrentPlayer();
        if (YES.equals(answer)) {
            currentPlayer.hit(deck.popOne());
        }
        if (NO.equals(answer)) {
            currentPlayer.stay();
        }
    }

    public Player getCurrentPlayer() {
        return players.getCurrentPlayer();
    }

    private void validateAnswer(String answer) {
        if (YES.equals(answer) || NO.equals(answer)) {
            return;
        }
        throw new IllegalArgumentException("y, n로만 대답할 수 있습니다.");
    }

    public void hit(User user) {
        user.hit(deck.popOne());
    }

    public boolean isNotFinishPlayersRound() {
        return players.isRemainToProceedPlayers();
    }

    public int proceedDealerRound() {
        int roundCount = 0;
        while (dealer.isMustHit()) {
            dealer.hit(deck.popOne());
            roundCount++;
        }
        return roundCount;
    }

    public ProfitResult calculateResult() {
        return ProfitResult.generateByUsers(players, dealer);
    }

    public Map<Player, Long> getProfitsByPlayer() {
        return players.getPlayers()
                .stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> player.getProfit(dealer),
                        (p1, p2) -> p1,
                        LinkedHashMap::new));
    }

    public long getDealerProfit() {
        return players.getPlayers()
                .stream()
                .mapToLong(player -> player.getProfit(dealer) * -1)
                .sum();
    }
}
