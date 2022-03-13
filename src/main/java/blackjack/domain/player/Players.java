package blackjack.domain.player;

import blackjack.domain.card.Deck;
import java.util.List;
import java.util.Objects;

public class Players {

    private static final int MAX_GAMER = 10;
    private static final String TOO_MANY_GAMER_ERROR_MESSAGE = "최대 플레이어 수는 " + MAX_GAMER + "명 입니다.";
    private static final String DUPLICATE_NAME_ERROR_MESSAGE = "플레이어의 이름이 중복되었습니다.";
    private static final String SAME_DEALER_NAME_ERROR_MESSAGE = "플레이어의 이름가 딜러면 안된다.";
    private static final String DEALER_NAME = "딜러";

    private final List<Player> players;

    public Players(List<Player> players) {
        validateSize(players);
        validateDuplicateName(players);
        validateSameDealerName(players);

        this.players = players;
    }

    private void validateSize(List<Player> players) {
        if (players.size() > MAX_GAMER) {
            throw new IllegalArgumentException(TOO_MANY_GAMER_ERROR_MESSAGE);
        }
    }

    private void validateDuplicateName(List<Player> players) {
        int distinctNameCount = (int) players.stream()
                .map(gamer -> gamer.getName().get())
                .distinct()
                .count();

        if (players.size() != distinctNameCount) {
            throw new IllegalArgumentException(DUPLICATE_NAME_ERROR_MESSAGE);
        }
    }

    private void validateSameDealerName(List<Player> players) {
        boolean hasSameDealerName = players.stream()
                .anyMatch(gamer -> gamer.getName().get().equals(DEALER_NAME));

        if (hasSameDealerName) {
            throw new IllegalArgumentException(SAME_DEALER_NAME_ERROR_MESSAGE);
        }
    }

    public void dealCards(Deck deck) {
        for (Player player : players) {
            player.hit(deck.pick());
            player.hit(deck.pick());
        }
    }

    public List<Player> get() {
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Players)) {
            return false;
        }
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }

    @Override
    public String toString() {
        return "Players{" +
                "players=" + players +
                '}';
    }
}
