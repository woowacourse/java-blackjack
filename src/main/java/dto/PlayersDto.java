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
        this.playersHand = Map.copyOf(
                playersHand.entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                entry1 -> entry1.getKey().getName(),
                                entry1 -> List.copyOf(entry1.getValue())
                        ))
        );
        this.playersScore = Map.copyOf(
                playersScore.entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                entry -> entry.getKey().getName(),
                                Map.Entry::getValue
                        ))
        );
    }

    public Map<String, List<Card>> getPlayersHand() {
        return playersHand;
    }

    public Map<String, Integer> getPlayersScore() {
        return playersScore;
    }
}
