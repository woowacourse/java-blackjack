package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Nested
    @DisplayName("딜러가 카드를 받아야 하는지 여부를 반환한다.")
    class IsPickCard {

        @DisplayName("16점 이하라면, 딜러가 카드를 뽑는 여부를 올바르게 반환한다.")
        @Test
        public void isPickCard() throws Exception {
            // given
            final var dealer = new Dealer();

            // when
            final var actual = dealer.isPickCard();

            // then
            assertThat(actual).isTrue();
        }

        @DisplayName("16점 초과라면, 딜러가 카드를 뽑는 여부를 올바르게 반환한다.")
        @Test
        public void isPickCard1() throws Exception {
            // given
            final Card card1 = new Card(CardNumber.TEN, Emblem.CLUB);
            final Card card2 = new Card(CardNumber.SEVEN, Emblem.HEART);
            final CardHand cardHand = new CardHand(List.of(card1, card2));
            final var dealer = new Dealer(cardHand);

            // when
            final var actual = dealer.isPickCard();

            // then
            assertThat(actual).isFalse();
        }
    }
}
