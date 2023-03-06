package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러가 카드를 더 뽑을 수 있으면 참을 반환해야 한다.")
    void canDrawCard_true() {
        // given
        Dealer dealer = new Dealer(List.of(
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.FIVE)
        ));

        // expect
        assertThat(dealer.canDrawCard())
                .isTrue();
    }

    @Test
    @DisplayName("딜러가 카드를 더 뽑을 수 없으면 거짓을 반환해야 한다.")
    void canDrawCard_false() {
        // given
        Dealer dealer = new Dealer(List.of(
                new Card(Suit.DIAMOND, Rank.KING),
                new Card(Suit.DIAMOND, Rank.FIVE),
                new Card(Suit.DIAMOND, Rank.QUEEN)
        ));

        // expect
        assertThat(dealer.canDrawCard())
                .isFalse();
    }

    @Test
    @DisplayName("처음에 딜러는 2장의 카드 중 1장의 카드만 보여줘야 한다.")
    void getStartCard_success() {
        // given
        Dealer dealer = new Dealer(List.of(
                new Card(Suit.DIAMOND, Rank.KING),
                new Card(Suit.DIAMOND, Rank.FIVE)
        ));

        // when
        List<Card> cards = dealer.getStartCards();

        // then
        assertThat(cards)
                .hasSize(1)
                .containsExactly(new Card(Suit.DIAMOND, Rank.KING));
    }

    @Test
    @DisplayName("dealer는 플레이어가 아니어야 한다.")
    void isPlayer_false() {
        // given
        Dealer dealer = new Dealer(Collections.emptyList());

        // expect
        assertThat(dealer.isPlayer())
                .isFalse();
    }
}
