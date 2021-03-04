package blackjack.domain.result;

import blackjack.domain.card.Card;
import java.util.List;

public class GameResult {

    //3다이아몬드, 9클로버, 8다이아몬드
    private final List<Card> dealerCards;
    //결과: 20
    private final int dealerSum;
    //1승 1패
    private final List<WinOrLose> dealerResult;
    //2하트, 8스페이드, A클로버
    //21
    //승
    private final List<PlayerResult> playersResultMap;

    public GameResult(List<Card> dealerCards, int dealerSum, List<WinOrLose> dealerResult,
            List<PlayerResult> playersResultMap) {
        this.dealerCards = dealerCards;
        this.dealerSum = dealerSum;
        this.dealerResult = dealerResult;
        this.playersResultMap = playersResultMap;
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
