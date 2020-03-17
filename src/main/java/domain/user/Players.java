package domain.user;

import domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public Players(List<String> names, List<Integer> betAmounts) {
        validateNames(names);
        validateBetAmounts(betAmounts);
        validateNamesSizeEqualToBetAmounts(names, betAmounts);

        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(names.get(i), betAmounts.get(i)));
        }
    }

    private void validateNames(List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("이름이 비어있습니다.");
        }
    }

    private void validateBetAmounts(List<Integer> betAmounts) {
        if (betAmounts == null || betAmounts.isEmpty()) {
            throw new IllegalArgumentException("입력된 배팅금액이 없습니다.");
        }
    }

    private void validateNamesSizeEqualToBetAmounts(List<String> names, List<Integer> betAmounts) {
        if (names.size() != betAmounts.size()) {
            throw new IllegalArgumentException("이름의 수와 배팅금액 수가 같아야 합니다.");
        }
    }

    public void receiveFirstCards(Deck deck) {
        for (Player player : players) {
            player.receiveFirstCards(deck);
        }
    }

    public List<String> getNames() {
        return players.stream().
            map(Player::getName)
            .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }
}
