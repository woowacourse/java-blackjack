package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vo.Bet;
import vo.GameResult;
import vo.Name;
import vo.Rank;
import vo.Suit;

public class GameJudgeTest {
    private GameJudge gameJudge;

    @BeforeEach
    void setUp() {
        gameJudge = new GameJudge();
    }

    @Test
    void 유저_버스트_BUST() {
        User user = userWith(Rank.TEN, Rank.SEVEN, Rank.FIVE);
        Dealer dealer = dealerWith(Rank.TEN, Rank.SEVEN);
        assertThat(gameJudge.judge(user, dealer)).isEqualTo(GameResult.BUST);
    }

    @Test
    void 딜러_버스트_유저_WIN() {
        User user = userWith(Rank.TEN, Rank.EIGHT);
        Dealer dealer = dealerWith(Rank.TEN, Rank.SEVEN, Rank.FIVE);
        assertThat(gameJudge.judge(user, dealer)).isEqualTo(GameResult.WIN);
    }

    @Test
    void 유저_점수_높으면_WIN() {
        User user = userWith(Rank.TEN, Rank.EIGHT);
        Dealer dealer = dealerWith(Rank.TEN, Rank.FIVE);
        assertThat(gameJudge.judge(user, dealer)).isEqualTo(GameResult.WIN);
    }

    @Test
    void 유저_점수_낮으면_LOSE() {
        User user = userWith(Rank.TEN, Rank.FIVE);
        Dealer dealer = dealerWith(Rank.TEN, Rank.EIGHT);
        assertThat(gameJudge.judge(user, dealer)).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 동점이면_PUSH() {
        User user = userWith(Rank.TEN, Rank.EIGHT);
        Dealer dealer = dealerWith(Rank.TEN, Rank.EIGHT);
        assertThat(gameJudge.judge(user, dealer)).isEqualTo(GameResult.PUSH);
    }

    @Test
    void 동점_21이면_PUSH() {
        User user = userWith(Rank.SEVEN, Rank.SEVEN, Rank.SEVEN);
        Dealer dealer = dealerWith(Rank.SEVEN, Rank.SEVEN, Rank.SEVEN);
        assertThat(gameJudge.judge(user, dealer)).isEqualTo(GameResult.PUSH);
    }

    @Test
    void 유저_블랙잭_BLACKJACK() {
        User user = userWith(Rank.ACE, Rank.TEN);
        Dealer dealer = dealerWith(Rank.TEN, Rank.SEVEN);
        assertThat(gameJudge.judge(user, dealer)).isEqualTo(GameResult.BLACKJACK);
    }

    @Test
    void 양쪽_모두_블랙잭_PUSH() {
        User user = userWith(Rank.ACE, Rank.TEN);
        Dealer dealer = dealerWith(Rank.ACE, Rank.TEN);
        assertThat(gameJudge.judge(user, dealer)).isEqualTo(GameResult.PUSH);
    }

    @Test
    void 유저_21_딜러_블랙잭_LOSE() {
        User user = userWith(Rank.SEVEN, Rank.SEVEN, Rank.SEVEN);
        Dealer dealer = dealerWith(Rank.ACE, Rank.TEN);
        assertThat(gameJudge.judge(user, dealer)).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 유저_블랙잭_딜러_21_BLACKJACK() {
        User user = userWith(Rank.ACE, Rank.TEN);
        Dealer dealer = dealerWith(Rank.SEVEN, Rank.SEVEN, Rank.SEVEN);
        assertThat(gameJudge.judge(user, dealer)).isEqualTo(GameResult.BLACKJACK);
    }

    private User userWith(Rank... ranks) {
        User user = new User(new Name("test"), new Bet("1000"));
        for (Rank rank : ranks) {
            user.receiveCard(new Card(Suit.SPADE, rank));
        }
        return user;
    }

    private Dealer dealerWith(Rank... ranks) {
        Dealer dealer = new Dealer();
        for (Rank rank : ranks) {
            dealer.receiveCard(new Card(Suit.SPADE, rank));
        }
        return dealer;
    }
}
