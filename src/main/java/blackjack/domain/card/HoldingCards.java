package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class HoldingCards {

    public static final int BUST_STANDARD = 21;
    private static final int ACE_DIFFERENCE = 10;
    private static final int WITHOUT_HIDDEN_CARD_INDEX = 1;

    private final List<Card> cards;

    public HoldingCards() {
        this.cards = new ArrayList<>();
    }

    public HoldingCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return cardSum() > BUST_STANDARD;
    }

    public int cardSum() {
        int sum = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();

        if (sum > BUST_STANDARD) { // Ace를 갖고 있고 10을 더해도 Bust가 아니면 11로 계산하도록 수정
            sum = adjustSum(sum);
        }
        return sum;
    }

    private int adjustSum(int sum) {
        int aceCount = getAceCount();

        while (sum > BUST_STANDARD && aceCount > 0) {
            sum -= ACE_DIFFERENCE;
            aceCount -= 1;
        }
        return sum;
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(card -> card.getCardNumber() == CardNumber.ACE)
                .count();
    }

    public boolean isBlackJack() {
        return hasAce() && hasNumberTenCard() ;
    }

    private boolean hasNumberTenCard() {
        return cards.stream()
                .anyMatch(card -> card.getCardNumber() == CardNumber.TEN ||
                        card.getCardNumber() == CardNumber.JACK ||
                        card.getCardNumber() == CardNumber.QUEEN ||
                        card.getCardNumber() == CardNumber.KING);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.getCardNumber() == CardNumber.ACE);
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getAllCards() {
        return List.copyOf(cards);
    }

    public List<Card> getCardsWithOutHiddenCard() {
        return List.copyOf(cards.subList(WITHOUT_HIDDEN_CARD_INDEX, cards.size()));
    }
}
