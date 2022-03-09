package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    public static final String DUPLICATE_EXCEPTION_MESSAGE = "카드 패에 중복된 카드가 존재할 수 없습니다.";

    private final List<Card> cardHand;

    public Cards(List<Card> cardHand) {
        this.cardHand = new ArrayList<>(cardHand);
    }

    public void receive(Card card) {
        validateDuplicate(card);
        cardHand.add(card);
    }

    private void validateDuplicate(Card card) {
        if (cardHand.contains(card)) {
            throw new IllegalArgumentException(DUPLICATE_EXCEPTION_MESSAGE);
        }
    }

    public List<Card> getCards() {
        return new ArrayList<>(cardHand);
    }
}
