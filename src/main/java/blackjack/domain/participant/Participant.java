package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.result.Result;
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

    public Result compareMatchResult(int dealerCardScore) {
        if (cardHand.getScore() < dealerCardScore || cardHand.isBust()) {
            return Result.LOSE;
        }
        if (dealerCardScore == cardHand.getScore()) {
            return Result.DRAW;
        }
        if (dealerCardScore < cardHand.getScore()) {
            return Result.WIN;
        }
        throw new IllegalArgumentException("[ERROR] 입력 값이 올바르지 않습니다.");
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
