package domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Denomination;
import domain.card.PlayingCard;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    @DisplayName("딜러 생성 테스트")
    void dealer_create() {
        // given
        String expected = "dolbum";
        Dealer dealer = new Dealer(expected);

        // when
        String actual = dealer.getName();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("보유한 카드 기준 합산 점수 반환 테스트")
    void supply_card() {
        // given
        Dealer rich = new Dealer("rich");
        rich.addCard(PlayingCard.of(Suit.HEARTS, Denomination.FIVE));
        rich.addCard(PlayingCard.of(Suit.SPADES, Denomination.FIVE));
        int expected = 10;

        // when
        int actual = rich.getPlayResult();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러는 현재 합산 16이하인지 확인할 수 있다")
    void checkIfSameOrLessThanSixteen() {
        // given
        Dealer dealer = new Dealer("rich");
        dealer.addCard(PlayingCard.of(Suit.HEARTS, Denomination.FIVE));
        dealer.addCard(PlayingCard.of(Suit.SPADES, Denomination.FIVE));

        // when
        boolean actual = dealer.isUnderSixteen();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("딜러는 현재 합산 17이상인지 확인할 수 있다")
    void checkIfSameOrGreaterThanSeventeen() {
        // given
        Dealer dealer = new Dealer("rich");
        dealer.addCard(PlayingCard.of(Suit.HEARTS, Denomination.KING));
        dealer.addCard(PlayingCard.of(Suit.SPADES, Denomination.KING));

        // when
        boolean actual = dealer.isUnderSixteen();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("버스트 여부를 확인할 수 있다.")
    void isBust() {
        // given
        Dealer dealer = new Dealer("rich");
        dealer.addCard(PlayingCard.of(Suit.HEARTS, Denomination.KING));
        dealer.addCard(PlayingCard.of(Suit.SPADES, Denomination.KING));
        dealer.addCard(PlayingCard.of(Suit.CLUBS, Denomination.KING));

        // when
        boolean isBust = dealer.isBust();

        // then
        assertThat(isBust).isTrue();
    }
}
