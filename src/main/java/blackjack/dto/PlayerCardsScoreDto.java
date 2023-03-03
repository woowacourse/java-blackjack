package blackjack.dto;

import java.util.Map;

public class PlayerCardsScoreDto {

    private final Map<String, CardsScoreDto> playerNameToResult;

    public PlayerCardsScoreDto(final Map<String, CardsScoreDto> playerNameToResult) {
        this.playerNameToResult = playerNameToResult;
    }

    public Map<String, CardsScoreDto> getPlayerNameToResult() {
        return playerNameToResult;
    }
}
