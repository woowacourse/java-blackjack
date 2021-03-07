package blackjack.domain.result;

import blackjack.domain.card.Card;
import java.util.List;

public class GameResultDto {

    private final List<Card> dealerCards;
    private final int dealerSum;
    private final List<MatchResult> dealerResult;
    private final List<PlayerResultDto> playersResultMap;

    public GameResultDto(List<Card> dealerCards, int dealerSum, List<MatchResult> dealerResult,
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

    public List<MatchResult> getDealerResult() {
        return dealerResult;
    }

    public List<PlayerResultDto> getPlayersResults() {
        return playersResultMap;
    }
}
