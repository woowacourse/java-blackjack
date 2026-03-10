package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class Cards {
    private final List<Card> cards;

    public Cards(final Random random) {
        // 카드 52개 생성
        cards = new ArrayList<>();
        for(Suit suit : Suit.values()){
            for(Rank rank : Rank.values()){
                cards.add(new Card(suit, rank));
            }
        }
        // 셔플
        Collections.shuffle(cards, random);
    }
}
