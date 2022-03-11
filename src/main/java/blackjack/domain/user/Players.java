package blackjack.domain.user;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Rule;
import blackjack.domain.card.Deck;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> playerNames) {
        validateDuplication(playerNames);

        List<Player> players = playerNames.stream()
                .map(Player::from)
                .collect(toList());

        return new Players(players);
    }

    private static void validateDuplication(List<String> playerNames) {
        HashSet<String> hashSet = new HashSet<>(playerNames);

        if (hashSet.size() < playerNames.size()) {
            throw new IllegalArgumentException("참여자 이름이 중복되면 안됩니다.");
        }
    }

    public void drawInitCards(Deck deck) {
        for (Player player : players) {
            player.drawInitCards(deck);
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void calculate(Rule rule) {
        for (Player player : players) {
            player.calculate(rule);
        }
    }
}
