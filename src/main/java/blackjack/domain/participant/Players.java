package blackjack.domain.participant;

import blackjack.domain.Deck;
import java.util.List;
import java.util.function.Consumer;

public class Players {

    private static final int MAX_PLAYERS_SIZE = 4;

    private final List<Player> players;

    private Players(List<Player> players) {
        validateSize(players);
        validateDistinct(players);
        this.players = players;
    }

    public static Players from(List<String> names) {
        List<Player> players = names.stream()
                .map(Player::from)
                .toList();
        return new Players(players);
    }

    private void validateSize(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("최소 한 명의 플레이어가 있어야 합니다.");
        }
        if (players.size() > MAX_PLAYERS_SIZE) {
            throw new IllegalArgumentException("최대 4명의 플레이어만 참여 가능합니다.");
        }
    }

    private void validateDistinct(List<Player> players) {
        if (isDuplicated(players)) {
            throw new IllegalArgumentException("중복된 이름을 사용할 수 없습니다.");
        }
    }

    private boolean isDuplicated(List<Player> players) {
        return players.size() != players.stream().distinct().count();
    }

    public void drawStartCards(Deck deck) {
        for (Player player : players) {
            player.drawStartCards(deck);
        }
    }

    //TODO 현재 얘는 테스트만을 위해서 임시로 작성된 코드
    List<Player> getPlayers() {
        return players;
    }

    public void play(Consumer<Player> playTurn) {
        for (Player player : players) {
            playTurn.accept(player);
        }
    }
}
