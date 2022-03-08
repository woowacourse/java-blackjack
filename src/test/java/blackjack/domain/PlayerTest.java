package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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
}
