package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Nested
    @DisplayName("딜러가 카드를 더 뽑을 수 ")
    class canDealerDraw {
        @Test
        @DisplayName("있으면 참을 반환해야 한다.")
        void canDrawCard_true() {
            // given
            Dealer dealer = new Dealer();
            dealer.addCard(new Card(Suit.DIAMOND, Rank.ACE));
            dealer.addCard(new Card(Suit.DIAMOND, Rank.FIVE));

            // expect
            assertThat(dealer.canDrawCard())
                    .isTrue();
        }

        @Test
        @DisplayName("없으면 거짓을 반환해야 한다.")
        void canDrawCard_false() {
            // given
            Dealer dealer = new Dealer();
            dealer.addCard(new Card(Suit.DIAMOND, Rank.KING));
            dealer.addCard(new Card(Suit.DIAMOND, Rank.FIVE));
            dealer.addCard(new Card(Suit.DIAMOND, Rank.QUEEN));

            // expect
            assertThat(dealer.canDrawCard())
                    .isFalse();
        }
    }

    @Test
    @DisplayName("처음에 딜러는 2장의 카드 중 1장의 카드만 보여줘야 한다.")
    void getInitCard_success() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Suit.DIAMOND, Rank.KING));
        dealer.addCard(new Card(Suit.DIAMOND, Rank.FIVE));

        // when
        List<Card> cards = dealer.getInitCards();

        // then
        assertThat(cards)
                .hasSize(1)
                .containsExactly(new Card(Suit.DIAMOND, Rank.KING));
    }

    @Test
    @DisplayName("dealer는 플레이어가 아니어야 한다.")
    void isPlayer_false() {
        // given
        Dealer dealer = new Dealer();

        // expect
        assertThat(dealer.isPlayer())
                .isFalse();
    }
}
