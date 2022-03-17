package blackjack.domain.participant;

import blackjack.domain.card.CardPickMachine;
import blackjack.domain.strategy.NumberGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private static final String DUPLICATED_ERROR = "[ERROR] 이름은 중복될 수 없습니다.";
    private static final String NO_PLAYER_ERROR = "[ERROR] 플레이어는 1명 이상이여야 합니다.";

    private final List<Player> players = new ArrayList<>();

    public Players(List<String> playerNames) {
        validateNames(playerNames);
        playerNames.forEach(name -> players.add(new Player(name)));
    }

    private void validateNames(List<String> playerNames) {
        if (playerNames.size() == 0) {
            throw new IllegalArgumentException(NO_PLAYER_ERROR);
        }

        List<String> duplicatedChecker = playerNames.stream().distinct().collect(Collectors.toList());
        if (duplicatedChecker.size() != playerNames.size()) {
            throw new IllegalArgumentException(DUPLICATED_ERROR);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addCards(CardPickMachine cardPickMachine, NumberGenerator numberGenerator) {
        players.forEach(player ->
                player.addCard(cardPickMachine.pickCard(numberGenerator)));
    }
}
