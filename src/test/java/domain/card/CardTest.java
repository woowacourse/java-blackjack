package domain.card;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

class CardTest {
    static Stream<Arguments> provideCardData() {
        return Stream.of(
                Arguments.of(new Card(TrumpSuit.HEART, TrumpNumber.ACE), "A하트"),
                Arguments.of(new Card(TrumpSuit.DIAMOND, TrumpNumber.SEVEN), "7다이아몬드"),
                Arguments.of(new Card(TrumpSuit.SPADE, TrumpNumber.JACK), "J스페이드")
        );
    }
}