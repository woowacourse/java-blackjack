package service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;

class BlackjackServiceTest {
    private BlackjackService blackjackService;

    @BeforeEach
    void setUp() {
        BlackjackService blackjackService = new BlackjackService();
        this.blackjackService = blackjackService;
    }



}