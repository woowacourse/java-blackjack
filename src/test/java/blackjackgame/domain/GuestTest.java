package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GuestTest {
    @DisplayName("게스트가 가진 카드들의 합이 21미만이면, true를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreUnder21() {
        Player guest = new Guest(new Name("name"));
        Card card1 = new Card(Symbol.SPADE, CardValue.FIVE);
        Card card2 = new Card(Symbol.CLOVER, CardValue.EIGHT);
        guest.addCard(card1);
        guest.addCard(card2);

        assertThat(guest.canHit()).isEqualTo(true);
    }

    @DisplayName("게스트가 가진 카드들의 합이 21이상이면, false를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreOver21() {
        Player guest = new Guest(new Name("name"));
        Card card1 = new Card(Symbol.SPADE, CardValue.JACK);
        Card card2 = new Card(Symbol.CLOVER, CardValue.KING);
        Card card3 = new Card(Symbol.HEART, CardValue.KING);

        guest.addCard(card1);
        guest.addCard(card2);
        guest.addCard(card3);

        assertThat(guest.canHit()).isEqualTo(false);
    }
}
