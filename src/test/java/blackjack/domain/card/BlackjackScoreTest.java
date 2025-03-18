package blackjack.domain.card;

import static blackjack.fixture.CardFixture.CLUB_NINE;
import static blackjack.fixture.CardFixture.CLUB_TEN;
import static blackjack.fixture.CardFixture.DIAMOND_ACE;
import static blackjack.fixture.CardFixture.DIAMOND_KING;
import static blackjack.fixture.CardFixture.DIAMOND_NINE;
import static blackjack.fixture.CardFixture.DIAMOND_TEN;
import static blackjack.fixture.CardFixture.DIAMOND_THREE;
import static blackjack.fixture.CardFixture.DIAMOND_TWO;
import static blackjack.fixture.CardFixture.HEART_NINE;
import static blackjack.fixture.CardFixture.HEART_TEN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BlackjackScoreTest {

    @Test
    void 승부를_보는_두명이_모두_21을_초과한_경우_무승부이다() {
        //given
        Cards mainCards = new Cards(CLUB_TEN, DIAMOND_TEN, HEART_TEN);
        Cards subCards = new Cards(CLUB_NINE, DIAMOND_NINE, HEART_NINE);

        BlackjackScore mainScore = mainCards.calculateScore();
        BlackjackScore subScore = subCards.calculateScore();

        //when
        WinningResult result = mainScore.decide(subScore);

        //then
        assertThat(result).isEqualTo(WinningResult.DRAW);
    }

    @Test
    void 상대방이_21을_초과했고_본인이_21을_초과하지_않았다면_승리이다() {
        //given
        Cards mainCards = new Cards(CLUB_TEN, DIAMOND_TEN);
        Cards subCards = new Cards(CLUB_NINE, DIAMOND_NINE, HEART_NINE);

        BlackjackScore mainScore = mainCards.calculateScore();
        BlackjackScore subScore = subCards.calculateScore();

        //when
        WinningResult result = mainScore.decide(subScore);

        //then
        assertThat(result).isEqualTo(WinningResult.WIN);
    }

    @Test
    void 상대방이_21을_초과하지_않았고_본인이_21을_초과하지_않았을_때_더_높은_점수가_승리이다() {
        //given
        Cards mainCards = new Cards(CLUB_TEN, DIAMOND_TEN);
        Cards subCards = new Cards(CLUB_NINE, DIAMOND_NINE);

        BlackjackScore mainScore = mainCards.calculateScore();
        BlackjackScore subScore = subCards.calculateScore();

        //when
        WinningResult result = mainScore.decide(subScore);

        //then
        assertThat(result).isEqualTo(WinningResult.WIN);
    }

    @Test
    void 상대방이_블랙잭이_아닌_21이고_본인이_블랙잭일_때_승리이다() {
        //given
        Cards mainCards = new Cards(DIAMOND_ACE, DIAMOND_TEN);
        Cards subCards = new Cards(CLUB_NINE, DIAMOND_THREE, DIAMOND_NINE);

        BlackjackScore mainScore = mainCards.calculateScore();
        BlackjackScore subScore = subCards.calculateScore();

        //when
        WinningResult result = mainScore.decide(subScore);

        //then
        assertThat(result).isEqualTo(WinningResult.BLACKJACK_WIN);
    }

    @Test
    void 상대방과_점수가_같다면_무승부이다() {
        //given
        Cards mainCards = new Cards(CLUB_TEN, DIAMOND_TEN);
        Cards subCards = new Cards(CLUB_NINE, DIAMOND_TWO, DIAMOND_NINE);

        BlackjackScore mainScore = mainCards.calculateScore();
        BlackjackScore subScore = subCards.calculateScore();

        //when
        WinningResult result = mainScore.decide(subScore);

        //then
        assertThat(result).isEqualTo(WinningResult.DRAW);
    }

    @Test
    void 상대방이_21을_초과하지_않았고_본인이_21을_초과하지_않았을_때_더_낮은_점수가_패배이다() {
        //given
        Cards mainCards = new Cards(CLUB_NINE, DIAMOND_TWO);
        Cards subCards = new Cards(CLUB_TEN, HEART_TEN);

        BlackjackScore mainScore = mainCards.calculateScore();
        BlackjackScore subScore = subCards.calculateScore();

        //when
        WinningResult result = mainScore.decide(subScore);

        //then
        assertThat(result).isEqualTo(WinningResult.LOSE);
    }

    @Test
    void 본인이_21을_초과하고_상대방이_21을_초과하지_않았다면_패배이다() {
        //given
        Cards mainCards = new Cards(CLUB_TEN, HEART_TEN, DIAMOND_TWO);
        Cards subCards = new Cards(DIAMOND_KING, DIAMOND_ACE);

        BlackjackScore mainScore = mainCards.calculateScore();
        BlackjackScore subScore = subCards.calculateScore();

        //when
        WinningResult result = mainScore.decide(subScore);

        //then
        assertThat(result).isEqualTo(WinningResult.LOSE);
    }

    @Test
    void 상대방이_블랙잭이고_본인이_블랙잭이_아닌_21일_때_패배이다() {
        //given
        Cards mainCards = new Cards(CLUB_TEN, HEART_TEN, new Card(Suit.DIAMOND, Rank.ONE)
        );
        Cards subCards = new Cards(DIAMOND_KING, DIAMOND_ACE);

        BlackjackScore mainScore = mainCards.calculateScore();
        BlackjackScore subScore = subCards.calculateScore();

        //when
        WinningResult result = mainScore.decide(subScore);

        //then
        assertThat(result).isEqualTo(WinningResult.LOSE);
    }
}

