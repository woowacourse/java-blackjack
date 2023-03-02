package domain;

import java.util.List;


public class Dealer extends Player {

    public Dealer(List<Card> cards) {
        super("딜러", cards);
    }

    public boolean isOverSixteen() {
        return getScore() > 16;
    }

    public void drawCardIfNecessary(Deck deck) {
        // TODO: 메서드 이름 생각해보기
        if (!isOverSixteen()) {
            addCard(deck.drawCard());
        }
    }
}


