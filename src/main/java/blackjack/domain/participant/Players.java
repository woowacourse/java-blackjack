package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import java.util.List;
import java.util.stream.IntStream;

public class Players {

    private final List<Player> playerList;

    private Players(List<Player> playerList) {
        validate(playerList);
        this.playerList = playerList;
    }

    public static Players fromNameAndBettingAmounts(List<String> playerNames, List<Integer> playerBettingAmounts) {
        if (playerNames.size() != playerBettingAmounts.size()) {
            throw new IllegalArgumentException("플레이어의 수와 동일한 수의 배팅금이 필요합니.");
        }
        List<Player> players = IntStream.range(0, playerNames.size())
                .mapToObj(index -> Player.from(playerNames.get(index), playerBettingAmounts.get(index)))
                .toList();
        return new Players(players);
    }

    private void validate(List<Player> playerList) {
        long distinctCount = playerList.stream()
                .map(Player::getNickname)
                .distinct()
                .count();
        if (distinctCount != playerList.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public void distributeCards(Deck deck) {
        playerList.forEach(player -> player.drawInitialCards(deck));
    }

    public List<Player> getPlayers() {
        return List.copyOf(playerList);
    }

    public Player getDrawablePlayer() {
        return playerList.stream()
                .filter(Player::isDrawable)
                .findFirst()
                .orElse(null);
    }
}
