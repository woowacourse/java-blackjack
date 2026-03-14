package blackjack.domain.wager;

import blackjack.domain.money.Money;
import blackjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.List;

public class Wagers {

    private static final String NOT_EXISTS_WAGER_OF_PLAYER_ERROR_MESSAGE = "사용자에 맞는 베팅 금액이 존재하지 않습니다.";

    private final List<Wager> wagers = new ArrayList<>();

    public void add(Wager wager) {
        wagers.add(wager);
    }

    public List<Player> allPlayer() {
        return wagers.stream().map(Wager::player).toList();
    }

    public Money wagerOf(Player player) {
        return wagers.stream().filter(wager -> wager.player().equals(player)).findFirst().map(Wager::money).orElseThrow(() -> new IllegalArgumentException(NOT_EXISTS_WAGER_OF_PLAYER_ERROR_MESSAGE));
    }
}
