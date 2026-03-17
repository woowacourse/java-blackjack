package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void beforeEach() {
        player = new Player("아나키", 1_000);
    }

    @DisplayName("공백이 들어오면 예외처리한다")
    @Test
    void 공백_들어오면_예외처리한다() {
        assertThatThrownBy(() -> new Player(" ", 1_000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("일반 카드의 점수를 합산한다")
    @Test
    void 일반_카드의_점수를_합산한다() {
        player.addCard(new Card(Rank.KING, Suit.SPADE));  // 10
        player.addCard(new Card(Rank.FIVE, Suit.HEART));  // 5
        assertThat(player.getScore()).isEqualTo(15);
    }

    @DisplayName("ACE가 있고 버스트가 아니면 11로 계산한다")
    @Test
    void ACE가_있고_버스트가_아니면_11로_계산한다() {
        player.addCard(new Card(Rank.ACE, Suit.SPADE));   // 11
        player.addCard(new Card(Rank.KING, Suit.HEART));  // 10
        assertThat(player.getScore()).isEqualTo(21);
    }

    @DisplayName("ACE가 있고 버스트면 1로 계산한다")
    @Test
    void ACE가_있고_버스트면_1로_계산한다() {
        player.addCard(new Card(Rank.ACE, Suit.SPADE));    // 1
        player.addCard(new Card(Rank.KING, Suit.HEART));   // 10
        player.addCard(new Card(Rank.FIVE, Suit.DIAMOND)); // 5
        assertThat(player.getScore()).isEqualTo(16);
    }

    @DisplayName("ACE가 여러 장이면 하나만 11로 계산한다")
    @Test
    void ACE가_여러_장이면_하나만_11로_계산한다() {
        player.addCard(new Card(Rank.ACE, Suit.SPADE));  // 11
        player.addCard(new Card(Rank.ACE, Suit.HEART));  // 1
        assertThat(player.getScore()).isEqualTo(12);
    }

    @DisplayName("점수가 21 이하면 히트할 수 있다")
    @Test
    void 점수가_21이하면_히트할_수_있다() {
        player.addCard(new Card(Rank.KING, Suit.SPADE));  // 10
        player.addCard(new Card(Rank.FIVE, Suit.HEART));  // 5
        assertThat(player.canHit()).isTrue();
    }

    @DisplayName("점수가 22 이상이면 히트할 수 없다")
    @Test
    void 점수가_22이상이면_히트할_수_없다() {
        player.addCard(new Card(Rank.KING, Suit.SPADE));   // 10
        player.addCard(new Card(Rank.KING, Suit.HEART));   // 10
        player.addCard(new Card(Rank.TWO, Suit.DIAMOND));  // 2
        assertThat(player.canHit()).isFalse();
    }

    @DisplayName("블랙잭이면 히트할 수 없다")
    @Test
    void 블랙잭이면_히트할_수_없다() {
        player.addCard(new Card(Rank.ACE, Suit.SPADE));
        player.addCard(new Card(Rank.KING, Suit.HEART));
        assertThat(player.canHit()).isFalse();
    }
}
