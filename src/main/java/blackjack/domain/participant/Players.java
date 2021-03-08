package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players of(final List<String> playerName) {
        validateDuplicate(playerName);
        final List<Player> players = playerName.stream()
                .map(name -> new Player(name))
                .collect(Collectors.toList());
        return new Players(players);
    }

    private static void validateDuplicate(final List<String> playerName) {
        final int distinctName = (int) playerName.stream().distinct().count();
        if (distinctName != playerName.size()) {
            throw new IllegalArgumentException("입력된 플레이어의 이름이 중복됩니다.");
        }
    }

    public void receiveInitialCard(final CardDeck cardDeck) {
        for (Player player : players) {
            player.receiveInitialCard(cardDeck);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
