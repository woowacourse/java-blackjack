package blackjack.domain.result;

import blackjack.domain.card.Card;
import java.util.List;

public class GameResult {

    private final List<Card> dealerCards;
    private final int dealerSum;
    private final List<WinOrLose> dealerResult;
    private final List<PlayerResult> playersResultMap;

    public GameResult(List<Card> dealerCards, int dealerSum, List<WinOrLose> dealerResult,
            List<PlayerResult> playersResults) {
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

    public List<WinOrLose> getDealerResult() {
        return dealerResult;
    }

    public List<PlayerResult> getPlayersResults() {
        return playersResultMap;
    }
}
