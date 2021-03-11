package blackjack.domain.participant;

import java.util.Map;

public class Dealer extends Participant {
    private static final int STAY_THRESHOLD = 17;

    public Dealer() {
        super("딜러");
    }

    public boolean isStay() {
        return calculateCardsScoreResult() >= STAY_THRESHOLD;
    }

    public String getGameResult(Map<String, String> playersGameResult) {
        int loseCount = (int) playersGameResult.entrySet().stream()
                .filter(entry -> "승".equals(entry.getValue()))
                .count();
        int winCount = playersGameResult.size() - loseCount;

        return String.format("%d승 %d패", winCount, loseCount);
    }

    public String getFirstCardInfo() {
        return cards.get(0).toString();
    }
}