package blackjack.domain.participant;

import blackjack.domain.card.CardPickMachine;
import blackjack.domain.strategy.NumberGenerator;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    private static final String NO_PLAYER_ERROR = "[ERROR] 플레이어는 1명 이상이여야 합니다.";

    private final List<Player> players;

    public Players(Map<String, Long> playersInfo) {
        validatePlayers(playersInfo);

        this.players = playersInfo.keySet().stream()
                .map(name -> new Player(name, playersInfo.get(name)))
                .collect(Collectors.toList());
    }

    private void validatePlayers(Map<String, Long> playersInfo) {
        if (playersInfo.size() == 0) {
            throw new IllegalArgumentException(NO_PLAYER_ERROR);
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void addCards(CardPickMachine cardPickMachine, NumberGenerator numberGenerator) {
        players.forEach(player ->
                player.addCard(cardPickMachine.pickCard(numberGenerator)));
    }
}
