package blackjack.domain.card;

public class CardDeck extends Deck {

    @Override
    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱이 비어 있을 때 카드를 뽑을 수 없습니다.");
        }
        return cards.pollFirst();
    }
}
