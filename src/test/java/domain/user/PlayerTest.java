package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어의 이름과 상태로 플레이어를 생성한다.")
    @Test
    void create() {
        Player player = new Player("split");
        assertThat(player.getName()).isEqualTo("split");
    }

    @DisplayName("플레이어에게 카드를 지급한다.")
    @Test
    void dealt() {
        Player player = new Player("split");
        Card card = new Card(Denomination.JACK, Suit.DIAMOND);
        player.dealt(card);
        List<Card> hand = player.getHand();
        assertThat(hand).containsExactly(card);
    }

    @DisplayName("게임을 시작할 카드를 반환한다")
    @Test
    void getInitialHand() {
        Player player = new Player("split");
        Card firstCard = new Card(Denomination.JACK, Suit.DIAMOND);
        Card secondCard = new Card(Denomination.ACE, Suit.DIAMOND);
        player.dealt(firstCard);
        player.dealt(secondCard);
        assertThat(player.getInitialHand()).containsExactly(firstCard, secondCard);
    }

    @DisplayName("카드를 지급받지 않고 게임을 시작할 카드를 조회하면 오류를 던진다")
    @Test
    void getInitialHandWithoutCard() {
        Player player = new Player("split");
        assertThatThrownBy(player::getInitialHand)
            .isExactlyInstanceOf(IllegalStateException.class)
            .hasMessage("모든 플레이어는 카드 두장을 받고 시작해야 합니다.");
    }

    @DisplayName("가지고 있는 모든 카드를 반환한다.")
    @Test
    void getHand() {
        Player player = new Player("split");
        Card firstCard = new Card(Denomination.SIX, Suit.DIAMOND);
        Card secondCard = new Card(Denomination.ACE, Suit.DIAMOND);
        Card thirdCard = new Card(Denomination.TWO, Suit.DIAMOND);
        Card fourthCard = new Card(Denomination.THREE, Suit.DIAMOND);
        player.dealt(firstCard);
        player.dealt(secondCard);
        player.dealt(thirdCard);
        player.dealt(fourthCard);
        assertThat(player.getHand()).containsExactly(firstCard, secondCard, thirdCard, fourthCard);
    }

    @DisplayName("가지고 있는 카드로 점수를 계산하여 반환한다.")
    @Nested
    class GetPoint {

        @DisplayName("ACE를 가지고 있지 않은 경우")
        @Test
        void notContainsAce() {
            Player player = new Player("split");
            Card firstCard = new Card(Denomination.SIX, Suit.DIAMOND);
            Card secondCard = new Card(Denomination.SEVEN, Suit.DIAMOND);
            player.dealt(firstCard);
            player.dealt(secondCard);
            assertThat(player.getPoint()).isEqualTo(13);
        }

        @DisplayName("ACE를 11로 계산하는 경우")
        @Test
        void useAceAsEleven() {
            Player player = new Player("split");
            Card firstCard = new Card(Denomination.ACE, Suit.DIAMOND);
            Card secondCard = new Card(Denomination.SEVEN, Suit.DIAMOND);
            player.dealt(firstCard);
            player.dealt(secondCard);
            assertThat(player.getPoint()).isEqualTo(18);
        }

        @DisplayName("ACE를 1로 계산하는 경우")
        @Test
        void useAceAsOne() {
            Player player = new Player("split");
            Card firstCard = new Card(Denomination.JACK, Suit.DIAMOND);
            Card secondCard = new Card(Denomination.QUEEN, Suit.DIAMOND);
            Card thirdCard = new Card(Denomination.ACE, Suit.DIAMOND);
            player.dealt(firstCard);
            player.dealt(secondCard);
            player.dealt(thirdCard);
            assertThat(player.getPoint()).isEqualTo(21);
        }
    }
}
