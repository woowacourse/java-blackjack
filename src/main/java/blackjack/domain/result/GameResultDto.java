package blackjack.domain.result;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.Map;

public class GameResultDto {

    private final List<Card> dealerCards;
    private final int dealerSum;
    private final Map<MatchResult, Integer> dealerResult;
    private final List<PlayerResultDto> playersResultMap;

    public GameResultDto(List<Card> dealerCards, int dealerSum, Map<MatchResult, Integer> dealerResult,
            List<PlayerResultDto> playersResults) {
        this.dealerCards = dealerCards;
        this.dealerSum = dealerSum;
        this.dealerResult = dealerResult;
        this.playersResultMap = playersResults;
    }

    public List<Card> getDealerCards() {
        return dealerCards;
    }

    public int getDealerSum() {
        return dealerSum;
    }

    public Map<MatchResult, Integer> getDealerResult() {
        return dealerResult;
    }

    public List<PlayerResultDto> getPlayersResults() {
        return playersResultMap;
    }
}
