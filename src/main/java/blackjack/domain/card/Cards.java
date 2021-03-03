package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public void add(Card card) {
        // TODO :: 점수 확인
        cards.add(card);
    }

    public Score getScore(){
        int score = cards.stream()
                .mapToInt(card -> card.getDenomination().getScore())
                .sum();
        return new Score(score);
    }

    public boolean isBust(){
        return getScore().isBust();
    }

    public List<Card> cards(){
        return Collections.unmodifiableList(cards);
    }
}
