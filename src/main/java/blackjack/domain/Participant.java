package blackjack.domain;

import java.util.List;
import java.util.Objects;

public abstract class Participant {
    private Name name;
    private HoldingCards holdingCards = new HoldingCards();

    public Participant(String name) {
        this.name = new Name(name);
    }

    void receiveCards(List<Card> cards) {
        for (Card card : cards) {
            holdingCards.add(card);
        }
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

    private static class Name {
        private final String name;

        private Name(String name) {
            if (Objects.isNull(name) || name.isEmpty()) {
                throw new IllegalArgumentException("[ERROR] 빈 이름은 사용할 수 없습니다.");
            }
            this.name = name;
        }
    }

}
