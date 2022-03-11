package blackjack.dto;

import blackjack.domain.BlackJackResult;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackResultDto {

    private static final String NAME_DELIMITER = ": ";

    private final List<String> dealerResult;
    private final List<String> gamblerResult;

    public BlackJackResultDto(final List<String> dealerResult, final List<String> gamblerResult) {
        this.dealerResult = dealerResult;
        this.gamblerResult = gamblerResult;
    }

    public static BlackJackResultDto from(final BlackJackResult result) {
        return new BlackJackResultDto(getDealerResult(result), getGamblerResult(result));
    }

    private static List<String> getDealerResult(final BlackJackResult result) {
        return result.getDealerResult().entrySet()
            .stream()
            .map(it -> it.getValue() + it.getKey().getResult())
            .collect(Collectors.toList());
    }

    private static List<String> getGamblerResult(final BlackJackResult result) {
        return result.getGamblerResult().entrySet()
            .stream()
            .map(it -> PlayerDto.from(it.getKey()).getName() + NAME_DELIMITER + it.getValue().getResult())
            .collect(Collectors.toList());
    }
    public List<String> getDealerResult() {
        return dealerResult;
    }

    public List<String> getGamblerResult() {
        return gamblerResult;
    }
}
