package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerTest {

    @Nested
    @DisplayName("플레아어가 카드를 뽑을지 여부를 반환한다.")
    class isPickCard {

        @DisplayName("플레이어가 카드를 뽑을지 여부를 올바르게 반환한다.")
        @ParameterizedTest
        @MethodSource("providePlayerHand")
        public void isPickCard(final List<Card> cards, final boolean expected) throws Exception {
            // given
            final CardHand cardHand = new CardHand(cards);
            final Participant participant = new Participant(cardHand);
            final Player p = new Player(participant);

            // when
            final boolean actual = p.isPickCard();

            // then
            assertThat(actual).isEqualTo(expected);
        }

        private static Stream<Arguments> providePlayerHand() {
            return Stream.of(
                Arguments.of(
                    List.of(new Card(CardNumber.TEN, Emblem.CLUB), new Card(CardNumber.TEN, Emblem.DIAMOND),
                        new Card(CardNumber.ACE, Emblem.CLUB)), true),
                Arguments.of(
                    List.of(new Card(CardNumber.TEN, Emblem.DIAMOND),
                        new Card(CardNumber.TEN, Emblem.CLUB), new Card(CardNumber.TWO, Emblem.CLUB)), false)
            );
        }

    }

}
