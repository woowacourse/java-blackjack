package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Players {
    private static final int MAXIMUM_PLAYER_COUNTS = 7;
    private static final int START_INDEX = 0;
    private static final String INVALID_PLAYER_COUNTS = "게임 참가자의 수는 딜러 제외 최소 1명 최대 7명입니다.";
    private static final String INVALID_INPUT_COUNTS_EQUALITY = "입력된 참가자의 이름 개수와 배팅 금액 개수가 일치하지 않습니다.";

    private final List<Player> players;

    private Players(List<Player> players) {
        validatePlayerCounts(players);
        validateNameDuplication(players);
        this.players = players;
    }

    public static Players of(List<String> playerNames, List<Integer> bettingMoneys) {
        validateInputCountsEquality(playerNames, bettingMoneys);
        int playerCounts = playerNames.size();
        List<Player> players = IntStream.range(START_INDEX, playerCounts)
                .mapToObj(i -> new Player(playerNames.get(i), bettingMoneys.get(i)))
                .collect(Collectors.toList());
        return new Players(players);
    }

    private static void validateInputCountsEquality(List<String> playerNames, List<Integer> bettingMoneys) {
        if (playerNames.size() != bettingMoneys.size()) {
            throw new IllegalArgumentException(INVALID_INPUT_COUNTS_EQUALITY);
        }
    }

    private void validatePlayerCounts(List<Player> players) {
        if (players.isEmpty() || players.size() > MAXIMUM_PLAYER_COUNTS) {
            throw new IllegalArgumentException(INVALID_PLAYER_COUNTS);
        }
    }

    private void validateNameDuplication(List<Player> players) {
        int playerCounts = players.size();
        int distinctPlayerCounts = (int) players.stream()
                .map(Player::getName)
                .distinct()
                .count();
        if (playerCounts != distinctPlayerCounts) {
            throw new IllegalArgumentException("참가자들의 이름은 중복이 없어야 합니다.");
        }
    }

    public void receiveDefaultCards(CardDeck cardDeck) {
        players.forEach(player -> player.receiveCards(cardDeck.drawDefaultCards()));
    }

    public List<Player> toList() {
        return Collections.unmodifiableList(players);
    }
}
