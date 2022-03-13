package blackjack.domain;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Suit.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CardsArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                arguments(new Cards(List.of(Card.of(NINE, CLOVER), Card.of(EIGHT, DIAMOND))), 17),
                arguments(new Cards(List.of(Card.of(KING, SPADE), Card.of(ACE, CLOVER))), 21),
                arguments(new Cards(List.of(Card.of(SEVEN, CLOVER), Card.of(KING, SPADE))), 17),
                arguments(new Cards(List.of(Card.of(ACE, CLOVER), Card.of(ACE, SPADE))), 12),
                arguments(new Cards(List.of(Card.of(ACE, CLOVER), Card.of(KING, SPADE))), 21),
                arguments(new Cards(List.of(Card.of(ACE, CLOVER), Card.of(THREE, HEART))), 14)
        );
    }
}
