package domain.participants;

import domain.card.Hand;
import domain.hitStrategy.HitStrategy;
import domain.score.Score;
import java.util.List;

//추상 클래스
public class Dealer extends Participant {
    private static final String NAME = "딜러";

    private final HitStrategy hitStrategy;

    public Dealer(Hand hand, HitStrategy hitStrategy) {
        super(NAME, hand);
        this.hitStrategy = hitStrategy;
    }

    public boolean needsToHit(List<Score> playerScores) {
        return hitStrategy.needToHit(getScore()) && existsNotBurstPlayer(playerScores);
    }

    private boolean existsNotBurstPlayer(List<Score> scores) {
        return scores.stream()
                .anyMatch(score -> !score.isBurst());
    }
}
