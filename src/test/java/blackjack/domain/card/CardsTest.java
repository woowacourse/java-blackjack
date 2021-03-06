package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @DisplayName("카드 뭉치가 블랙잭인 것을 확인한다.")
    @Test
    void testIsBlackJack() {
        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.ACE),
                new Card(Type.CLUB, Denomination.TEN)
            )
        );
        assertThat(cards.isBlackJack()).isEqualTo(true);
    }

    @DisplayName("카드 뭉치가 블랙잭이 아닌 것을 확인한다.")
    @Test
    void testIsNotBlackJack() {
        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.THREE),
                new Card(Type.CLUB, Denomination.FOUR)
            )
        );
        assertThat(cards.isBlackJack()).isEqualTo(false);
    }

    @DisplayName("카드 뭉치가 버스트인 것을 확인한다.")
    @Test
    void testCardsIsBurst() {
        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN),
                new Card(Type.HEART, Denomination.TEN),
                new Card(Type.SPADE, Denomination.TEN)
            )
        );
        assertThat(cards.isBurst()).isEqualTo(true);
    }

    @DisplayName("카드 뭉치가 버스트가 아 것을 확인한다.")
    @Test
    void testCardsIsNotBurst() {
        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN),
                new Card(Type.HEART, Denomination.TEN)
            )
        );
        assertThat(cards.isBurst()).isEqualTo(false);
    }

}