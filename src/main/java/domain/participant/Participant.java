package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private final Name name;
    private final Cards cards;

    public Participant(Name name) {
        this.name = name;
        this.cards = new Cards();
    }

    public abstract boolean isReceivable();

    public void receive(Card receivedCard) {
        if (isReceivable()) {
            cards.receive(receivedCard);
            return;
        }
        throw new IllegalStateException("[ERROR] 카드를 받을 수 없는 상태입니다.");
    }

    public void receive(List<Card> receivedCards) {
        if (isReceivable()) {
            cards.receive(receivedCards);
            return;
        }
        throw new IllegalStateException("[ERROR] 카드를 받을 수 없는 상태입니다.");
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public boolean isBusted() {
        return cards.isOverBlackjackScore();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }
}
