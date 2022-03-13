package blackjack.domain;

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

    public List<Player> getValue() {
        return List.copyOf(value);
    }
}
