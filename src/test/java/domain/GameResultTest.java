package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    private Deck createTestDeck() {
        return Deck.create();
    }

    private Money createTestMoney(int amount) {
        return Money.makeMoneyInt(amount);
    }

    private Player createPlayerWithScore(Deck deck, Money money, int score, boolean isBlackJack, boolean isBust) {
        return new Player("TestPlayer", deck, money) {
            @Override
            public int getTotalScore() {
                return score;
            }

            @Override
            public boolean isBlackJack() {
                return isBlackJack;
            }

            @Override
            public boolean isBust() {
                return isBust;
            }
        };
    }

    private Dealer createDealerWithScore(Deck deck, Money money, int score, boolean isBlackJack, boolean isBust) {
        return new Dealer(deck, money) {
            @Override
            public int getTotalScore() {
                return score;
            }

            @Override
            public boolean isBlackJack() {
                return isBlackJack;
            }

            @Override
            public boolean isBust() {
                return isBust;
            }
        };
    }

    @Test
    @DisplayName("플레이어가 Bust 상태일 경우 LOSE 반환")
    void testPlayerBust() {
        Deck deck = createTestDeck();
        Money money = createTestMoney(1000);
        Player player = createPlayerWithScore(deck, money, 22, false, true);
        Dealer dealer = createDealerWithScore(deck, money, 20, false, false);

        assertEquals(GameResult.LOSE, GameResult.from(player, dealer));
    }

    @Test
    @DisplayName("딜러가 Bust 상태일 경우 WIN 반환")
    void testDealerBust() {
        Deck deck = createTestDeck();
        Money money = createTestMoney(1000);
        Player player = createPlayerWithScore(deck, money, 20, false, false);
        Dealer dealer = createDealerWithScore(deck, money, 22, false, true);

        assertEquals(GameResult.WIN, GameResult.from(player, dealer));
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 BlackJack일 경우 DRAW 반환")
    void testBothBlackJack() {
        Deck deck = createTestDeck();
        Money money = createTestMoney(1000);
        Player player = createPlayerWithScore(deck, money, 21, true, false);
        Dealer dealer = createDealerWithScore(deck, money, 21, true, false);

        assertEquals(GameResult.DRAW, GameResult.from(player, dealer));
    }

    @Test
    @DisplayName("플레이어만 BlackJack일 경우 BLACKJACK 반환")
    void testPlayerBlackJack() {
        Deck deck = createTestDeck();
        Money money = createTestMoney(1000);
        Player player = createPlayerWithScore(deck, money, 21, true, false);
        Dealer dealer = createDealerWithScore(deck, money, 20, false, false);

        assertEquals(GameResult.BLACKJACK, GameResult.from(player, dealer));
    }

    @Test
    @DisplayName("딜러만 BlackJack일 경우 LOSE 반환")
    void testDealerBlackJack() {
        Deck deck = createTestDeck();
        Money money = createTestMoney(1000);
        Player player = createPlayerWithScore(deck, money, 20, false, false);
        Dealer dealer = createDealerWithScore(deck, money, 21, true, false);

        assertEquals(GameResult.LOSE, GameResult.from(player, dealer));
    }
    @Test
    @DisplayName("플레이어와 딜러가 동점인 경우 DRAW 반환")
    void testDrawScore() {
        Deck deck = createTestDeck();
        Money money = createTestMoney(1000);
        Player player = createPlayerWithScore(deck, money, 20, false, false);
        Dealer dealer = createDealerWithScore(deck, money, 20, false, false);

        assertEquals(GameResult.DRAW, GameResult.from(player, dealer));
    }
    @Test
    @DisplayName("플레이어가 점수가 더 높은 경우 WIN 반환")
    void testWinScore() {
        Deck deck = createTestDeck();
        Money money = createTestMoney(1000);
        Player player = createPlayerWithScore(deck, money, 20, false, false);
        Dealer dealer = createDealerWithScore(deck, money, 19, false, false);

        assertEquals(GameResult.WIN, GameResult.from(player, dealer));
    }

    @Test
    @DisplayName("플레이어가 점수가 더 높은 경우 WIN 반환")
    void testLoseScore() {
        Deck deck = createTestDeck();
        Money money = createTestMoney(1000);
        Player player = createPlayerWithScore(deck, money, 19, false, false);
        Dealer dealer = createDealerWithScore(deck, money, 20, false, false);

        assertEquals(GameResult.LOSE, GameResult.from(player, dealer));
    }

    @Test
    @DisplayName("게임 결과의 배당률 반환")
    void testGetRate() {
        assertEquals(1.0, GameResult.WIN.getRate());
        assertEquals(-1.0, GameResult.LOSE.getRate());
        assertEquals(0.0, GameResult.DRAW.getRate());
        assertEquals(0.5, GameResult.BLACKJACK.getRate());
    }
}
