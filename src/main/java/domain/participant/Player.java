package domain.participant;

import domain.MatchResult;

import java.util.Objects;

public class Player extends Participant {

    private static final int MAX_NAME_LENGTH = 8;

    private final String name;
    private Bet bet;

    public Player(String name) {
        validateNameLength(name);
        this.name = name;
    }

    public void placeBet(int amount) {
        this.bet = Bet.of(amount);
    }

    public int applyMatchResultToBet(MatchResult matchResult) {
        return bet.calculateProfit(matchResult, isBlackJack());
    }

    private void validateNameLength(String name) {
        if (name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("잘못된 이름: " + name + " (플레이어 이름은 1자 이상 8자 이하여야 합니다.)");
        }
    }

    public String getName() {
        return name;
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
