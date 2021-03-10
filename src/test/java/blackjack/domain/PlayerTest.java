package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("pobi");
    }

    @Test
    @DisplayName("이름에 공백 입력 경우 예외 처리")
    void playerNameSplitException() {
        String input = "pobi, jason";
        assertThatThrownBy(() -> {
            for (String name : input.split(",")) {
                new Player(name);
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름이 없을 경우 예외 처리")
    void playerNameLengthException() {
        String name = "";
        assertThatThrownBy(() -> {
            new Player(name);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어 점수 계산")
    void checkReceiveCard() {
        Card card = new Card(CardPattern.CLOVER, CardNumber.TEN);
        player.receiveCard(card);
        assertEquals(player.getPoint(), 10);
    }


    @Test
    @DisplayName("플레이어가 카드를 받을 수 있는지 확인")
    void playerPossibleReceiveCard() {
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.KING));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.KING));
        assertTrue(player.canReceiveCard());
    }

    @Test
    @DisplayName("플레이어가 카드를 받을 수 없는지 확인")
    void playerImpossibleReceiveCard() {
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.KING));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.KING));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.TWO));
        assertFalse(player.canReceiveCard());
    }

    @Test
    @DisplayName("에이스가 11이어야할 때")
    void aceCardScoring() {
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.TEN));
        assertEquals(player.getPoint(), 21);
    }

    @Test
    @DisplayName("에이스가 1이어야할 때")
    void aceCardBoundary() {
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.KING));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.KING));
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
        assertEquals(player.getPoint(), 21);
    }

    @Test
    @DisplayName("기본 배팅 금액 확인")
    void Betting() {
        assertEquals(player.getMoney(), 0);
    }

    @Test
    @DisplayName("임의 배팅 금액 확인")
    void MoreBetting() {
        Player testUser = new Player("testUser", "10000");

        assertEquals(testUser.getMoney(), 10000);
    }

    @Test
    @DisplayName("올바르지 않은 배팅 금액 확인")
    void ErrorBetting() {
        assertThatThrownBy(() -> {
            Player testUser = new Player("testUser", "100 00");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("소숫값 입력 금액은 불가능합니다.")
    void FloatBetting() {
        assertThatThrownBy(() -> {
            Player testUser = new Player("testUser", "100.5");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("배팅 금액은 0원 이상이여야 합니다.")
    void MinusBetting() {
        assertThatThrownBy(() -> {
            Player testUser = new Player("testUser", "-100");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
