package blackjack.domain.scoreboard;

import blackjack.domain.card.Card;

import java.util.List;

//todo : GameResult와의 중복 제거
public class DealerGameResult {
    private final List<Card> resultCards;
    private final List<WinOrLose> winOrLoses;

    public DealerGameResult(List<Card> resultCards, List<WinOrLose> winOrLoses) {
        this.resultCards = resultCards;
        this.winOrLoses = winOrLoses;
    }

    public int calculateScore() {
        return resultCards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public List<WinOrLose> getWinOrLoses(){
        return winOrLoses;
    }
}
