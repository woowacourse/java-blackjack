package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final String DUPLICATED_INDEX_EXCEPTION = "[ERROR] 이미 뽑힌 카드입니다.";
    private List<Card> cards;
    private List<Boolean> isUsed;

    public Cards() {
        isUsed = new ArrayList<>();
        for (int i = 0; i<52; ++i) {
            isUsed.add(false);
        }


        cards = new ArrayList<>();
        cards.add(new Card("A다이아몬드"));
        cards.add(new Card("2다이아몬드"));
        cards.add(new Card("3다이아몬드"));
        cards.add(new Card("4다이아몬드"));
        cards.add(new Card("5다이아몬드"));
        cards.add(new Card("6다이아몬드"));
        cards.add(new Card("7다이아몬드"));
        cards.add(new Card("8다이아몬드"));
        cards.add(new Card("9다이아몬드"));
        cards.add(new Card("J다이아몬드"));
        cards.add(new Card("Q다이아몬드"));
        cards.add(new Card("K다이아몬드"));

        cards.add(new Card("A하트"));
        cards.add(new Card("2하트"));
        cards.add(new Card("3하트"));
        cards.add(new Card("4하트"));
        cards.add(new Card("A하트"));
        cards.add(new Card("5하트"));
        cards.add(new Card("6하트"));
        cards.add(new Card("7하트"));
        cards.add(new Card("8하트"));
        cards.add(new Card("9하트"));
        cards.add(new Card("J하트"));
        cards.add(new Card("Q하트"));
        cards.add(new Card("K하트"));

        cards.add(new Card("A스페이드"));
        cards.add(new Card("2스페이드"));
        cards.add(new Card("3스페이드"));
        cards.add(new Card("4스페이드"));
        cards.add(new Card("A스페이드"));
        cards.add(new Card("5스페이드"));
        cards.add(new Card("6스페이드"));
        cards.add(new Card("7스페이드"));
        cards.add(new Card("8스페이드"));
        cards.add(new Card("9스페이드"));
        cards.add(new Card("J스페이드"));
        cards.add(new Card("Q스페이드"));
        cards.add(new Card("K스페이드"));

        cards.add(new Card("A클로버"));
        cards.add(new Card("2클로버"));
        cards.add(new Card("3클로버"));
        cards.add(new Card("4클로버"));
        cards.add(new Card("A클로버"));
        cards.add(new Card("5클로버"));
        cards.add(new Card("6클로버"));
        cards.add(new Card("7클로버"));
        cards.add(new Card("8클로버"));
        cards.add(new Card("9클로버"));
        cards.add(new Card("J클로버"));
        cards.add(new Card("Q클로버"));
        cards.add(new Card("K클로버"));
    }

    public Card pickCard(NumberGenerator numberGenerator) {
        int index = numberGenerator.generateNumber();
        validateDuplicated(index);
        return cards.get(index);
    }

    private void validateDuplicated(int index) {
        if (isUsed.get(index)) {
            throw new IllegalArgumentException(DUPLICATED_INDEX_EXCEPTION);
        }
        isUsed.set(index, true);
    }
}
