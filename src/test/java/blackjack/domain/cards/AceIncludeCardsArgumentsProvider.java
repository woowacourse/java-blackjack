package blackjack.domain.cards;

import static blackjack.domain.Denomination.ACE;
import static blackjack.domain.Denomination.NINE;
import static blackjack.domain.Denomination.TEN;
import static blackjack.domain.Suit.CLOVER;
import static blackjack.domain.Suit.DIAMOND;

import blackjack.domain.Card;
import blackjack.domain.Cards;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class AceIncludeCardsArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(Arguments.arguments(
                        new Cards(List.of(Card.of(TEN, CLOVER), Card.of(TEN, DIAMOND), Card.of(ACE, DIAMOND))), 21),
                Arguments.arguments(new Cards(List.of(Card.of(TEN, CLOVER), Card.of(ACE, DIAMOND))), 21),
                Arguments.arguments(new Cards(List.of(Card.of(ACE, CLOVER), Card.of(TEN, DIAMOND))), 21),
                Arguments.arguments(new Cards(List.of(Card.of(NINE, CLOVER), Card.of(ACE, DIAMOND))), 20),
                Arguments.arguments(
                        new Cards(List.of(Card.of(NINE, CLOVER), Card.of(ACE, DIAMOND), Card.of(ACE, CLOVER))), 21));
    }
}


