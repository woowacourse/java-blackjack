package blackjack.domain.dto;

import blackjack.domain.card.Hand;
import java.util.Map;

public class ResponseCardResultDto {

    private Map<String, Hand> cardResult;

    public ResponseCardResultDto(Map<String, Hand> cardResult) {
        this.cardResult = cardResult;
    }

    public Map<String, Hand> getCardResult() {
        return cardResult;
    }
}
