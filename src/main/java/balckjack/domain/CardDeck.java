package balckjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        validateNull(card);
        cards.add(card);
    }

    private void validateNull(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("아무런 카드도 입력되지 않았습니다.");
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Card> extractAceCards() {
        return cards.stream()
            .filter(Card::isAce)
            .collect(Collectors.toList());
    }

    public List<Card> extractStandardCards() {
        return cards.stream()
            .filter((card) -> !card.isAce())
            .collect(Collectors.toList());
    }

}
