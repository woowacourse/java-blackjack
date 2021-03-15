package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("상징과 숫자가 같으면 같은 카드다.")
    public void create() {
        Card card = new Card(Denomination.JACK, Suit.CLOVER);
        assertThat(card).isEqualTo(new Card(Denomination.JACK, Suit.CLOVER));
    }

    @Test
    @DisplayName("카드가 에이스인지 확인한다.")
    public void ace() {
        Card card = new Card(Denomination.QUEEN, Suit.SPADE);
        Card ace = new Card(Denomination.ACE, Suit.DIAMOND);
        assertThat(card.isAce()).isFalse();
        assertThat(ace.isAce()).isTrue();
    }

    @Test
    @DisplayName("상징과 숫자를 통해 무슨 카드인지 표현한다.")
    public void getName() {
        Card card = new Card(Denomination.THREE, Suit.DIAMOND);
        assertThat(card.getName()).isEqualTo("3다이아몬드");
    }

    @Test
    @DisplayName("카드의 숫자를 점수로 반환한다.")
    public void getScore() {
        Card card = new Card(Denomination.THREE, Suit.SPADE);
        assertThat(card.findScore()).isEqualTo(new Score(3));
    }

}
