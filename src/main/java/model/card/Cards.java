package model.card;

import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int BUST_THRESHOLD = 21;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public int calculateResult() {
        changeAceElevenToOneUntilNotBust();
        return calculateSum();
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

    public void addCard(final Card card) {
        if (isBust()) {
            throw new IllegalStateException("버스트일 때는 카드를 추가할 수 없습니다.");
        }
        cards.add(card);
    }

    public boolean isBust() {
        return calculateSum() > BUST_THRESHOLD;
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
                .orElseThrow(() -> new IllegalStateException("1로 취급할 A가 없습니다."));

        cards.remove(aceElevenCard);
        cards.add(new Card(CardNumber.ACE_ONE, aceElevenCard.shape()));
    }

    public int getCardsSize() {
        return cards.size();
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean isBlackjack() {
        return getCardsSize() == 2 && calculateResult() == BUST_THRESHOLD;
    }
}
