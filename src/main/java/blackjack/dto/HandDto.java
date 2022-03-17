package blackjack.dto;

import blackjack.model.player.Player;
import java.util.List;

public final class HandDto {
    private final List<TrumpCardDto> cards;

    private HandDto(List<TrumpCardDto> cards) {
        this.cards = cards;
    }

    public static HandDto from(Player player) {
        return new HandDto(TrumpCardDto.from(player.getHand()));
    }

    public List<TrumpCardDto> getCards() {
        return cards;
    }
}
