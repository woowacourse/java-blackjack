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
        assertEquals(player.calcPoint(), 10);
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
        assertEquals(player.calcPoint(), 21);
    }

    @Test
    @DisplayName("에이스가 1이어야할 때")
    void aceCardBoundary() {
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.KING));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.KING));
        assertEquals(player.calcPoint(), 21);
    }
}
