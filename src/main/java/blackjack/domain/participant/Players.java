package blackjack.domain.participant;

import blackjack.domain.money.Money;
import java.util.ArrayList;
import java.util.List;

public class Players {

    private static final String NOT_FOUND_PLAYER_EXCEPTION_MESSAGE = "플레이어를 찾을 수 없습니다.";
    private final List<Player> value = new ArrayList<>();

    public void add(Player player) {
        validateNotDuplicateName(player.getName());
        value.add(player);
    }

    private void validateNotDuplicateName(String playerName) {
        long count = value.stream()
                .filter(player -> player.getName().equals(playerName))
                .count();

        if (count > 0) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public Money calculateProfit(Player player, Dealer dealer) {
        Player foundPlayer = value.stream()
                .filter(it -> it.equals(player))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_PLAYER_EXCEPTION_MESSAGE));

        return foundPlayer.calculateProfit(dealer);
    }

    public Money calculateTotalProfit(Dealer dealer) {
        Money totalProfit = Money.from(0);
        for (Player player : value) {
            totalProfit = totalProfit.add(player.calculateProfit(dealer));
        }

        return totalProfit;
    }

    public List<Player> getValue() {
        return List.copyOf(value);
    }

    @Override
    public String toString() {
        return "Players{" +
                "value=" + value +
                '}';
    }
}
