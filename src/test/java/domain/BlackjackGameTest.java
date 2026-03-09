package domain;

import org.junit.jupiter.api.BeforeAll;

public class BlackjackGameTest {
    private static BlackjackGame blackjackGame;

    @BeforeAll
    static void setUp() {
        blackjackGame = new BlackjackGame();
    }

//    @Test
//    @DisplayName("못함")
//    void 카드_두_장_분배_테스트() {
//        // when
//        blackjackService.dealCards();
//    }
}
