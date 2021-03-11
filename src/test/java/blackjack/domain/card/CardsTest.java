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
                new Card(Type.CLUB, Denomination.ACE_ELEVEN),
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
        assertThat(cards.isBust()).isEqualTo(true);
    }

    @DisplayName("카드 뭉치가 버스트가 아닌 것을 확인한다.")
    @Test
    void testCardsIsNotBurst() {
        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.TEN),
                new Card(Type.HEART, Denomination.TEN)
            )
        );
        assertThat(cards.isBust()).isEqualTo(false);
    }

    @DisplayName("초기 드로우는 카드 2장을 드로우한다.")
    @Test
    void cardsInitialDraw() {
        Deck deck = new Deck(new Cards(Arrays.asList(
            new Card(Type.CLUB, Denomination.TEN),
            new Card(Type.HEART, Denomination.TEN)
        )));

        Cards cards = new Cards();
        cards.initialDraw(deck);
        assertThat(cards.cardsSize()).isEqualTo(2);
    }

    @DisplayName("카드 점수 총합을 계산한다.")
    @Test
    void testCalculateCardsScore() {
        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.FOUR),
                new Card(Type.HEART, Denomination.THREE),
                new Card(Type.HEART, Denomination.FIVE)
            )
        );
        assertThat(cards.getScore()).isEqualTo(12);
    }

    @DisplayName("카드 점수 총합이 21을 초과하지 않으면 ACE를 11점으로 계산한다.")
    @Test
    void testCalculateCardsScoreElvenAce() {
        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.ACE),
                new Card(Type.HEART, Denomination.TEN)
            )
        );
        assertThat(cards.getScore()).isEqualTo(21);
    }

    @DisplayName("카드 점수 총합이 21을 초과하지 않으면 ACE를 1점으로 계산한다.")
    @Test
    void testCalculateCardsScoreOneAce() {
        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.ACE),
                new Card(Type.HEART, Denomination.TEN),
                new Card(Type.HEART, Denomination.FIVE)
            )
        );
        assertThat(cards.getScore()).isEqualTo(16);
    }

}