package model.cards;

import java.util.Collections;
import java.util.List;
import model.card.Card;
import model.card.CardNumber;

public abstract class Cards {

    private static final int BUST_THRESHOLD = 21;

    protected final List<Card> cards;

    protected Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(final Card card) {
        if (isBust()) {
            throw new IllegalStateException("버스트일 때는 카드를 추가할 수 없습니다.");
        }
        cards.add(card);
    }

    public boolean isBust() {
        return calculateSum() > BUST_THRESHOLD;
    }

    public int calculateResult() {
        changeAceElevenToOneUntilNotBust();
        return calculateSum();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    private void changeAceElevenToOneUntilNotBust() {
        int aceCount = findAceElevenCount();
        while (isBust() && aceCount > 0) {
            aceCount--;
            changeAceElevenToOne();
        }
    }

    private int calculateSum() {
        return cards.stream()
                .mapToInt(Card::getNumberValue)
                .sum();
    }

    private int findAceElevenCount() {
        return (int) cards.stream()
                .filter(card -> card.isSameNumber(CardNumber.ACE_ELEVEN))
                .count();
    }

    private void changeAceElevenToOne() {
        Card aceElevenCard = cards.stream()
                .filter(card -> card.isSameNumber(CardNumber.ACE_ELEVEN))
                .findAny()
                .orElseThrow();

        cards.remove(aceElevenCard);
        cards.add(new Card(CardNumber.ACE_ONE, aceElevenCard.getShape()));
    }
}
