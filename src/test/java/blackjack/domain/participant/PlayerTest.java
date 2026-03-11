package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @Test
    void 버스트가_아니면_카드를_받을_수_있다() {
        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.HEART, Rank.TWO));

        assertThat(player.canReceiveCard()).isTrue();
    }

    @Test
    void 버스트이면_카드를_받을_수_없다() {
        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.TEN));
        player.receiveCard(new Card(Suit.CLUB, Rank.TEN));

        assertThat(player.canReceiveCard()).isFalse();
    }

    @Test
    void 이름이_빈_문자열이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Player(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백일 수 없습니다.");
    }

    @Test
    void 점수가_정확히_21이면_카드를_받을_수_있다() {
        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.HEART, Rank.ACE));
        player.receiveCard(new Card(Suit.SPADE, Rank.TEN));

        assertThat(player.canReceiveCard()).isTrue();
    }
}
