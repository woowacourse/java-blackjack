package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParticipantHand {

    private static final int BUST_STANDARD = 21;
    private static final int ACE_DIFF = 10;
    private static final String INVALID_CARD_STATE = "비정상적인 카드 추가입니다. 플레이어는 21점 이상 받을 수 없습니다";

    private final List<TrumpCard> handCards;

    public ParticipantHand() {
        this.handCards = new ArrayList<>();
    }


    public void addCard(TrumpCard card) {
        if (isBust()) {
            throw new IllegalStateException(INVALID_CARD_STATE);
        }
        handCards.add(card);
    }

    public boolean isBust() {
        int sum = calculateCardSum();
        return BUST_STANDARD < sum;
    }

    public int calculateCardSum() {
        return calculateCardSum(BUST_STANDARD);
    }

    public int calculateCardSum(int aceCalculateStandard) {
        int sum = handCards.stream()
                .map(TrumpCard::cardNumberValue)
                .reduce(Integer::sum)
                .orElse(0);
        int aceCount = (int) handCards.stream()
                .filter(TrumpCard::isAce)
                .count();
        if (aceCount != 0) {
            return calculateAceIncludeSum(aceCalculateStandard, aceCount, sum);
        }
        return sum;
    }

    private int calculateAceIncludeSum(int aceCalculateStandard, int aceCount, int sum) {
        if (aceCalculateStandard < sum && aceCount != 0) {
            return calculateAceIncludeSum(aceCalculateStandard, aceCount - 1, sum - ACE_DIFF);
        }
        return sum;
    }

    public List<TrumpCard> getCards(){
        return Collections.unmodifiableList(handCards);
    }

}
