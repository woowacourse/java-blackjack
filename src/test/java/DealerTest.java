import static org.assertj.core.api.Assertions.assertThat;

import constant.CardNumber;
import constant.Emblem;
import domain.Card;
import domain.CardHand;
import domain.Dealer;
import domain.Deck;
import java.util.ArrayDeque;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Nested
    @DisplayName("딜러는 카드를 뽑는다.")
    class pickCard {
        @Test
        @DisplayName("Dealer는 카드를 뽑는다.")
        void test_pickCard() {
            //given
            Card card = new Card(CardNumber.TWO, Emblem.CLUB);
            final var d = new ArrayDeque<>(List.of(card));
            final var deck = new Deck(d);
            var dealer = new Dealer();

            //when
            dealer.pickUpCard(deck);

            //then
            assertThat(dealer.hand().hand()).contains(card);
        }
    }

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
