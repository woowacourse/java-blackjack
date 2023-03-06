package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GuestTest {
    @DisplayName("게스트가 가진 카드들의 합이 21미만이면, true를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreUnder21() {
        Player guest = new Guest(new Name("name"));
        Card spade5Card = new Card(Symbol.SPADE, CardValue.FIVE);
        Card clover8Card = new Card(Symbol.CLOVER, CardValue.EIGHT);
        guest.addCard(spade5Card);
        guest.addCard(clover8Card);

        assertThat(guest.canHit()).isEqualTo(true);
    }

    @DisplayName("게스트가 가진 카드들의 합이 21이면, false를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreIs21() {
        Player guest = new Guest(new Name("name"));
        Card spadeJCard = new Card(Symbol.SPADE, CardValue.JACK);
        Card cloverKCard = new Card(Symbol.CLOVER, CardValue.KING);
        Card heartACard = new Card(Symbol.HEART, CardValue.ACE);

        guest.addCard(spadeJCard);
        guest.addCard(cloverKCard);
        guest.addCard(heartACard);

        assertThat(guest.canHit()).isEqualTo(false);
    }

    @DisplayName("게스트가 가진 카드들의 합이 21을 초과하면, false를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreOver21() {
        Player guest = new Guest(new Name("name"));
        Card spadeJCard = new Card(Symbol.SPADE, CardValue.JACK);
        Card cloverKCard = new Card(Symbol.CLOVER, CardValue.KING);
        Card heartKCard = new Card(Symbol.HEART, CardValue.KING);

        guest.addCard(spadeJCard);
        guest.addCard(cloverKCard);
        guest.addCard(heartKCard);

        assertThat(guest.canHit()).isEqualTo(false);
    }
}
