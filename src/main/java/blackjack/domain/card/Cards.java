package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private static final int CARD_TOTAL_SIZE = 48;

    private static List<Card> cards;

    private Cards() {
    }

    public static Cards initializeCards() {
        Cards cards = new Cards();
        cards.createCards();
        cards.shuffleCards();
        return cards;
    }

    private void createCards() {
        List<Card> createCards = Arrays.stream(CardSymbol.values())
                .flatMap(cardSymbol -> Arrays.stream(CardNumber.values())
                        .map(cardNumber -> new Card(cardNumber, cardSymbol)))
                .collect(Collectors.toList());
        validate(createCards);
        cards = createCards;
    }

    private void validate(List<Card> cards) {
        if (cards.size() != CARD_TOTAL_SIZE) {
            throw new IllegalArgumentException("카드의 개수는 총 48개여야 합니다.");
        }
    }

    private void shuffleCards() {
        Collections.shuffle(cards);
    }

    public List<Card> getCards() {
        return cards;
    }
}
