package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GuestTest {
    private static final BettingMoney thousandBetting = BettingMoney.of(1000);
    @DisplayName("게스트가 가진 카드들의 합이 21미만이면, true를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreUnder21() {
        Card spade5 = new Card(Symbol.SPADE, CardValue.FIVE);
        Card clover8 = new Card(Symbol.CLOVER, CardValue.EIGHT);
        Hand hand = new Hand(spade5, clover8);
        Player guest = new Guest(new Name("name"), hand, thousandBetting);

        assertThat(guest.canHit()).isEqualTo(true);
    }

    @DisplayName("게스트가 가진 카드들의 합이 21이면, false를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreIs21() {
        Card spadeJ = new Card(Symbol.SPADE, CardValue.JACK);
        Card cloverK = new Card(Symbol.CLOVER, CardValue.KING);
        Hand hand = new Hand(spadeJ, cloverK);
        Card heartA = new Card(Symbol.HEART, CardValue.ACE);

        Player guest = new Guest(new Name("name"), hand, thousandBetting);
        guest.addCard(heartA);

        assertThat(guest.canHit()).isEqualTo(false);
    }

    @DisplayName("게스트가 가진 카드들의 합이 21을 초과하면, false를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreOver21() {
        Card spadeJ = new Card(Symbol.SPADE, CardValue.JACK);
        Card cloverK = new Card(Symbol.CLOVER, CardValue.KING);
        Hand hand = new Hand(spadeJ, cloverK);
        Card heartK = new Card(Symbol.HEART, CardValue.KING);

        Player guest = new Guest(new Name("name"), hand, thousandBetting);
        guest.addCard(heartK);

        assertThat(guest.canHit()).isEqualTo(false);
    }
}
