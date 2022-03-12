package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Type;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JudgeTest {

    @ParameterizedTest
    @MethodSource("dealerWinWhenNotBurst")
    @DisplayName("딜러가 참여자보다 점수가 크거나 같으면 딜러가 승리한다.")
    void whenDealerWinOverThanParticipants(List<Card> participantCards, List<Card> dealerCards) {
        Dealer dealer = new Dealer(dealerCards);
        Participant participant = new Participant(participantCards, "zero");
        assertThat(Judge.compete(dealer, participant)).isEqualTo(true);
    }

    private static Stream<Arguments> dealerWinWhenNotBurst() {
        return Stream.of(
                Arguments.of(List.of(new Card(Type.SPADE, Score.TEN),
                                new Card(Type.HEART, Score.TEN)),
                        List.of(new Card(Type.SPADE, Score.TEN),
                                new Card(Type.HEART, Score.TEN))
                ),
                Arguments.of(List.of(new Card(Type.SPADE, Score.ACE),
                                new Card(Type.HEART, Score.KING)),
                        List.of(new Card(Type.SPADE, Score.ACE),
                                new Card(Type.HEART, Score.JACK))
                )
        );
    }

    @Test
    @DisplayName("참여자가 Burst고 딜러가 Burst가 아니면 딜러가 승리한다.")
    void whenDealerWinParticipantBurst() {
        Dealer dealer = new Dealer(List.of(new Card(Type.SPADE, Score.ACE),
                new Card(Type.HEART, Score.KING)));
        Participant participant = new Participant(
                List.of(new Card(Type.SPADE, Score.TEN),
                        new Card(Type.HEART, Score.EIGHT)), "corinne");
        participant.addCard(new Card(Type.DIAMOND, Score.FOUR));
        assertThat(Judge.compete(dealer, participant)).isEqualTo(true);
    }

    @Test
    @DisplayName("참여자와 딜러 모두 Burst면 딜러가 승리한다.")
    void whenDealerWinBothBurst() {
        Dealer dealer = new Dealer(List.of(new Card(Type.SPADE, Score.TEN),
                new Card(Type.HEART, Score.KING)));
        dealer.addCard(new Card(Type.CLOVER, Score.TWO));
        Participant participant = new Participant(
                List.of(new Card(Type.SPADE, Score.TEN),
                        new Card(Type.HEART, Score.EIGHT)), "corinne");
        participant.addCard(new Card(Type.DIAMOND, Score.FOUR));
        assertThat(Judge.compete(dealer, participant)).isEqualTo(true);
    }

    @Test
    @DisplayName("참여자가 딜러보다 점수가 크면 참여자가 승리한다.")
    void whenParticipantWin() {
        Dealer dealer = new Dealer(List.of(new Card(Type.SPADE, Score.TEN),
                new Card(Type.HEART, Score.KING)));
        Participant participant = new Participant(
                List.of(new Card(Type.SPADE, Score.ACE),
                        new Card(Type.HEART, Score.JACK)), "corinne");
        assertThat(Judge.compete(dealer, participant)).isEqualTo(false);
    }
}
