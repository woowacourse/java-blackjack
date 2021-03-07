package blackjack.domain.participant;

import blackjack.domain.carddeck.CardDeck;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Players {

    public static final int MAX_PLAYER = 7;

    private final List<Player> players;

    public Players(final List<String> names) {
        this.players = convertToPlayers(names);
        validatePlayerCount(names);
        validateDuplicate(names);
    }

    private List<Player> convertToPlayers(final List<String> names) {
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(new Name(name)));
        }
        return players;
    }

    private void validatePlayerCount(final List<String> names) {
        if (names.size() > MAX_PLAYER) {
            throw new IllegalArgumentException("최대 참여 플레이어는 " + MAX_PLAYER + "명입니다.");
        }
    }

    private void validateDuplicate(final List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    public void drawCards(final CardDeck cardDeck) {
        for (Player player : this.players) {
            player.addCard(cardDeck.draw());
        }
    }

    public Player findPlayerByName(final Name name) {
        return this.players
            .stream()
            .filter(player -> player.equalsName(name))
            .findAny()
            .get();
    }

    public List<Player> toList() {
        return new ArrayList<>(this.players);
    }
}
