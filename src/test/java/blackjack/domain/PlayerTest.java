package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;

public class PlayerTest {

    @DisplayName("카드 받는 기능 테스트")
    @Test
    void addCard() {
        Player player = new Player("pobi", 1000);
        player.addCard(Card.THREE_DIAMOND);
        int playerCardSize = player.getMyCards().size();
        assertThat(playerCardSize).isEqualTo(1);
    }

    @DisplayName("이름이 빈 값일 경우 에러 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void blankName(String name) {
        assertThatThrownBy(() -> new Player(name, 1000)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("베팅금액이 0이하일 경우 에러 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, -1000, -25000})
    void incorrectBettingMoney(int money) {
        assertThatThrownBy(() -> new Player("잉", money)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어가 카드를 뽑을 상황 테스트_버스트일 때")
    @Test
    void canDrawBust() {
        Player player = new Player("pobi", 1000);
        player.addCard(Card.TWO_HEART);
        player.addCard(Card.J_CLOVER);
        player.addCard(Card.J_HEART);

        assertThat(player.canDraw()).isFalse();
    }

    @DisplayName("플레이어가 카드를 뽑을 상황 테스트_블랙잭일 때")
    @Test
    void canDrawBlackjack() {
        Player player = new Player("pobi", 1000);
        player.addCard(Card.A_HEART);
        player.addCard(Card.J_CLOVER);

        assertThat(player.canDraw()).isFalse();
    }
}