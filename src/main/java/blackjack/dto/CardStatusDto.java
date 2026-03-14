package blackjack.dto;

import blackjack.domain.participant.Player;
import java.util.List;

public record CardStatusDto(
    String nickname,
    List<CardDto> cardDtos
) {

    public static CardStatusDto from(final Player player) {
        return new CardStatusDto(player.getNickname(), player.getAllCardNames());
    }

}
