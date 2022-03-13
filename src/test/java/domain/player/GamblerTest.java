package domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Denomination;
import domain.card.Card;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblerTest {
    @Test
    @DisplayName("겜블러 생성 테스트")
    void gambler_create() {
        // given
        String expected = "pobi";
        Gambler gambler = new Gambler(expected);

        // when
        String actual = gambler.getName();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("버스트 여부를 확인할 수 있다.")
    void is_bust() {
        // given
        Gambler gambler = new Gambler("dolbum");
        gambler.addCard(Card.of(Suit.HEARTS, Denomination.KING));
        gambler.addCard(Card.of(Suit.SPADES, Denomination.KING));
        gambler.addCard(Card.of(Suit.CLUBS, Denomination.KING));

        // when
        boolean isBust = gambler.isBust();

        // then
        assertThat(isBust).isTrue();
    }
}
