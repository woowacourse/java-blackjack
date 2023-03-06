package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalCards {

    private final List<Card> cards;
    private final int sum;

    // TODO final 일관성 있게 수정
    private FinalCards(final List<Card> cards, final int sum) {
        this.cards = cards;
        this.sum = sum;
    }

    // TODO participant 직접 참조하지 말고 Cards를 받기
    public static FinalCards from(final Participant participant) {
        return new FinalCards(participant.getCards(), participant.computeSumOfCards());
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public int getSum() {
        return sum;
    }
}
