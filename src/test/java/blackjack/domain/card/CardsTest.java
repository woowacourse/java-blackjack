package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @DisplayName("카드 뭉치 a, b 둘 다 블랙잭일 경우 비긴다.")
    @Test
    void testBothBlackJackDrawResult() {
        Cards aCards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.KING),
                new Card(Type.CLUB, Denomination.ACE)
            )
        );

        Cards bCards = new Cards(
            Arrays.asList(
                new Card(Type.HEART, Denomination.KING),
                new Card(Type.HEART, Denomination.ACE)
            )
        );

        assertThat(aCards.getOtherCardsCompareResult(bCards)).isEqualTo(Result.DRAW);
    }

    @DisplayName("카드 뭉치 a, b 둘 다 버스트 경우 비긴다.")
    @Test
    void testBothBurstDrawResult() {
        Cards aCards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN),
                new Card(Type.CLUB, Denomination.NINE),
                new Card(Type.CLUB, Denomination.SIX)
            )
        );

        Cards bCards = new Cards(
            Arrays.asList(
                new Card(Type.HEART, Denomination.TEN),
                new Card(Type.HEART, Denomination.NINE),
                new Card(Type.HEART, Denomination.SIX)
            )
        );

        assertThat(aCards.getOtherCardsCompareResult(bCards)).isEqualTo(Result.DRAW);
    }

    @DisplayName("카드 뭉치 a, b 중 b만 블랙잭인 경우 b가 이긴다.")
    @Test
    void testBlackJackWinResult() {
        Cards aCards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN),
                new Card(Type.CLUB, Denomination.NINE)
            )
        );

        Cards bCards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.ACE),
                new Card(Type.SPADE, Denomination.KING)
            )
        );

        assertThat(aCards.getOtherCardsCompareResult(bCards)).isEqualTo(Result.WIN);
    }

    @DisplayName("카드 뭉치 a, b 중 b만 버스트인 경우 b가 진다.")
    @Test
    void testBurstLoseResult() {
        Cards aCards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN),
                new Card(Type.CLUB, Denomination.NINE)
            )
        );

        Cards bCards = new Cards(
            Arrays.asList(
                new Card(Type.HEART, Denomination.TEN),
                new Card(Type.HEART, Denomination.FIVE),
                new Card(Type.SPADE, Denomination.TEN)
            )
        );

        assertThat(aCards.getOtherCardsCompareResult(bCards)).isEqualTo(Result.LOSE);
    }

    @DisplayName("카드 뭉치 a, b 중 b가 카드 점수가 높아서 이긴다.")
    @Test
    void testCardsScoreWinResult() {
        Cards aCards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.THREE),
                new Card(Type.CLUB, Denomination.FOUR)
            )
        );

        Cards bCards = new Cards(
            Arrays.asList(
                new Card(Type.HEART, Denomination.TEN),
                new Card(Type.HEART, Denomination.FIVE)
            )
        );

        assertThat(aCards.getOtherCardsCompareResult(bCards)).isEqualTo(Result.WIN);
    }

    @DisplayName("카드 뭉치 a, b 카드 점수가 같아서 비긴다.")
    @Test
    void testCardsScoreDrawResult() {
        Cards aCards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.THREE),
                new Card(Type.CLUB, Denomination.FOUR)
            )
        );

        Cards bCards = new Cards(
            Arrays.asList(
                new Card(Type.HEART, Denomination.THREE),
                new Card(Type.HEART, Denomination.FOUR)
            )
        );

        assertThat(aCards.getOtherCardsCompareResult(bCards)).isEqualTo(Result.DRAW);
    }

    // 랄랄
    @DisplayName("카드 뭉치 a, b 중 b가 카드 점수가 낮아서 진다.")
    @Test
    void testCardsScoreLoseResult() {
        Cards aCards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN),
                new Card(Type.CLUB, Denomination.FOUR)
            )
        );

        Cards bCards = new Cards(
            Arrays.asList(
                new Card(Type.HEART, Denomination.THREE),
                new Card(Type.HEART, Denomination.FOUR)
            )
        );

        assertThat(aCards.getOtherCardsCompareResult(bCards)).isEqualTo(Result.LOSE);
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

    @DisplayName("카드 뭉치가 버스트인 것을 확인한다.")
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