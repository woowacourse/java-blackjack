package blackjack.card;

import static blackjack.fixture.TestFixture.provideOneAceCards;
import static blackjack.fixture.TestFixture.provideTwoAceCards1;
import static blackjack.fixture.TestFixture.provideTwoAceCards2;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    @DisplayName("카드 합 구하기")
    @MethodSource("provideCards")
    @ParameterizedTest
    void sumCards(List<Card> cards, int sum) {
        // given
        final Hand hand = new Hand();

        // when
        hand.addCards(cards);

        // then
        assertThat(hand.sumCards()).isEqualTo(sum);
    }

    private static Stream<Arguments> provideCards() {
        return Stream.of(
                Arguments.of(provideOneAceCards(), 13),
                Arguments.of(provideTwoAceCards1(), 12),
                Arguments.of(provideTwoAceCards2(), 20)
        );
    }

}
