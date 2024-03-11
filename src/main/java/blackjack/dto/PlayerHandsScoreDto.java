package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.player.PlayerName;

public record PlayerHandsScoreDto(String name, HandsScoreDto handsScore) {

    public static PlayerHandsScoreDto of(final PlayerName name, final Hands hands) {
        return new PlayerHandsScoreDto(name.getName(), HandsScoreDto.from(hands));
    }
}
