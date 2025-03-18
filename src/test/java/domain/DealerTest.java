package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    private Dealer dealer;
    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = Deck.create();
        dealer = Dealer.initiallizeDealer(deck);
    }

    @Test
    @DisplayName("딜러가 초기화될 때 2장의 카드를 가지고 있는지 확인")
    void testDealerInitialization() {
        List<TrumpCard> cards = dealer.getCards();
        assertEquals(2, cards.size(), "딜러는 초기화 시 2장의 카드를 가지고 있어야 합니다.");
    }

    @Test
    @DisplayName("딜러의 첫 번째 카드를 반환하는지 확인")
    void testRetrieveFirstCard() {
        TrumpCard firstCard = dealer.retrieveFirstCard();
        assertNotNull(firstCard, "딜러의 첫 번째 카드는 null이 아니어야 합니다.");
    }

    @Test
    @DisplayName("딜러의 총 점수를 반환하는지 확인")
    void testGetTotalScore() {
        int totalScore = dealer.getTotalScore();
        assertTrue(totalScore >= 2 && totalScore <= 21, "딜러의 총 점수는 2 이상 21 이하이어야 합니다.");
    }

    @Test
    @DisplayName("딜러가 블랙잭인지 확인")
    void testIsBlackJack() {
        boolean isBlackJack = dealer.isBlackJack();
        assertFalse(isBlackJack, "딜러는 초기화 시 블랙잭일 가능성이 낮아야 합니다.");
    }

    @Test
    @DisplayName("딜러가 버스트인지 확인")
    void testIsBust() {
        boolean isBust = dealer.isBust();
        assertFalse(isBust, "딜러는 초기화 시 버스트 상태가 아니어야 합니다.");
    }

    @Test
    @DisplayName("딜러가 히트 가능한 상태인지 확인")
    void testIsHitable() {
        boolean isHitable = dealer.isHitable();
        assertTrue(isHitable || dealer.getTotalScore() >= 17, "딜러는 점수가 17 미만일 때 히트 가능해야 합니다.");
    }

    @Test
    @DisplayName("딜러가 히트를 처리하고 올바른 카드 수를 추가하는지 확인")
    void testProcessDealerHit() {
        int initialCardCount = dealer.getCards().size();
        int hitCount = dealer.processDealerHit();
        assertEquals(initialCardCount + hitCount, dealer.getCards().size(), "히트 후 딜러의 카드 수가 올바르게 증가해야 합니다.");
    }

    @Test
    @DisplayName("딜러의 돈 처리가 올바르게 동작하는지 확인")
    void testProcessBetting() {
        int initialMoney = dealer.getTotalMoney();
        dealer.processBetting(100);
        assertEquals(initialMoney + 100, dealer.getTotalMoney(), "베팅 후 딜러의 총 금액이 올바르게 증가해야 합니다.");
    }
}
