package domain;

import java.util.List;

// Cards의 책임이 뭘까? 상태 관리, 점수계산
public class Cards {
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card){
        cards.add(card);
    }

}
