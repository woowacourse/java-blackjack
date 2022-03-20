package blackjack.domain.user;

import blackjack.domain.card.deck.Deck;
import java.util.List;

public class Players {

    private static final int MAX_PLAYER = 10;
    private static final String TOO_MANY_PLAYER_ERROR_MESSAGE = "참여할 수 있는 플레이어 수는 1명 이상 " + MAX_PLAYER + "명 이하만 가능합니다.";
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
        if (players.size() > MAX_PLAYER || players.isEmpty()) {
            throw new IllegalArgumentException(TOO_MANY_PLAYER_ERROR_MESSAGE);
        }
    }

    private void validateDuplicateName(List<Player> players) {
        int distinctNameCount = (int) players.stream()
                .map(player -> player.getName().get())
                .distinct()
                .count();

        if (players.size() != distinctNameCount) {
            throw new IllegalArgumentException(DUPLICATE_NAME_ERROR_MESSAGE);
        }
    }

    private void validateSameDealerName(List<Player> players) {
        boolean hasSameDealerName = players.stream()
                .anyMatch(player -> player.getName().get().equals(DEALER_NAME));

        if (hasSameDealerName) {
            throw new IllegalArgumentException(SAME_DEALER_NAME_ERROR_MESSAGE);
        }
    }

    public void takeInitHand(Deck deck) {
        for (Player player : players) {
            player.takeInitHand(deck);
        }
    }

    public boolean hasPlayerName(String name) {
        return players.stream()
                .map(User::getName)
                .map(UserName::get)
                .anyMatch(userName -> userName.equals(name));
    }

    public List<Player> get() {
        return players;
    }
}
