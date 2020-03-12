package domain.result;

import java.util.List;
import java.util.stream.Collectors;

public class AllBlackJackResults {
    List<BlackJackResult> blackJackResults;

    public AllBlackJackResults(List<BlackJackResult> blackJackResults) {
        this.blackJackResults = blackJackResults;
    }

    public List<PlayerResult> extractPlayerResults() {
        return blackJackResults.stream()
                .filter(result -> result instanceof PlayerResult)
                .map(result -> (PlayerResult) result)
                .collect(Collectors.toList());
    }

    public DealerResult extractDealerResult() {
        return blackJackResults.stream()
                .filter(result -> result instanceof DealerResult)
                .map(result -> (DealerResult) result)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("딜러의 결과가 존재하지 않습니다."));
    }
}
