package blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.player.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드의 점수를 정확하게 계산한다.")
    void calculateCardTest() {
        Card card = new Card(Shape.SPADE, Rank.ACE);
        assertThat(card.getScore()).isEqualTo(new Score(1));
    }

    @Test
    @DisplayName("카드가 에이스인지 확인한다.")
    void isAceTest() {
        Card aceCard = new Card(Shape.HEART, Rank.ACE);
        Card nonAceCard = new Card(Shape.CLOVER, Rank.JACK);

        assertAll(
                () -> assertThat(aceCard.isAce()).isTrue(),
                () -> assertThat(nonAceCard.isAce()).isFalse()
        );
    }
}
