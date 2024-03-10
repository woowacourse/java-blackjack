package dto;

import domain.participant.Player;
import domain.participant.PlayerName;
import domain.participant.Score;

import java.util.List;

public record PlayerHandStatusDto(PlayerName playerName, List<PlayingCardDto> playingCards, Score playerTotalScore) {
    public static PlayerHandStatusDto of(final Player player) {
        List<PlayingCardDto> playingCars = player.getHandCards()
                .stream()
                .map(PlayingCardDto::of)
                .toList();
        return new PlayerHandStatusDto(player.getPlayerName(), playingCars, player.getTotalScore());
    }
}
