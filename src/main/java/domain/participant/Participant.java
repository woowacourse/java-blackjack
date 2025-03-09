package domain.participant;

import domain.card.Card;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Participant {
    protected static final int BUST_THRESHOLD = 21;

    protected final String nickname;
    protected final Map<BattleResult, Integer> battleResult;

    protected Participant(final String nickname) {
        this.nickname = nickname;
        this.battleResult = new HashMap<>();
    }

    public abstract boolean ableToDraw(final int score);
    public abstract boolean areYouDealer();

    public int getScore(List<Card> ownedCards) {
        int totalScore = 0;
        int aceCounts = 0;

        for (Card card : ownedCards) {
            totalScore += card.getNumber();
            if (card.isAceCard()) {
                aceCounts++;
            }
        }

        totalScore = addAceScore(aceCounts, totalScore);

        return totalScore;
    }

    private int addAceScore(int aceCounts, int totalScore) {
        while (aceCounts-- > 0) {
            if (totalScore + 10 <= BUST_THRESHOLD) {
                totalScore += 10;
            }
        }
        return totalScore;
    }

    public void updateBattleResult(final BattleResult battleResult) {
        this.battleResult.merge(battleResult, 1, Integer::sum);
    }

    public String getNickname() {
        return nickname;
    }

    public Map<BattleResult, Integer> getBattleResult() {
        return battleResult;
    }

}
