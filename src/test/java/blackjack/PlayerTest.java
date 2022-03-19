package blackjack;

import static org.assertj.core.api.Assertions.*;

import blackjack.user.Dealer;
import blackjack.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("이름 없이 생성 안되는지 확인")
    @Test
    void generateTest() {
        assertThatThrownBy(() -> Player.generate("")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름 공백 생성 안되는지 확인하는 테스트")
    @Test
    void generateTest2() {
        assertThatThrownBy(() -> Player.generate(" ")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("초기 state가 true인지 확인하는 테스트")
    @Test
    void isHitTest() {
        assertThat(Player.generate("pobi").isHit()).isTrue();
    }

    @DisplayName("state가 stay로 설정되었을때 false인지 확인하는 테스트")
    @Test
    void isHitTest2() {
        Player player = Player.generate("pobi");
        player.setStateStayIfSatisfied(true);
        assertThat(player.isHit()).isFalse();
    }

    @DisplayName("블랙잭 조건 만족했을때 true인지 확인하는 테스트")
    @Test
    void isBlackjackTest() {
        Player player = Player.generate("pobi");
        player.addCard(Card.generate(Suit.DIAMOND, Denomination.ACE));
        player.addCard(Card.generate(Suit.DIAMOND, Denomination.Q));
        assertThat(player.isBlackjack()).isTrue();
    }

    @DisplayName("합은 21이지만 카트가 3장일때 false인지 확인하는 테스트")
    @Test
    void isBlackjackTest2() {
        Player player = Player.generate("pobi");
        player.addCard(Card.generate(Suit.DIAMOND, Denomination.SIX));
        player.addCard(Card.generate(Suit.DIAMOND, Denomination.SEVEN));
        player.addCard(Card.generate(Suit.DIAMOND, Denomination.EIGHT));
        assertThat(player.isBlackjack()).isFalse();
    }

    @Test
    void isBurstTest_true() {
        Player player = Player.generate("pobi");
        player.addCard(Card.generate(Suit.DIAMOND, Denomination.Q));
        player.addCard(Card.generate(Suit.DIAMOND, Denomination.SEVEN));
        player.addCard(Card.generate(Suit.DIAMOND, Denomination.EIGHT));
        assertThat(player.isBust()).isTrue();
    }

    @Test
    void isBurstTest_false() {
        Player player = Player.generate("pobi");
        player.addCard(Card.generate(Suit.DIAMOND, Denomination.TWO));
        player.addCard(Card.generate(Suit.DIAMOND, Denomination.SEVEN));
        player.addCard(Card.generate(Suit.DIAMOND, Denomination.EIGHT));
        assertThat(player.isBust()).isFalse();
    }

    @Test
    void pickOpenCardsTest() {
        Player player = Player.generate("pobi");
        player.addCard(Card.generate(Suit.DIAMOND, Denomination.TWO));
        player.addCard(Card.generate(Suit.DIAMOND, Denomination.SEVEN));
        assertThat(player.pickOpenCards().numberOfCards()).isEqualTo(2);
    }
}
