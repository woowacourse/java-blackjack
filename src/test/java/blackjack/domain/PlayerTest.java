package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.blackjack.domain.Dealer;
import blackjack.blackjack.domain.Denomination;
import blackjack.blackjack.domain.Gambler;
import blackjack.blackjack.domain.Player;
import blackjack.blackjack.domain.PlayingCard;
import blackjack.blackjack.domain.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("겜블러 생성 테스트")
    void gambler_create() {
        // given
        String expected = "pobi";
        final Player pobi = new Gambler(expected);

        // when
        String actual = pobi.getName();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러 생성 테스트")
    void dealer_create() {
        // given
        String expected = "dolbum";
        final Player dolbum = new Dealer(expected);

        // when
        String actual = dolbum.getName();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("보유한 카드 기준 합산 점수 반환 테스트")
    void supply_card() {
        // given
        final Player rich = new Dealer("rich");
        rich.addCard(PlayingCard.of(Suit.HEARTS, Denomination.FIVE));
        rich.addCard(PlayingCard.of(Suit.SPADES, Denomination.FIVE));
        int expected = 10;

        // when
        int actual = rich.getResult();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러는 현재 합산 16이하인지 확인할 수 있다")
    void less_than_or_same_sixteen() {
        // given
        final Dealer rich = new Dealer("rich");
        rich.addCard(PlayingCard.of(Suit.HEARTS, Denomination.FIVE));
        rich.addCard(PlayingCard.of(Suit.SPADES, Denomination.FIVE));

        // when
        boolean actual = rich.isUnderSixteen();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("딜러는 현재 합산 17이상인지 확인할 수 있다")
    void over_than_sixteen() {
        // given
        final Dealer rich = new Dealer("rich");
        rich.addCard(PlayingCard.of(Suit.HEARTS, Denomination.KING));
        rich.addCard(PlayingCard.of(Suit.SPADES, Denomination.KING));

        // when
        boolean actual = rich.isUnderSixteen();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("딜러와 겜블러 모두 버스트 여부를 확인할 수 있다.")
    void is_burst() {
        // given
        final Dealer rich = new Dealer("rich");
        final Player dolbum = new Gambler("dolbum");
        rich.addCard(PlayingCard.of(Suit.HEARTS, Denomination.KING));
        rich.addCard(PlayingCard.of(Suit.SPADES, Denomination.KING));
        rich.addCard(PlayingCard.of(Suit.CLUBS, Denomination.KING));

        dolbum.addCard(PlayingCard.of(Suit.HEARTS, Denomination.KING));
        dolbum.addCard(PlayingCard.of(Suit.SPADES, Denomination.KING));
        dolbum.addCard(PlayingCard.of(Suit.CLUBS, Denomination.KING));

        // when
        boolean isBurstForDealer = rich.isBurst();
        boolean isBurstForGambler = rich.isBurst();

        // then
        assertAll(
            () -> assertThat(isBurstForDealer).isTrue(),
            () -> assertThat(isBurstForGambler).isTrue()
        );
    }
}
