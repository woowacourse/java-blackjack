package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @DisplayName("카드 뭉치 두 개를 비교했을때 승리 결과가 나온다.")
    @Test
    void testCompareWin() {
        Cards cards1 = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN),
                new Card(Type.CLUB, Denomination.FIVE)
            )
        );

        Cards cards2 = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN),
                new Card(Type.CLUB, Denomination.SIX)
            )
        );

        assertThat(cards1.compare(cards2)).isEqualTo(Result.WIN);
    }

    @DisplayName("카드 뭉치 두 개를 비교했을때 무승부 결과가 나온다.")
    @Test
    void testCompareDraw() {
        Cards cards1 = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN),
                new Card(Type.CLUB, Denomination.FIVE)
            )
        );

        Cards cards2 = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN),
                new Card(Type.CLUB, Denomination.FIVE)
            )
        );

        assertThat(cards1.compare(cards2)).isEqualTo(Result.DRAW);
    }

    @DisplayName("카드 뭉치 두 개를 비교했을때 실패 결과가 나온다.")
    @Test
    void testCompareLose() {
        Cards cards1 = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN),
                new Card(Type.CLUB, Denomination.FIVE)
            )
        );

        Cards cards2 = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN),
                new Card(Type.CLUB, Denomination.THREE)
            )
        );

        assertThat(cards1.compare(cards2)).isEqualTo(Result.LOSE);
    }


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

    @DisplayName("카드 뭉치가 블랙잭이 아닌 것 확인한다.")
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
    void testCardsIsBurst(){
        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN),
                new Card(Type.HEART, Denomination.TEN),
                new Card(Type.SPADE, Denomination.TEN)
            )
        );
        assertThat(cards.isBurst()).isEqualTo(true);
    }

    @DisplayName("카드 뭉치가 버스트인 것을 확인한다.")
    @Test
    void testCardsIsNotBurst(){
        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN),
                new Card(Type.HEART, Denomination.TEN)
            )
        );
        assertThat(cards.isBurst()).isEqualTo(false);
    }

}