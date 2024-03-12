package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.BlackjackHand;

import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private final Name name;
    private final BlackjackHand blackjackHand;

    public Participant(Name name) {
        this.name = name;
        this.blackjackHand = new BlackjackHand();
    }

    public abstract boolean isReceivable();

    public abstract List<Card> findShowingCards();

    public void receive(Card receivedCard) {
        if (isReceivable()) {
            blackjackHand.receive(receivedCard);
            return;
        }
        throw new IllegalStateException("[ERROR] 카드를 받을 수 없는 상태입니다.");
    }

    public void receive(List<Card> receivedCards) {
        if (isReceivable()) {
            blackjackHand.receive(receivedCards);
            return;
        }
        throw new IllegalStateException("[ERROR] 카드를 받을 수 없는 상태입니다.");
    }

    public int calculateScore() {
        return blackjackHand.calculateScore();
    }

    public boolean isBusted() {
        return blackjackHand.isOverBlackjackScore();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(blackjackHand.getCards());
    }
}
