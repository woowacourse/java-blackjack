package domain;

import static org.assertj.core.api.Assertions.assertThat;

import constant.CardNumber;
import constant.Emblem;
import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerTest {

    @Nested
    @DisplayName("플레이어 카드 뽑기")
    class PickCard {

        @DisplayName("플레이어는 덱으로부터, 올바르게 카드를 뽑는다.")
        @Test
        public void pickCard() throws Exception {
            // given
            final Player player = new Player();
            final List<Card> cards = List.of(new Card(CardNumber.ACE, Emblem.CLUB));
            final Deck deck = new Deck(new ArrayDeque<>(cards));

            // when
            player.pickCard(deck);

            // then
            assertThat(player.cardHand().hand()).isNotEmpty();
        }

    }

    @Nested
    @DisplayName("플레아어가 카드를 뽑을지 여부를 반환한다.")
    class isPickCard {

        @DisplayName("플레이어가 카드를 뽑을지 여부를 올바르게 반환한다.")
        @ParameterizedTest
        @MethodSource("providePlayerHand")
        public void isPickCard(final List<Card> cards, final boolean expected) throws Exception {
            // given
            final CardHand cardHand = new CardHand(cards);
            final Player p = new Player(cardHand);

            // when
            final boolean actual = p.isPickCard();

            // then
            assertThat(actual).isTrue();
        }

        private static Stream<Arguments> providePlayerHand() {
            return Stream.of(
                    Arguments.of(
                            List.of(new Card(CardNumber.TEN, Emblem.CLUB), new Card(CardNumber.TEN, Emblem.DIAMOND),
                                    new Card(CardNumber.ACE, Emblem.CLUB)), true),
                    Arguments.of(
                            List.of(new Card(CardNumber.ACE, Emblem.DIAMOND),
                                    new Card(CardNumber.ACE, Emblem.CLUB)), false)
            );
        }

    }

}
