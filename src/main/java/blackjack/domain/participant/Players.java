package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players fromPlayerNicknames(List<String> playerNicknames) {
        validate(playerNicknames);
        List<Player> players = playerNicknames.stream()
                .map(Player::new)
                .toList();
        return new Players(players);
    }

    private static void validate(List<String> playerNicknames) {
        if (playerNicknames.isEmpty()) {
            throw new IllegalArgumentException("한 명 이상의 플레이어 닉네임을 입력해주세요");
        }
        long distinctCount = playerNicknames.stream()
                .distinct()
                .count();
        if (distinctCount != playerNicknames.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public void distributeCards(Deck deck) {
        players.forEach(player -> player.drawInitialCards(deck));
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
