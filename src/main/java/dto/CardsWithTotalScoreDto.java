package dto;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CardsWithTotalScoreDto {
    private final Map<String, List<Card>> nameWithCards;
    private final int totalScore;

    public CardsWithTotalScoreDto(final Map<String, List<Card>> nameWithCards, final int totalScore) {
        this.nameWithCards = nameWithCards;
        this.totalScore = totalScore;
    }

    public static List<CardsWithTotalScoreDto> generateDtos(final Dealer dealer, final Players players) {
        final List<CardsWithTotalScoreDto> dtos = new ArrayList<>();
        dtos.add(new CardsWithTotalScoreDto(dealer.getNameWithCards(), dealer.getTotalScore()));
        for (final Player player : players.getPlayers()) {
            dtos.add(new CardsWithTotalScoreDto(player.getNameWithCards(), player.getTotalScore()));
        }
        return dtos;
    }

    public Map<String, List<Card>> getNameWithCards() {
        return new HashMap<>(nameWithCards);
    }

    public int getTotalScore() {
        return totalScore;
    }
}
