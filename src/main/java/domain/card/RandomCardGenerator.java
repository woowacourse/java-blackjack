package domain.card;

import java.util.List;

public class RandomCardGenerator implements CardGenerator {

    @Override
    public List<Card> generate() {
        return CardType.getAllCardTypes()
                .stream()
                .flatMap(cardType -> this.createCards(cardType).stream())
                .toList();
    }

    private List<Card> createCards(final CardType cardType) {
        return CardScore.getAllCardScores()
                .stream()
                .map(cardScore -> new Card(cardType, cardScore))
                .toList();
    }
}
