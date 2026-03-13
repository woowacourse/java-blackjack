package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {
    private BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        blackjackGame = new BlackjackGame();
        blackjackGame.prepare("영기,라이");
        blackjackGame.getUsers().forEach(user -> blackjackGame.placeBet(user, "1000"));
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
        GameSummary summary = blackjackGame.getResult();

        // then
        assertThat(summary.getUserResults()).hasSize(2);
    }

    @Test
    void 게임_결과에_각_참가자_이름_포함() {
        // given
        blackjackGame.dealCards();
        List<User> users = blackjackGame.getUsers();

        // when
        GameSummary summary = blackjackGame.getResult();
        User user1 = users.get(0);
        User user2 = users.get(1);

        // then
        assertThat(summary.getUserResults()).containsKey(user1);
        assertThat(summary.getUserResults()).containsKey(user2);
    }

    @Test
    void 딜러_수익은_유저_수익의_합산에_반전() {
        // given
        blackjackGame.dealCards();

        // when
        GameSummary summary = blackjackGame.getResult();
        BigDecimal userProfitSum = summary.getUserResults().keySet().stream()
                .map(summary::getUserProfit)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // then
        assertThat(summary.getDealerProfit()).isEqualTo(userProfitSum.negate());
    }
}
