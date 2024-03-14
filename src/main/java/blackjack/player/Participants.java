package blackjack.player;

import blackjack.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Participants {

    private static final int MIN_PLAYER_COUNT = 1;
    private static final int MAX_PLAYER_COUNT = 10;

    private final List<Player> players;

    private Participants(List<Player> players) {
        this.players = players;
    }

    public static Participants create(List<String> playerNames) {
        validatePlayers(playerNames);

        ArrayList<Player> players = new ArrayList<>();
        playerNames.stream()
                .map(Participant::new)
                .forEach(players::add);
        return new Participants(players);
    }

    private static void validatePlayers(List<String> playerNames) {
        validateNotNull(playerNames);
        validateSize(playerNames);
        validateUniqueNames(playerNames);
    }

    private static void validateNotNull(List<String> playerNames) {
        if (playerNames == null) {
            throw new IllegalArgumentException("[ERROR] 플레이어로 null이 전달되었습니다.");
        }
    }

    private static void validateSize(List<String> playerNames) {
        if (playerNames.size() < MIN_PLAYER_COUNT || playerNames.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 수는 1명 이상 10명 이하여야 합니다.");
        }
    }

    private static void validateUniqueNames(List<String> playerNames) {
        int distinctNameCount = (int) playerNames.stream()
                .distinct()
                .count();
        if (distinctNameCount != playerNames.size()) {
            throw new IllegalArgumentException("[ERROR] 이름은 중복될 수 없습니다.");
        }
    }

    public void drawCardsForAll(Supplier<Card> cardSupplier, int amount) {
        players.forEach(player -> player.drawCards(cardSupplier, amount));
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
