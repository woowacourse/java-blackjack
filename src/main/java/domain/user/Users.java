package domain.user;

import domain.Money;
import domain.card.Deck;
import domain.Result;

import java.util.ArrayList;
import java.util.List;

import static domain.Referee.getResult;
import static domain.RewardCalculator.calculate;

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

    public Money getDealerMoney() {
        Money money = Money.zero();

        for (Player player : players) {
            money = money.add(getPlayerMoney(player));
        }

        return money.multiply(-1);
    }

    private Money getPlayerMoney(Player player) {
        Result result = getUserResult(player);
        Money money = player.getMoney();

        return calculate(result, money);
    }

    public List<Player> players() {
        return new ArrayList<>(players);
    }

    public Dealer dealer() {
        return dealer;
    }
}
