package domain.participant;

import domain.match.MatchResult;
import domain.money.Bet;
import domain.money.Money;

import java.util.Objects;

public class Player extends Participant {

    private final Name name;
    private final Bet bet;

    public Player(Name name, Bet bet) {
        this.name = name;
        this.bet = bet;
    }

    public Money applyMatchResultToBet(MatchResult matchResult) {
        return bet.calculateProfit(matchResult, isBlackJack());
    }

    public String getName() {
        return name.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
