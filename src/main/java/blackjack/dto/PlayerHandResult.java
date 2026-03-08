package blackjack.dto;

import blackjack.domain.Player;

import java.util.List;

public record PlayerHandResult(
        String name,
        List<CardInfo> cards
) {
    public static PlayerHandResult from(Player player) {
        List<CardInfo> cardInfos = player.getCards().stream()
                .map(CardInfo::from)
                .toList();
        return new PlayerHandResult(player.name(), cardInfos);
    }
}
