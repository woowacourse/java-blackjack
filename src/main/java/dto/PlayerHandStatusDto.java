package dto;

import domain.participant.Player;
import domain.participant.PlayerName;

import java.util.List;

public record PlayerHandStatusDto(PlayerName playerName, List<PlayingCardDto> playingCards, int playingCardSum) {
    public static PlayerHandStatusDto of(Player player) {
        List<PlayingCardDto> playingCars = player.getHandCards().stream()
                .map(PlayingCardDto::of)
                .toList();
        return new PlayerHandStatusDto(player.getPlayerName(), playingCars, player.getHandSum());
    }
}
