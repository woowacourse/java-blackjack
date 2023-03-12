package domain.user;

import domain.Money;
import domain.card.Deck;
import domain.Result;

import java.util.ArrayList;
import java.util.List;

import static domain.Referee.getResult;

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
        return getResult(user, dealer);
    }

    public Money getDealerProfit() {
        Money money = Money.zero();

        for (Player player : players) {
            money = money.add(calculateProfit(player));
        }

        return money.multiply(-1);
    }

    public Money calculateProfit(Player player) {
        return player.calculateProfit(getUserResult(player));
    }

    public List<Player> players() {
        return new ArrayList<>(players);
    }

    public Dealer dealer() {
        return dealer;
    }
}
