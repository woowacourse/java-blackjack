package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GuestTest {
    @DisplayName("참여자가 가진 카드들의 합을 반환한다.")
    @Test
    void Should_ReturnScore_When_Request() {
        Card card1 = new Card(Symbol.SPADE, CardValue.FIVE);
        Card card2 = new Card(Symbol.CLOVER, CardValue.EIGHT);
        Guest guest = new Guest(new Name("pobi"), card1, card2);

        assertThat(guest.getScore()).isEqualTo(13);
    }

    @DisplayName("참여자가 카드를 추가하면 가지고 있는 카드의 개수가 늘어난다")
    @Test
    void Should_SizePlusOne_When_AddCard() {
        Card card1 = new Card(Symbol.SPADE, CardValue.FIVE);
        Card card2 = new Card(Symbol.CLOVER, CardValue.EIGHT);
        Guest guest = new Guest(new Name("pobi"), card1, card2);
        guest.addCard(new Card(Symbol.HEART, CardValue.QUEEN));

        assertThat(guest.getCards().size()).isEqualTo(3);
    }
}
