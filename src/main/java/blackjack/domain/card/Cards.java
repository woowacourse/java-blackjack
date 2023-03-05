package blackjack.domain.card;

import java.util.*;
import java.util.stream.Collectors;

public class Cards {

    private static final int CARD_TOTAL_SIZE = 48;

    private static Queue<Card> cards;

    private Cards() {
    }

    public static Cards initializeCards() {
        Cards cards = new Cards();
        cards.createCards();
        cards.shuffleCards();
        return cards;
    }

    private void createCards() {
        Queue<Card> createCards = Arrays.stream(CardSymbol.values())
                .flatMap(cardSymbol -> Arrays.stream(CardNumber.values())
                        .map(cardNumber -> new Card(cardNumber, cardSymbol)))
                .collect(Collectors.toCollection(ArrayDeque::new));
        validate(createCards);
        cards = createCards;
    }

    private void validate(Queue<Card> cards) {
        if (cards.size() != CARD_TOTAL_SIZE) {
            throw new IllegalArgumentException("카드의 개수는 총 48개여야 합니다.");
        }
    }

    private void shuffleCards() {
        List<Card> list = new ArrayList<>(cards);
        Collections.shuffle(list);
        cards = new LinkedList<>(list);
    }

    public Queue<Card> getCards() {
        return cards;
    }
}
