package domain.card;

import java.util.ArrayList;
import java.util.List;
import strategy.CardGenerator;

public class DeckCards {

    private final List<Card> cards;

    private DeckCards(List<Card> cards) {
        this.cards = cards;
    }

    public static DeckCards from(CardGenerator cardGenerator) {
        List<Card> generatedCards = new ArrayList<>(cardGenerator.generate());
        return new DeckCards(generatedCards);
    }

    public Card draw() { // TODO: 메소드 이름 생각해 보기
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 카드를 모두 사용하였습니다.");
        }
        return cards.remove(cards.size() - 1);
    }
}
