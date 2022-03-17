package dto;

import domain.BlackJackResult;
import java.util.HashMap;
import java.util.Map;
import vo.Name;
import vo.Revenue;

public class BlackjackResultDto {
    private final Map<Name, Revenue> blackjackResult;

    public BlackjackResultDto(BlackJackResult blackJackResult) {
        this.blackjackResult = new HashMap<>(blackJackResult.getBlackjackResult());
    }

    public Map<Name, Revenue> getBlackjackResult() {
        return blackjackResult;
    }
}
