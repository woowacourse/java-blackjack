package blackjack.domain.user.status;

import blackjack.domain.card.Card;
import blackjack.domain.card.painting.Suit;
import blackjack.domain.card.painting.Symbol;
import blackjack.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusTest {
    @DisplayName("점수에 맞는 상태를 반환하는지 확인한다")
    @ParameterizedTest
    @MethodSource
    void testOf(List<Card> cards, Status status) {
        User user = new User("웨지", 0);
        cards.forEach(user::drawCard);

        assertThat(Status.of(user)).isEqualTo(status);
    }

    private static Stream<Arguments> testOf() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Symbol.JACK), new Card(Suit.DIAMOND, Symbol.ACE)), Status.BLACKJACK),
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Symbol.THREE), new Card(Suit.DIAMOND, Symbol.TWO)), Status.PLAYING),
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLOVER, Symbol.JACK), new Card(Suit.DIAMOND, Symbol.QUEEN),
                        new Card(Suit.DIAMOND, Symbol.KING)), Status.BURST)
        );
    }
}
