package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.betting.BettingMoney;
import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("버스트가 아니고 히트 의사가 있으면 카드를 받을 수 있다")
    void canReceiveCard_returnsTrue_whenNotBustAndWantsHit() {
        // given
        Player player = new Player("pobi", new BettingMoney(1000));
        player.receiveCard(new Card(Suit.HEART, Rank.TWO));

        // when
        boolean result = player.canReceiveCard();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("버스트이면 카드를 받을 수 없다")
    void canReceiveCard_returnsFalse_whenBust() {
        // given
        Player player = new Player("pobi", new BettingMoney(1000));
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.TEN));
        player.receiveCard(new Card(Suit.CLOVER, Rank.TEN));

        // when
        boolean result = player.canReceiveCard();

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("히트를 거부하면 카드를 받을 수 없다")
    void canReceiveCard_returnsFalse_whenStay() {
        // given
        Player player = new Player("pobi", new BettingMoney(1000));
        player.stay();

        // when
        boolean result = player.canReceiveCard();

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("이름이 빈 문자열이면 예외가 발생한다")
    void constructor_throwsException_whenNameIsBlank() {
        // given & when & then
        assertThatThrownBy(() -> new Player("", new BettingMoney(1000)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백일 수 없습니다.");
    }

    @Test
    @DisplayName("점수가 정확히 21이면 카드를 받을 수 있다")
    void canReceiveCard_returnsTrue_whenScoreIsExactlyTwentyOne() {
        // given
        Player player = new Player("pobi", new BettingMoney(1000));
        player.receiveCard(new Card(Suit.HEART, Rank.ACE));
        player.receiveCard(new Card(Suit.SPADE, Rank.TEN));

        // when
        boolean result = player.canReceiveCard();

        // then
        assertThat(result).isTrue();
    }
}
