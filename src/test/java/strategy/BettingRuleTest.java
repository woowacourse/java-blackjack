package strategy;

import domain.Card;
import domain.GameResult;
import domain.RoundBetInfo;
import domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BettingRuleTest {

    private BettingRule bettingRule;

    @BeforeEach
    void setUp() {
        bettingRule = new DefaultBettingRule();
    }

    @Test
    @DisplayName("블랙잭이면 베팅 금액의 1.5배를 받는다.")
    void 블랙잭_수익_계산() {
        User blackjackUser = User.from("test");
        blackjackUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_ACE));
        blackjackUser.calculateScore();
        RoundBetInfo roundBetInfo = new RoundBetInfo(1, blackjackUser, 1000);

        assertThat(bettingRule.calculateBetAmount(roundBetInfo, GameResult.WIN)).isEqualTo(1500);
    }

    @Test
    @DisplayName("블랙잭이 아니고 WIN이면 베팅 금액의 1배를 받는다.")
    void 일반_승리_수익_계산() {
        User user = User.from("test");
        user.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_SEVEN));
        user.calculateScore();
        RoundBetInfo roundBetInfo = new RoundBetInfo(1, user, 1000);

        assertThat(bettingRule.calculateBetAmount(roundBetInfo, GameResult.WIN)).isEqualTo(1000);
    }

    @Test
    @DisplayName("DRAW면 베팅 금액을 돌려받는다 (수익 0).")
    void 무승부_수익_계산() {
        User user = User.from("test");
        user.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_SEVEN));
        user.calculateScore();
        RoundBetInfo roundBetInfo = new RoundBetInfo(1, user, 1000);

        assertThat(bettingRule.calculateBetAmount(roundBetInfo, GameResult.DRAW)).isEqualTo(0);
    }

    @Test
    @DisplayName("LOSE면 베팅 금액을 잃는다.")
    void 패배_수익_계산() {
        User user = User.from("test");
        user.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_SEVEN));
        user.calculateScore();
        RoundBetInfo roundBetInfo = new RoundBetInfo(1, user, 1000);

        assertThat(bettingRule.calculateBetAmount(roundBetInfo, GameResult.LOSE)).isEqualTo(-1000);
    }
}