package blackjack.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DeckInitializer {

    public List<Card> generateNormalCards() {
        return IntStream.range(2, 11)
                .boxed()
                .flatMap(i -> Stream.of(CardShape.values())
                        .map(cardShape -> new NormalCard(i, cardShape)))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Card> generateSpecialCards() {
        return Stream.of('J', 'Q', 'K')
                .flatMap(character -> Stream.of(CardShape.values())
                        .map(cardShape -> new NormalCard(character, cardShape)))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Card> generateAceCards() {
        return Stream.of(CardShape.values())
                .map(AceCard::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public Deck generateDeck() {
        return new Deck(Stream.of(generateNormalCards(), generateSpecialCards(), generateAceCards())
                .flatMap(List::stream)
                .toList()
        );
    }
}
