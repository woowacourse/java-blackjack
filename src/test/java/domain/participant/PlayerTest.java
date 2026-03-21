package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("공백이 들어오면 예외처리한다")
    @Test
    void 공백_들어오면_예외처리한다() {
        assertThatThrownBy(() -> new Player(" ", 1_000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드를 추가하면 점수에 반영된다")
    @Test
    void 카드를_추가하면_점수에_반영된다() {
        Player player = new Player("아나키", 1_000);
        player.addCard(new Card(Rank.KING, Suit.SPADE));
        player.addCard(new Card(Rank.FIVE, Suit.HEART));
        assertThat(player.getScore()).isEqualTo(15);
    }

    @DisplayName("블랙잭이 되면 더 이상 히트할 수 없다")
    @Test
    void 블랙잭이_되면_히트할_수_없다() {
        Player player = new Player("아나키", 1_000);
        player.addCard(new Card(Rank.ACE, Suit.SPADE));
        player.addCard(new Card(Rank.KING, Suit.HEART));
        assertThat(player.canHit()).isFalse();
    }

    @DisplayName("버스트가 되면 더 이상 히트할 수 없다")
    @Test
    void 버스트가_되면_히트할_수_없다() {
        Player player = new Player("아나키", 1_000);
        player.addCard(new Card(Rank.KING, Suit.SPADE));
        player.addCard(new Card(Rank.KING, Suit.HEART));
        player.addCard(new Card(Rank.TWO, Suit.DIAMOND));
        assertThat(player.canHit()).isFalse();
    }

    @DisplayName("stay하면 더 이상 히트할 수 없다")
    @Test
    void stay하면_히트할_수_없다() {
        Player player = new Player("아나키", 1_000);
        player.addCard(new Card(Rank.KING, Suit.SPADE));
        player.stay();
        assertThat(player.canHit()).isFalse();
    }

    @DisplayName("이미 완료된 상태에서 stay를 호출해도 예외가 발생하지 않는다")
    @Test
    void 완료된_상태에서_stay해도_예외가_발생하지_않는다() {
        Player player = new Player("아나키", 1_000);
        player.addCard(new Card(Rank.ACE, Suit.SPADE));
        player.addCard(new Card(Rank.KING, Suit.HEART));
        player.stay();
        assertThat(player.canHit()).isFalse();
    }
}
