package first.domain.result;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AllBlackJackResults {
    private final List<BlackJackResult> blackJackResults;

    public AllBlackJackResults(List<BlackJackResult> blackJackResults) {
        this.blackJackResults = blackJackResults;
    }

    public List<PlayerResult> extractPlayerResults() {
        List<PlayerResult> playerResults = blackJackResults.stream()
                .filter(result -> result instanceof PlayerResult)
                .map(result -> (PlayerResult) result)
                .collect(Collectors.toList());

        return Collections.unmodifiableList(playerResults);
    }

    public DealerResult extractDealerResult() {
        return blackJackResults.stream()
                .filter(result -> result instanceof DealerResult)
                .map(result -> (DealerResult) result)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("딜러의 결과가 존재하지 않습니다."));
    }
}
