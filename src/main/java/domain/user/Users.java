package domain.user;

import domain.Money;
import domain.Referee;
import domain.Result;
import domain.card.Deck;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Users {
    private final List<Player> players;
    private final Dealer dealer;

    public Users(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void dealCardsFrom(Deck deck) {
        for (Player player : players) {
            player.addCard(deck.drawCard());
        }
        dealer.addCard(deck.drawCard());
    }

    public Result getUserResult(User user) {
        return Referee.getResult(user, dealer);
    }

    public Money getDealerProfit(Map<String, Money> profits) {
        Collection<Money> playersProfits = profits.values();

        Money money = playersProfits.stream()
                .reduce(Money.zero(), Money::add);

        return money.multiply(-1);
    }

    public Map<String, Money> getPlayersProfits(Users users) {
        return users.players().stream()
                .collect(Collectors.toUnmodifiableMap(
                        Player::getName,
                        users::calculateProfit));
    }

    public Money calculateProfit(Player player) {
        return player.calculateProfit(getUserResult(player));
    }

    public List<Player> players() {
        return Collections.unmodifiableList(players);
    }

    public Dealer dealer() {
        return dealer;
    }
}
