package blackjack.domain;

import static blackjack.Application.playing;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> value;

    public Players(List<Player> players) {
        this.value = new ArrayList<>(players);
        validate();
    }

    private void validate() {
        if (this.value.isEmpty()) {
            throw new IllegalArgumentException("게임 진행을 위해서는 최소 플레이어 1명이 필요합니다.");
        }
    }

    public void createPlayerResult(Dealer dealer) {
        value.forEach(player -> player.changeByBettingMoneyResult(dealer));
    }

    public void createDealerResult(Dealer dealer) {
        value.forEach(player -> dealer.changeByBettingMoneyResult(player));
    }

    public void playPlayers(Deck deck) {
        if (!hasBlackJack()) {
            value.forEach(player -> playing(deck, player));
        }
    }

    public boolean hasBlackJack() {
        return value.stream().anyMatch(Player::isBlackJack);
    }

    public List<Player> getValue() {
        return List.copyOf(value);
    }
}
