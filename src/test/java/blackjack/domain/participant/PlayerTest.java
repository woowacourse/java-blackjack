package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("버스트가 아니고 히트 의사가 있으면 카드를 받을 수 있다")
    void canReceiveCard_returnsTrue_whenNotBustAndWantsHit() {
        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.HEART, Rank.TWO));

        assertThat(player.canReceiveCard()).isTrue();
    }

    @Test
    @DisplayName("버스트이면 카드를 받을 수 없다")
    void canReceiveCard_returnsFalse_whenBust() {
        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.TEN));
        player.receiveCard(new Card(Suit.CLUB, Rank.TEN));

        assertThat(player.canReceiveCard()).isFalse();
    }

    @Test
    @DisplayName("히트를 거부하면 카드를 받을 수 없다")
    void canReceiveCard_returnsFalse_whenStay() {
        Player player = new Player("pobi");
        player.stay();

        assertThat(player.canReceiveCard()).isFalse();
    }

    @Test
    @DisplayName("이름이 빈 문자열이면 예외가 발생한다")
    void constructor_throwsException_whenNameIsBlank() {
        assertThatThrownBy(() -> new Player(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백일 수 없습니다.");
    }

    @Test
    @DisplayName("점수가 정확히 21이면 카드를 받을 수 있다")
    void canReceiveCard_returnsTrue_whenScoreIsExactlyTwentyOne() {
        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.HEART, Rank.ACE));
        player.receiveCard(new Card(Suit.SPADE, Rank.TEN));

        assertThat(player.canReceiveCard()).isTrue();
    }
}
