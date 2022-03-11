package blackjack.dto;

import blackjack.domain.BlackJackResult;
import java.util.List;

public class BlackJackResultDto {

    private final List<String> dealerResult;
    private final List<String> gamblerResult;

    public BlackJackResultDto(final List<String> dealerResult, final List<String> gamblerResult) {
        this.dealerResult = dealerResult;
        this.gamblerResult = gamblerResult;
    }

    public static BlackJackResultDto from(final BlackJackResult result) {
        return new BlackJackResultDto(result.getDealerResult(), result.getGamblerResult());
    }

    public List<String> getDealerResult() {
        return dealerResult;
    }

    public List<String> getGamblerResult() {
        return gamblerResult;
    }
}
