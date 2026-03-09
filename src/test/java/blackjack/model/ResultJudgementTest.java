package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResultJudgementTest {

    final ResultJudgement resultJudgement = new ResultJudgement();

    Hand lowerScoreHand;
    Hand defaultHand;
    Hand higherScoreHand;
    Hand bustScoreHand;

    @BeforeEach
    void initHands() {
        Hand lowerScoreHand = new Hand();
        lowerScoreHand.addCard(new Card(Rank.TWO, Suit.HEART));
        this.lowerScoreHand = lowerScoreHand;

        Hand defaultHand = new Hand();
        defaultHand.addCard(new Card(Rank.THREE, Suit.HEART));
        this.defaultHand = defaultHand;

        Hand higherScoreHand = new Hand();
        higherScoreHand.addCard(new Card(Rank.TEN, Suit.HEART));
        this.higherScoreHand = higherScoreHand;

        Hand bustScoreHand = new Hand();
        bustScoreHand.addCards(List.of(
                new Card(Rank.JACK, Suit.HEART),
                new Card(Rank.QUEEN, Suit.HEART),
                new Card(Rank.KING, Suit.HEART)
        ));
        this.bustScoreHand = bustScoreHand;
    }

    @Test
    void 둘_다_버스트가_아니면서_플레이어가_점수가_더_높다면_플레이어가_승리한다() {
        // given
        Hand playerHand = higherScoreHand;
        Hand dealerHand = lowerScoreHand;

        // when
        BlackjackResult result = resultJudgement.judge(playerHand, dealerHand);

        // then
        assertThat(result).isEqualTo(BlackjackResult.WIN);
    }

    @Test
    void 둘_다_버스트가_아니면서_플레이어가_점수가_더_낮다면_플레이어가_패배한다() {
        // given
        Hand playerHand = lowerScoreHand;
        Hand dealerHand = higherScoreHand;

        // when
        BlackjackResult result = resultJudgement.judge(playerHand, dealerHand);

        // then
        assertThat(result).isEqualTo(BlackjackResult.LOSE);
    }

    @Test
    void 둘_다_버스트가_아니면서_점수가_같다면_무승부한다() {
        // given
        Hand playerHand = defaultHand;
        Hand dealerHand = defaultHand;

        // when
        BlackjackResult result = resultJudgement.judge(playerHand, dealerHand);

        // then
        assertThat(result).isEqualTo(BlackjackResult.PUSH);
    }

    @Test
    void 플레이어가_버스트라면_플레이어가_패배한다() {
        // given
        Hand playerHand = bustScoreHand;
        Hand dealerHand = defaultHand;

        // when
        BlackjackResult result = resultJudgement.judge(playerHand, dealerHand);

        // then
        assertThat(result).isEqualTo(BlackjackResult.LOSE);
    }

    @Test
    void 딜러만_버스트라면_플레이어가_승리한다() {
        // given
        Hand playerHand = defaultHand;
        Hand dealerHand = bustScoreHand;

        // when
        BlackjackResult result = resultJudgement.judge(playerHand, dealerHand);

        // then
        assertThat(result).isEqualTo(BlackjackResult.WIN);
    }

    @Test
    void 둘_다_버스트라면_플레이어가_패배한다() {
        // given
        Hand playerHand = bustScoreHand;
        Hand dealerHand = bustScoreHand;

        // when
        BlackjackResult result = resultJudgement.judge(playerHand, dealerHand);

        // then
        assertThat(result).isEqualTo(BlackjackResult.LOSE);
    }
}
