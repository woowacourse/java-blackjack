package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {
    private BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        blackjackGame = new BlackjackGame();
        blackjackGame.saveParticipants(List.of("영기", "라이"));
        blackjackGame.makeDeck();
    }

    @Test
    void 참가자_수만큼_추가_카드_요청_메시지_생성() {
        // when
        List<String> requestMessages = blackjackGame.makeExtraCardRequests();

        // then
        assertThat(requestMessages).hasSize(2);
    }

    @Test
    void 추가_카드_요청_메시지에_참가자_이름_포함() {
        // when
        List<String> requestMessages = blackjackGame.makeExtraCardRequests();

        // then
        assertThat(requestMessages.get(0)).contains("영기");
        assertThat(requestMessages.get(1)).contains("라이");
    }

    @Test
    void 초기_카드_배분_후_유저_카드_표시_개수() {
        // when
        blackjackGame.dealCards();
        List<String> userCardsDisplays = blackjackGame.getUserCardsDisplays();

        // then
        assertThat(userCardsDisplays).hasSize(2);
    }

    @Test
    void 초기_카드_배분_후_유저_카드_표시에_이름_포함() {
        // when
        blackjackGame.dealCards();
        List<String> userCardsDisplays = blackjackGame.getUserCardsDisplays();

        // then
        assertThat(userCardsDisplays.get(0)).contains("영기");
        assertThat(userCardsDisplays.get(1)).contains("라이");
    }

    @Test
    void 게임_결과_항목_수는_딜러_포함_참가자_수_더하기_1() {
        // given
        blackjackGame.dealCards();

        // when
        List<String> results = blackjackGame.evaluateGame();

        // then
        assertThat(results).hasSize(3); // 딜러 1 + 플레이어 2
    }

    @Test
    void 게임_결과_첫번째_항목은_딜러() {
        // given
        blackjackGame.dealCards();

        // when
        List<String> results = blackjackGame.evaluateGame();

        // then
        assertThat(results.get(0)).contains("딜러");
    }

    @Test
    void 게임_결과에_각_참가자_이름_포함() {
        // given
        blackjackGame.dealCards();

        // when
        List<String> results = blackjackGame.evaluateGame();

        // then
        assertThat(results.get(1)).contains("영기");
        assertThat(results.get(2)).contains("라이");
    }
}