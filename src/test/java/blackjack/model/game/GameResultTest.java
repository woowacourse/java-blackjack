package blackjack.model.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardShape;
import blackjack.model.card.CardType;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameResultTest {

    private Dealer dealer;
    private Participant participant1;
    private Participant participant2;
    private Participants participants;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        participant1 = new Participant("벡터");
        participant2 = new Participant("한스");
        participants = new Participants(List.of(participant1, participant2));
    }

    @Test
    void 참가자가_버스트면_딜러가_승리한다() {
        // given
        participant1.putCard(new Card(CardShape.HEART, CardType.KING));
        participant1.putCard(new Card(CardShape.HEART, CardType.QUEEN));
        participant1.putCard(new Card(CardShape.HEART, CardType.NORMAL_5)); // 25점, 버스트

        // when
        GameResult gameResult = new GameResult(dealer, participants);

        // then
        assertThat(gameResult.getWinLoseResult().get(participant1)).isEqualTo(ParticipantResult.LOSE);
    }

    @Test
    void 딜러가_버스트면_참가자가_승리한다() {
        // given
        dealer.putCard(new Card(CardShape.HEART, CardType.KING));
        dealer.putCard(new Card(CardShape.HEART, CardType.QUEEN));
        dealer.putCard(new Card(CardShape.HEART, CardType.NORMAL_5)); // 25점, 버스트

        participant1.putCard(new Card(CardShape.SPADE, CardType.KING)); // 10점
        participant1.putCard(new Card(CardShape.SPADE, CardType.QUEEN)); // 10점 -> 총 20점

        // when
        GameResult gameResult = new GameResult(dealer, participants);

        // then
        assertThat(gameResult.getWinLoseResult().get(participant1)).isEqualTo(ParticipantResult.WIN);
    }

    @Test
    void 참가자의_점수가_딜러보다_높으면_승리한다() {
        // given
        dealer.putCard(new Card(CardShape.HEART, CardType.KING)); // 10점
        dealer.putCard(new Card(CardShape.HEART, CardType.NORMAL_6)); // 6점 -> 총 16점

        participant1.putCard(new Card(CardShape.SPADE, CardType.KING)); // 10점
        participant1.putCard(new Card(CardShape.SPADE, CardType.NORMAL_8)); // 8점 -> 총 18점

        // when
        GameResult gameResult = new GameResult(dealer, participants);

        // then
        assertThat(gameResult.getWinLoseResult().get(participant1)).isEqualTo(ParticipantResult.WIN);
    }

    @Test
    void 딜러의_점수가_참가자보다_높으면_딜러가_승리한다() {
        // given
        dealer.putCard(new Card(CardShape.HEART, CardType.KING)); // 10점
        dealer.putCard(new Card(CardShape.HEART, CardType.NORMAL_8)); // 8점 -> 총 18점

        participant1.putCard(new Card(CardShape.SPADE, CardType.KING)); // 10점
        participant1.putCard(new Card(CardShape.SPADE, CardType.NORMAL_6)); // 6점 -> 총 16점

        // when
        GameResult gameResult = new GameResult(dealer, participants);

        // then
        assertThat(gameResult.getWinLoseResult().get(participant1)).isEqualTo(ParticipantResult.LOSE);
    }

    @Test
    void 딜러와_참가자의_점수가_같으면_무승부() {
        // given
        dealer.putCard(new Card(CardShape.HEART, CardType.KING)); // 10점
        dealer.putCard(new Card(CardShape.HEART, CardType.NORMAL_8)); // 8점 -> 총 18점

        participant1.putCard(new Card(CardShape.SPADE, CardType.KING)); // 10점
        participant1.putCard(new Card(CardShape.SPADE, CardType.NORMAL_8)); // 8점 -> 총 18점

        // when
        GameResult gameResult = new GameResult(dealer, participants);

        // then
        assertThat(gameResult.getWinLoseResult().get(participant1)).isEqualTo(ParticipantResult.DRAW);
    }

    @Test
    void 딜러의_승패_카운트를_확인한다() {
        // given
        dealer.putCard(new Card(CardShape.HEART, CardType.KING)); // 10점
        dealer.putCard(new Card(CardShape.HEART, CardType.NORMAL_8)); // 8점 -> 총 18점

        participant1.putCard(new Card(CardShape.SPADE, CardType.KING)); // 10점
        participant1.putCard(new Card(CardShape.SPADE, CardType.NORMAL_6)); // 6점 -> 총 16점 (패배)

        participant2.putCard(new Card(CardShape.DIAMOND, CardType.KING)); // 10점
        participant2.putCard(new Card(CardShape.DIAMOND, CardType.NORMAL_9)); // 9점 -> 총 19점 (승리)

        // when
        GameResult gameResult = new GameResult(dealer, participants);

        // then
        assertThat(gameResult.getDealerWinCount()).isEqualTo(1);
        assertThat(gameResult.getDealerLoseCount()).isEqualTo(1);
    }

}