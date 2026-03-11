package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vo.GameResult;

public class BlackjackGameTest {
    private BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        blackjackGame = new BlackjackGame();
        blackjackGame.prepare("영기,라이");
        blackjackGame.makeDeck();
    }

    @Test
    void 참가자_수만큼_이름_반환() {
        // when
        List<String> names = blackjackGame.getParticipantNames();

        // then
        assertThat(names).hasSize(2);
    }

    @Test
    void 참가자_이름_포함_확인() {
        // when
        List<String> names = blackjackGame.getParticipantNames();

        // then
        assertThat(names.get(0)).isEqualTo("영기");
        assertThat(names.get(1)).isEqualTo("라이");
    }

    @Test
    void 초기_카드_배분_후_유저_수() {
        // when
        blackjackGame.dealCards();

        // then
        assertThat(blackjackGame.getUsers()).hasSize(2);
    }

    @Test
    void 초기_카드_배분_후_유저_이름_확인() {
        // when
        blackjackGame.dealCards();
        List<User> users = blackjackGame.getUsers();

        // then
        assertThat(users.get(0).getName()).isEqualTo("영기");
        assertThat(users.get(1).getName()).isEqualTo("라이");
    }

    @Test
    void 게임_결과_유저_수만큼_반환() {
        // given
        blackjackGame.dealCards();

        // when
        Map<String, GameResult> results = blackjackGame.getUserResults();

        // then
        assertThat(results).hasSize(2);
    }

    @Test
    void 게임_결과에_각_참가자_이름_포함() {
        // given
        blackjackGame.dealCards();

        // when
        Map<String, GameResult> results = blackjackGame.getUserResults();

        // then
        assertThat(results).containsKey("영기");
        assertThat(results).containsKey("라이");
    }

    @Test
    void 딜러_결과에_승패_정보_포함() {
        // given
        blackjackGame.dealCards();

        // when
        var dealerResults = blackjackGame.getDealerResults();

        // then
        assertThat(dealerResults).containsKey(GameResult.WIN);
        assertThat(dealerResults).containsKey(GameResult.LOSE);
    }
}
