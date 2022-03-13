package blackjack.domain.card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cards {

    private static final String DUPLICATE_EXCEPTION_MESSAGE = "카드 패에 중복된 카드가 존재할 수 없습니다.";

    private final List<Card> cardHand;

    public Cards(List<Card> cardHand) {
        validateDuplicate(cardHand);
        this.cardHand = new ArrayList<>(cardHand);
    }

    public Cards concat(Cards cards) {
        cardHand.addAll(cards.getCardHand());
        return new Cards(cardHand);
    }

    private void validateDuplicate(List<Card> cards) {
        Set<Card> distinctCards = new HashSet<>(cards);
        if (distinctCards.size() != cards.size()) {
            throw new IllegalArgumentException(DUPLICATE_EXCEPTION_MESSAGE);
        }
    }

    public List<Card> getCardHand() {
        return cardHand;
    }

    public int getSize() {
        return cardHand.size();
    }
}
