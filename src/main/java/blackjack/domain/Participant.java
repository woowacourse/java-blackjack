package blackjack.domain;

import java.util.List;

public abstract class Participant {
    private Name name;
    private HoldingCards holdingCards;

    public Participant(String name, HoldingCards holdingCards) {
        this.name = new Name(name);
        this.holdingCards = holdingCards;
    }

    void receiveCard(Card card){
        holdingCards.add(card);
    };

    List<Card> showCards(){
        return holdingCards.getCards();
    };

    public String getName() {
        return this.name.name;
    }

    public HoldingCards getHoldingCard() {
        return holdingCards;
    }

    public abstract boolean isFinished();

    private class Name {
        private final String name;

        private Name(String name) {
            if (name.isEmpty()) {
                throw new IllegalArgumentException("[ERROR] 빈 이름은 사용할 수 없습니다.");
            }
            this.name = name;
        }
    }
}
