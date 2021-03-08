package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Deck;
import blackjack.domain.game.WinnerFlag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("플레이어 점수 계산")
    void checkReceiveCard() {
        Card card = new Card(CardPattern.CLOVER, CardNumber.TEN);
        player.receiveCard(card);
        assertEquals(10, player.makeJudgingPoint());
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
    @DisplayName("플레이어가 버스트지 확인")
    void playerIsBurst() {
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.KING));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.KING));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.TWO));
        assertTrue(player.isBurst(player.makeFinalPoint()));
    }

    @Test
    @DisplayName("에이스가 11이어야할 때")
    void aceCardScoring() {
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.TEN));
        assertEquals(21, player.makeFinalPoint());
    }

    @Test
    @DisplayName("에이스가 1이어야할 때")
    void aceCardBoundary() {
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.KING));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.KING));
        assertEquals(21, player.makeFinalPoint());
    }

    @ParameterizedTest
    @DisplayName("다시 받을 때 올바른 입력 확인")
    @CsvSource(value = {"y:true", "n:false"}, delimiter = ':')
    void playerContinueDraw(String value, boolean expected) {
        assertEquals(expected, player.continueDraw(value, new Deck()));
    }

    @Test
    @DisplayName("다시 받을 때 예외 처리")
    void playerStopDraw() {
        assertThatThrownBy(() -> player.continueDraw("l", new Deck())).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("y");
    }

    @Test
    @DisplayName("결과 매칭 테스트")
    void matchResult() {
        Dealer dealer = new Dealer();
        Player player = new Player("pobi");
        dealer.receiveCard(new Card(CardPattern.HEART, CardNumber.NINE));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.EIGHT));
        player.calculateResult(dealer);
        assertEquals(WinnerFlag.LOSE, player.getResult());
    }
}
