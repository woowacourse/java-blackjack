package blackjack.domain.participant;

import static blackjack.domain.participant.Dealer.DEALER_NAME;

import blackjack.domain.deck.Deck;
import java.util.List;

public class Players {

    private static final int MAX_PLAYERS_SIZE = 5;

    private final List<Player> players;

    private Players(List<String> playersName) {
        validateNumberOfPlayers(playersName);
        validateUniqueName(playersName);
        this.players = playersName.stream()
                .map(Player::new)
                .toList();
    }

    public static Players from(List<String> playersName) {
        return new Players(playersName);
    }

    public void draw(Deck deck) {
        for (Player player : players) {
            player.draw(deck.pop());
        }
    }

    public boolean isAllPlayersBurst() {
        int burstUserCount = (int) players.stream()
                .filter(Player::isBurst)
                .count();
        return players.size() == burstUserCount;
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    private void validateNumberOfPlayers(List<String> playersName) {
        if (playersName.isEmpty() || playersName.size() > MAX_PLAYERS_SIZE) {
            throw new IllegalArgumentException(String.format("플레이어는 1인 이상, %d인 이하입니다.", MAX_PLAYERS_SIZE));
        }
    }

    private void validateUniqueName(List<String> playersName) {
        if (playersName.stream().distinct().count() != playersName.size()) {
            throw new IllegalArgumentException("게임에 참가하는 플레이어끼리는 이름에 중복이 없어야합니다.");
        }
        if (playersName.stream().anyMatch(DEALER_NAME::equals)) {
            throw new IllegalArgumentException(String.format("플레이어 이름은 %s일 수 없습니다.", DEALER_NAME));
        }
    }

}
