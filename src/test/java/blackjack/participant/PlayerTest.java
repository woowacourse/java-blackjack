package blackjack.participant;

import blackjack.card.Card;
import blackjack.card.Number;
import blackjack.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {
    private static final Card TWO_DIAMOND = new Card(Number.TWO, Shape.DIAMOND);
    private static final Card JACK_SPADE = new Card(Number.JACK, Shape.SPADE);
    private static final Card THREE_HEART = new Card(Number.THREE, Shape.HEART);
    private static final Card ACE_CLOVER = new Card(Number.ACE, Shape.CLOVER);
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("bob");
    }

    @Test
    void isBlackjack() {
        player.addCard(ACE_CLOVER);
        player.addCard(JACK_SPADE);
        assertTrue(player.isBlackJack());
    }

    @Test
    void noneBlackjack() {
        player.addCard(TWO_DIAMOND);
        player.addCard(THREE_HEART);
        assertFalse(player.isBlackJack());
    }

}

/*
return 없고, 값 WinCount

승패 판단하기
- 플레이어 isBlackJack 승리
- 플레이어 isBurst 패배

딜러와의 값비교
24를 넘겨줌.
 dealer burst<=

- 딜러랑 값 비교(24)
  - 딜러 21넘겻는지지
무승부부
 */