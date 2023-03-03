package domain.helper;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public final class ParticipantArguments {

    private ParticipantArguments() {
    }

    private static Stream<Arguments> makeCards() {
        return Stream.of(
                Arguments.of(List.of(Card.create(CardPattern.HEART, CardNumber.ACE), Card.create(CardPattern.DIAMOND, CardNumber.TWO),
                        Card.create(CardPattern.CLOVER, CardNumber.THREE), Card.create(CardPattern.SPADE, CardNumber.QUEEN)), 16)
                , Arguments.of(List.of(Card.create(CardPattern.HEART, CardNumber.ACE), Card.create(CardPattern.DIAMOND, CardNumber.TWO),
                        Card.create(CardPattern.CLOVER, CardNumber.TWO), Card.create(CardPattern.SPADE, CardNumber.TWO)), 17)
        );
    }

    private static Stream<Arguments> makeBustCard() {
        return Stream.of(
                Arguments.of(List.of(Card.create(CardPattern.HEART, CardNumber.ACE), Card.create(CardPattern.DIAMOND, CardNumber.TWO),
                        Card.create(CardPattern.CLOVER, CardNumber.THREE), Card.create(CardPattern.SPADE, CardNumber.QUEEN)), false)
                , Arguments.of(List.of(Card.create(CardPattern.HEART, CardNumber.QUEEN), Card.create(CardPattern.DIAMOND, CardNumber.TWO),
                        Card.create(CardPattern.CLOVER, CardNumber.TEN), Card.create(CardPattern.SPADE, CardNumber.TWO)), true)
        );
    }

    private static Stream<Arguments> makeBlackJackCard() {
        return Stream.of(
                Arguments.of(List.of(Card.create(CardPattern.HEART, CardNumber.ACE), Card.create(CardPattern.SPADE, CardNumber.TWO)), false)
                , Arguments.of(List.of(Card.create(CardPattern.HEART, CardNumber.ACE), Card.create(CardPattern.DIAMOND, CardNumber.QUEEN)), true)
        );
    }

    private static Stream<Arguments> makeDealerCards() {
        return Stream.of(
                Arguments.of(List.of(Card.create(CardPattern.HEART, CardNumber.ACE), Card.create(CardPattern.DIAMOND, CardNumber.TWO),
                        Card.create(CardPattern.CLOVER, CardNumber.THREE), Card.create(CardPattern.SPADE, CardNumber.QUEEN)), true)
                , Arguments.of(List.of(Card.create(CardPattern.HEART, CardNumber.ACE), Card.create(CardPattern.DIAMOND, CardNumber.TWO),
                        Card.create(CardPattern.CLOVER, CardNumber.TWO), Card.create(CardPattern.SPADE, CardNumber.TWO)), false)
        );
    }
}
