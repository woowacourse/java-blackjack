package domain.card;

import domain.CardShuffler;

import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private final List<Card> cards;

    private Cards(final List<CardPattern> cardPatterns, final List<CardNumber> cardNumbers) {
        this.cards = makeCards(cardPatterns, cardNumbers);
    }

    public static Cards create() {
        List<CardPattern> cardPatterns = CardPattern.getAll();
        List<CardNumber> cardNumbers = CardNumber.getAll();
        return new Cards(cardPatterns, cardNumbers);
    }

    private List<Card> makeCards(final List<CardPattern> cardPatterns, final List<CardNumber> cardNumbers) {
        return cardPatterns.stream()
                .flatMap(pattern -> cardNumbers.stream().map(number -> Card.create(pattern, number)))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Card> getShuffledCards(final CardShuffler cardShuffler) {
        return cardShuffler.shuffle(cards);
    }
}
