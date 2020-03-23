package blackjack.domain.user;

import blackjack.domain.deck.Deck;
import blackjack.domain.user.exception.PlayersException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        validateNames(players);

        this.players = players;
    }

    public static Players of(Map<String, String> playerProperties) {

        List<Player> players = playerProperties.entrySet().stream()
                .map(entry -> Player.of(entry.getKey(), entry.getValue()))
                .collect(collectingAndThen(toList(),
                        Collections::unmodifiableList));

        return new Players(players);
    }

    private void validateNames(List<Player> players) {
        boolean hasDealerName = players.stream()
                .map(player -> player.getName())
                .anyMatch(name -> name.equals(Dealer.NAME));

        if (hasDealerName) {
            throw new PlayersException("플레이어의 이름은 딜러일 수 없습니다.");
        }
    }

    public void drawCardsAtFirst(Deck deck) {
        for (Player player : players) {
            player.drawCardsAtFirst(deck);
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
