package blackjack.domain.user;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import blackjack.domain.card.Deck;
import blackjack.domain.money.Money;

public class Users {

    private static final int INIT_DRAW_SIZE = 2;

    private final List<Player> players;
    private final Dealer dealer;

    private Users(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static Users of(Map<String, String> inputNameAndMoney, Deck deck) {
        List<Player> players = inputNameAndMoney.entrySet()
            .stream()
            .map(nameMoneyEntry -> Player.of(nameMoneyEntry.getKey(), nameMoneyEntry.getValue()))
            .collect(toList());
        Dealer dealer = new Dealer();
        return new Users(players, dealer);
    }

    public void drawInitialCardsPerUser(Deck deck) {
        for (Player player : players) {
            drawInitialCards(player, deck);
        }
        drawInitialCards(dealer, deck);
    }

    private void drawInitialCards(User user, Deck deck) {
        for (int i = 0; i < INIT_DRAW_SIZE; i++) {
            user.hit(deck.drawCard());
        }
    }

    public boolean isDealerBlackjack() {
        return dealer.isBlackjack();
    }

    public Map<Player, Money> createPlayerProfit() {
        return players.stream()
            .collect(Collectors.toMap(
                Function.identity(),
                player -> player.calculateProfit(dealer)
            ));
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public Dealer getDealer() {
        return dealer;
    }
}
