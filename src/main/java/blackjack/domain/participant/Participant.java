package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.List;

public abstract class Participant {

    protected final Name name;
    protected final Hand cardHand;

    protected Participant(Name name, Hand cardHand) {
        validateNull(name, cardHand);
        this.name = name;
        this.cardHand = cardHand;
    }

    private void validateNull(Name name, Hand cardHand) {
        if (name == null || cardHand == null) {
            throw new IllegalArgumentException("[ERROR] 이름과 카드패가 null일 수 없습니다.");
        }
    }

    public abstract boolean shouldReceive();

    public boolean isWin(Participant participant) {
        return this.cardHand.compareScore(participant.cardHand);
    }

    public void receiveCard(Card card) {
        cardHand.add(card);
    }

    public boolean isBust() {
        return cardHand.isBust();
    }

    public boolean isBlackjack() {
        return cardHand.isBlackjack();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cardHand.getCards();
    }

    public int getCardTotalScore() {
        return cardHand.getScore();
    }

    public Card getOpenCard() {
        return cardHand.openCard();
    }
}
