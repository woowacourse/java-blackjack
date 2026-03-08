package blackjack.dto;

import blackjack.domain.Player;

import java.util.List;

public record PlayerScoreResult(
        String name,
        List<CardInfo> cards,
        int score
) {
    public static PlayerScoreResult from(Player player) {
        List<CardInfo> cardInfos = player.getCards().stream()
                .map(CardInfo::from)
                .toList();
        return new PlayerScoreResult(player.name(), cardInfos, player.score());
    }
}
