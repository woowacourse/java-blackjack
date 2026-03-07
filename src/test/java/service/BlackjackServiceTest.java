package service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackServiceTest {
    private static BlackjackService blackjackService;

    @BeforeAll
    static void setUp() {
        blackjackService = new BlackjackService();
    }

//    @Test
//    @DisplayName("못함")
//    void 카드_두_장_분배_테스트() {
//        // when
//        blackjackService.dealCards();
//    }
}
