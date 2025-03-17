package view;

import card.Card;
import card.GameScore;
import java.util.List;
import participant.Name;
import result.Profit;

public final class GameResultView extends BlackjackView {
    public String getFinalScore(final Name name, final List<Card> cards, final GameScore score) {
        return String.format("%s - 결과: %s%n", getParticipantCards(name, cards), score);
    }

    public String getFinalProfitHeader() {
        return String.format("%n## 최종 수익%n");
    }

    public String getFinalProfit(final Name name, final Profit profit) {
        return String.format("%s: %s%n", name, profit);
    }
}
