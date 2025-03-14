package domain.participant;

import domain.card.TrumpCard;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParticipantHand {
    private static final Score BUST_STANDARD_SCORE = Score.from(21);
    private static final Score ACE_DIFF_SCORE = Score.from(10);
    private static final String BUST_HAND_FORMAT = "비정상적인 카드 추가입니다. 플레이어는 %d점 이상 받을 수 없습니다";

    private final List<TrumpCard> handCards;


    public ParticipantHand() {
        this.handCards = new ArrayList<>();
    }

    public void addCard(TrumpCard card) {
        Score totalScore = calculateCardSum();
        if (isBust(totalScore)) {
            throw new IllegalStateException(String.format(BUST_HAND_FORMAT, BUST_STANDARD_SCORE.toInt()));
        }
        handCards.add(card);
    }

    public boolean isBust(Score score) {
        return score.isGreaterThan(BUST_STANDARD_SCORE);
    }

    public Score calculateCardSum() {
        return calculateCardSum(BUST_STANDARD_SCORE);
    }

    public Score calculateCardSum(Score aceCalculateStandard) {
        Score totalScore = totalScore();
        int aceCount = aceCount();
        return calculateAceIncludeSum(aceCalculateStandard, aceCount, totalScore);
    }

    private Score totalScore() {
        return handCards.stream()
                .map(TrumpCard::cardNumberValue)
                .reduce(Integer::sum)
                .map(Score::new)
                .orElse(Score.zero());
    }

    private int aceCount() {
        return (int) handCards.stream()
                .filter(TrumpCard::isAce)
                .count();
    }

    private Score calculateAceIncludeSum(Score aceStandard, int aceCount, Score totalScore) {
        while (totalScore.isGreaterThan(aceStandard) && aceCount > 0) {
            totalScore = totalScore.minus(ACE_DIFF_SCORE);
            aceCount--;
        }

        return totalScore;
    }

    public List<TrumpCard> getCards() {
        return Collections.unmodifiableList(handCards);
    }

    public int size(){
        return handCards.size();
    }

}
