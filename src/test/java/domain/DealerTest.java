package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vo.GameResult;
import vo.Rank;
import vo.Suit;

public class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    void 유저_버스트_패() {
        // given
        dealer.receiveCard(new Card(Suit.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Suit.HEART, Rank.SEVEN));
        int userScore = 22;

        // when
        GameResult result = dealer.judgeUserWin(userScore);

        // then
        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 유저_블랙잭_승() {
        // given
        dealer.receiveCard(new Card(Suit.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Suit.HEART, Rank.SEVEN));
        int userScore = 21;

        // when
        GameResult result = dealer.judgeUserWin(userScore);

        // then
        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @Test
    void 딜러_버스트_유저_승() {
        // given
        dealer.receiveCard(new Card(Suit.SPADE, Rank.KING));
        dealer.receiveCard(new Card(Suit.HEART, Rank.QUEEN));
        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.TWO));
        int userScore = 18;

        // when
        GameResult result = dealer.judgeUserWin(userScore);

        // then
        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @Test
    void 유저_점수_높으면_승() {
        // given
        dealer.receiveCard(new Card(Suit.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Suit.HEART, Rank.FIVE));
        int userScore = 18;

        // when
        GameResult result = dealer.judgeUserWin(userScore);

        // then
        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @Test
    void 유저_점수_낮으면_패() {
        // given
        dealer.receiveCard(new Card(Suit.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Suit.HEART, Rank.EIGHT));
        int userScore = 15;

        // when
        GameResult result = dealer.judgeUserWin(userScore);

        // then
        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 딜러_점수_같으면_승() {
        // given
        dealer.receiveCard(new Card(Suit.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Suit.HEART, Rank.EIGHT));
        int userScore = 18;

        // when
        GameResult result = dealer.judgeUserWin(userScore);

        // then
        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @Test
    void 딜러_16이하_히트_필요() {
        // given
        dealer.receiveCard(new Card(Suit.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Suit.HEART, Rank.SIX));

        // when & then
        assertThat(dealer.determineDealerDealMore()).isTrue();
    }

    @Test
    void 딜러_17이상_히트_불필요() {
        // given
        dealer.receiveCard(new Card(Suit.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Suit.HEART, Rank.SEVEN));

        // when & then
        assertThat(dealer.determineDealerDealMore()).isFalse();
    }
}