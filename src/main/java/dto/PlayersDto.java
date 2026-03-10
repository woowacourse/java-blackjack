package dto;

import domain.card.Card;
import domain.participant.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayersDto {

    private final Map<String, List<Card>> playersHand;
    private final Map<String, Integer> playersScore;

    public PlayersDto(Map<Player, List<Card>> playersHand, Map<Player, Integer> playersScore) {
        this.playersHand = convertPlayerKeyToName(playersHand);
        this.playersScore = convertPlayerKeyToName(playersScore);
    }

    private <T> Map<String, T> convertPlayerKeyToName(Map<Player, T> map) {
        return map.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        Map.Entry::getValue
                ));
    }

    public Map<String, List<Card>> getPlayersHand() {
        return playersHand;
    }

    public Map<String, Integer> getPlayersScore() {
        return playersScore;
    }
}
