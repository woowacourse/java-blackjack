package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;
    private Deck deck;
    private Money money;

    @BeforeEach
    void setUp() {
        deck = Deck.create();
        money = Money.makeMoneyInt(1000);
        player = Player.of("John", deck, money);
    }

    @Test
    @DisplayName("플레이어가 초기화될 때 이름과 2장의 카드를 가지는지 확인")
    void testPlayerInitialization() {
        assertEquals("John", player.getName(), "플레이어 이름이 올바르게 설정되어야 합니다.");
        assertEquals(2, player.getCards().size(), "플레이어는 초기화 시 2장의 카드를 가져야 합니다.");
    }

    @Test
    @DisplayName("플레이어 이름이 null 또는 비어있을 경우 예외 발생")
    void testPlayerNameValidation() {
        assertThrows(IllegalArgumentException.class, () -> Player.of(null, deck, money),
                "플레이어 이름이 null이면 예외가 발생해야 합니다.");
        assertThrows(IllegalArgumentException.class, () -> Player.of(" ", deck, money), "플레이어 이름이 비어있으면 예외가 발생해야 합니다.");
    }

    @Test
    @DisplayName("플레이어의 총 점수를 반환하는지 확인")
    void testGetTotalScore() {
        int totalScore = player.getTotalScore();
        assertTrue(totalScore >= 2 && totalScore <= 21, "플레이어의 총 점수는 2 이상 21 이하이어야 합니다.");
    }

    @Test
    @DisplayName("플레이어가 블랙잭인지 확인")
    void testIsBlackJack() {
        boolean isBlackJack = player.isBlackJack();
        assertFalse(isBlackJack, "플레이어는 초기화 시 블랙잭 상태가 아닐 가능성이 높아야 합니다.");
    }

    @Test
    @DisplayName("플레이어가 버스트 상태인지 확인")
    void testIsBust() {
        boolean isBust = player.isBust();
        assertFalse(isBust, "플레이어는 초기화 시 버스트 상태가 아니어야 합니다.");
    }

    @Test
    @DisplayName("플레이어가 카드를 추가할 수 있는지 확인")
    void testAddCard() {
        int initialCardCount = player.getCards().size();
        player.addCard();
        assertEquals(initialCardCount + 1, player.getCards().size(), "카드 추가 후 플레이어의 카드 수가 1 증가해야 합니다.");
    }

    @Test
    @DisplayName("플레이어가 베팅을 처리할 수 있는지 확인")
    void testProcessBetting() {
        double rate = 1.5;
        int initialMoney = player.getSeedMoney();
        player.processBetting(rate);
        assertEquals((int) Math.round(initialMoney * rate), player.getEarnMoney(), "베팅 후 플레이어의 금액이 올바르게 계산되어야 합니다.");
    }
}
