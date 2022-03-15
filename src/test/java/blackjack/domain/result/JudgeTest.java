package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Type;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class JudgeTest {

    @Test
    @DisplayName("딜러가 참여자보다 점수가 크거나 같으면 딜러가 승리한다.")
    void dealerWinWhenOverThanParticipant() {
        Participant zero = new Participant("zero");
        zero.addCard(new Card(Type.SPADE, Score.ACE));
        zero.addCard(new Card(Type.HEART, Score.KING));
        Participant corinne = new Participant("corinne");
        corinne.addCard(new Card(Type.HEART, Score.SIX));
        corinne.addCard(new Card(Type.HEART, Score.TEN));
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Type.HEART, Score.ACE));
        dealer.addCard(new Card(Type.DIAMOND, Score.KING));

        Players players = new Players(List.of(zero, corinne), dealer);
        GameResult result = Judge.calculateGameResult(players);

        assertAll(
                () -> assertThat(result.getDealerResult().getWin().getCount()).isEqualTo(2),
                () -> assertThat(result.getDealerResult().getLose().getCount()).isEqualTo(0),
                () -> assertThat(result.getParticipantResults().get(0).getResult()).isEqualTo(Result.LOSE),
                () -> assertThat(result.getParticipantResults().get(1).getResult()).isEqualTo(Result.LOSE)
        );
    }

    @Test
    @DisplayName("참가자가 Burst고 딜러는 Burst가 아니라면 딜러가 승리한다.")
    void dealerWinWhenNotBurstAndParticipantBurst() {
        Participant zero = new Participant("zero");
        zero.addCard(new Card(Type.SPADE, Score.KING));
        zero.addCard(new Card(Type.HEART, Score.SIX));
        zero.addCard(new Card(Type.HEART, Score.KING));
        Participant corinne = new Participant("corinne");
        corinne.addCard(new Card(Type.CLOVER, Score.SIX));
        corinne.addCard(new Card(Type.HEART, Score.TEN));
        corinne.addCard(new Card(Type.DIAMOND, Score.SIX));

        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Type.HEART, Score.ACE));
        dealer.addCard(new Card(Type.DIAMOND, Score.KING));

        Players players = new Players(List.of(zero, corinne), dealer);
        GameResult result = Judge.calculateGameResult(players);

        assertAll(
                () -> assertThat(result.getDealerResult().getWin().getCount()).isEqualTo(2),
                () -> assertThat(result.getDealerResult().getLose().getCount()).isEqualTo(0),
                () -> assertThat(result.getParticipantResults().get(0).getResult()).isEqualTo(Result.LOSE),
                () -> assertThat(result.getParticipantResults().get(1).getResult()).isEqualTo(Result.LOSE)
        );
    }

    @Test
    @DisplayName("참가자와 딜러 모두 Burst 라면 딜러가 승리한다.")
    void dealerWinWhenBurstAndParticipantBurst() {
        Participant zero = new Participant("zero");
        zero.addCard(new Card(Type.SPADE, Score.KING));
        zero.addCard(new Card(Type.HEART, Score.SIX));
        zero.addCard(new Card(Type.CLOVER, Score.TEN));

        Participant corinne = new Participant("corinne");
        corinne.addCard(new Card(Type.CLOVER, Score.SIX));
        corinne.addCard(new Card(Type.HEART, Score.TEN));
        corinne.addCard(new Card(Type.DIAMOND, Score.SIX));

        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Type.HEART, Score.ACE));
        dealer.addCard(new Card(Type.DIAMOND, Score.KING));
        dealer.addCard(new Card(Type.CLOVER, Score.ACE));

        Players players = new Players(List.of(zero, corinne), dealer);
        GameResult result = Judge.calculateGameResult(players);

        assertAll(
                () -> assertThat(result.getDealerResult().getWin().getCount()).isEqualTo(2),
                () -> assertThat(result.getDealerResult().getLose().getCount()).isEqualTo(0),
                () -> assertThat(result.getParticipantResults().get(0).getResult()).isEqualTo(Result.LOSE),
                () -> assertThat(result.getParticipantResults().get(1).getResult()).isEqualTo(Result.LOSE)
        );
    }

    @Test
    @DisplayName("참가자가 딜러보다 점수합이 높으면 참가자가 승리한다.")
    void participantWinWhenOverDealer() {
        Participant zero = new Participant("zero");
        zero.addCard(new Card(Type.CLOVER, Score.ACE));
        zero.addCard(new Card(Type.HEART, Score.SIX));

        Participant corinne = new Participant("corinne");
        corinne.addCard(new Card(Type.SPADE, Score.KING));
        corinne.addCard(new Card(Type.HEART, Score.SEVEN));

        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Type.HEART, Score.ACE));
        dealer.addCard(new Card(Type.DIAMOND, Score.FIVE));

        Players players = new Players(List.of(zero, corinne), dealer);
        GameResult result = Judge.calculateGameResult(players);

        assertAll(
                () -> assertThat(result.getDealerResult().getWin().getCount()).isEqualTo(0),
                () -> assertThat(result.getDealerResult().getLose().getCount()).isEqualTo(2),
                () -> assertThat(result.getParticipantResults().get(0).getResult()).isEqualTo(Result.WIN),
                () -> assertThat(result.getParticipantResults().get(1).getResult()).isEqualTo(Result.WIN)
        );
    }
}
