package domain.user;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlayerTest {
    @Test
    @DisplayName("점수를 계산한다.")
    void 점수_계산() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADE, Rank.TWO));
        hand.add(new Card(Suit.CLOVER, Rank.KING));
        Player player = new Player(
                new UserInformation(new PlayerName("name"), new BettingAmount(1_000)),
                hand);
        assertThat(player.calculateScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("이름을 반환한다.")
    void 이름_반환() {
        String nameValue = "Player";
        Player player = Player.of("Player", 1_000);
        assertThat(player.getNameValue()).isEqualTo(nameValue);
    }

    @Test
    @DisplayName("카드를 더 받을 수 있는 경우 true를 반환한다.")
    void 카드_추가_가능() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADE, Rank.TWO));
        hand.add(new Card(Suit.CLOVER, Rank.KING));
        Player player = new Player(
                new UserInformation(new PlayerName("name"), new BettingAmount(1_000)),
                hand);
        assertThat(player.canAdd()).isTrue();
    }

    @Test
    @DisplayName("카드를 더 받을 수 없는 경우 false를 반환한다.")
    void 카드_추가_불가능() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADE, Rank.ACE));
        hand.add(new Card(Suit.CLOVER, Rank.KING));
        hand.add(new Card(Suit.CLOVER, Rank.KING));
        Player player = new Player(
                new UserInformation(new PlayerName("name"), new BettingAmount(1_000)),
                hand);
        assertThat(player.canAdd()).isFalse();
    }

    @Test
    @DisplayName("카드를 저장한다.")
    void 카드_저장() {
        Player player = Player.of("Player", 1_000);
        assertThat(player.calculateScore()).isEqualTo(0);
        player.addCard(new Card(Suit.SPADE, Rank.TWO));
        assertThat(player.calculateScore()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드를 추가하는 것이 불가능할 때 추가하려는 경우 예외가 발생한다.")
    void 카드_저장_불가능시_예외() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADE, Rank.ACE));
        hand.add(new Card(Suit.CLOVER, Rank.KING));
        hand.add(new Card(Suit.CLOVER, Rank.KING));
        Player player = new Player(
                new UserInformation(new PlayerName("name"), new BettingAmount(1_000)),
                hand);
        assertThatThrownBy(() -> player.addCard(new Card(Suit.SPADE, Rank.ACE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드 추가가 불가능하여 실행되지 않았습니다.");
    }

    @Test
    @DisplayName("Player가 가진 카드가 블랙잭이면 true를 반환한다.")
    void 카드_블랙잭_인지_확인() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADE, Rank.ACE));
        hand.add(new Card(Suit.CLOVER, Rank.KING));
        Player player = new Player(
                new UserInformation(new PlayerName("name"), new BettingAmount(1_000)),
                hand);
        assertThat(player.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("Player가 가진 카드의 점수의 합이 블랙잭이 아니면 false를 반환한다.")
    void 카드_블랙잭_아닌지_확인() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.CLOVER, Rank.KING));
        hand.add(new Card(Suit.CLOVER, Rank.KING));
        Player player = new Player(
                new UserInformation(new PlayerName("name"), new BettingAmount(1_000)),
                hand);
        assertThat(player.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("갖고 있는 카드의 점수가 블랙잭 점수를 초과하면 true를 반환한다.")
    void 블랙잭_초과() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.CLOVER, Rank.KING));
        hand.add(new Card(Suit.CLOVER, Rank.KING));
        hand.add(new Card(Suit.CLOVER, Rank.TWO));
        Player player = new Player(
                new UserInformation(new PlayerName("name"), new BettingAmount(1_000)),
                hand);
        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("갖고 있는 카드의 점수가 블랙잭 점수를 초과하지 않으면 false를 반환한다.")
    void 블랙잭_이하() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.CLOVER, Rank.KING));
        hand.add(new Card(Suit.CLOVER, Rank.KING));
        hand.add(new Card(Suit.CLOVER, Rank.ACE));
        Player player = new Player(
                new UserInformation(new PlayerName("name"), new BettingAmount(1_000)),
                hand);
        assertThat(player.isBust()).isFalse();
    }

    @Test
    @DisplayName("베팅 금액을 반환한다.")
    void 베팅_금액_반환() {
        int bettingAmountValue = 7_000;
        Player player = Player.of("Player", bettingAmountValue);
        assertThat(player.getBettingAmountValue()).isEqualTo(bettingAmountValue);
    }
}
