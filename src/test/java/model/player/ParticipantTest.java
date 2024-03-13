package model.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;
import model.Outcome;
import model.card.Card;
import model.card.CardDeck;
import model.card.CardNumber;
import model.card.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ParticipantTest {

    @DisplayName("참가자의 수가 1명 미만이면 예외가 발생한다.")
    @Test
    void validateUnderOneParticipant() {
        assertThatThrownBy(() -> new Participants(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드의 합이 21이하일 때는 참을 반환한다.")
    @Test
    void noticeTrue() {
        Participant participant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.SPACE, CardNumber.FIVE)));
        assertFalse(participant.isNotHit());
    }

    @DisplayName("카드의 합이 21초과일 때는 거짓을 반환한다.")
    @Test
    void noticeFalse() {
        Participant participant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.SPACE, CardNumber.FIVE)));
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        assertTrue(participant.isNotHit());
    }

    @DisplayName("참가자와 딜러 카드의 합이 같을 때 무승부이다.")
    @Test
    void findOutcomeDraw() {
        Participant participant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.SPACE, CardNumber.FIVE)));
        Dealer dealer = new Dealer(new CardDeck(CardDeck.createCards()),
                ()->List.of(new Card(CardShape.CLOVER, CardNumber.NINE), new Card(CardShape.HEART, CardNumber.FIVE)));

        Outcome playerOutcome = participant.findOutcome(dealer);

        Assertions.assertThat(playerOutcome).isEqualTo(Outcome.DRAW);
    }

    static Stream<Arguments> createParticipant() {
        Participant underThresholdParticipant = new Participant("켬미",
                List.of(new Card(CardShape.SPACE, CardNumber.EIGHT), new Card(CardShape.CLOVER, CardNumber.NINE)));
        Participant overThresholdParticipant = new Participant("켬미",
                List.of(new Card(CardShape.SPACE, CardNumber.EIGHT), new Card(CardShape.CLOVER, CardNumber.NINE)));
        underThresholdParticipant.addCard(new Card(CardShape.HEART, CardNumber.NINE));
        return Stream.of(Arguments.of(
                underThresholdParticipant,
                overThresholdParticipant
        ));
    }

    @DisplayName("참가자 카드이 합이 21을 넘거나, 둘 다 21을 넘지 않았을 때 21과의 차이가 먼 참가자가 패한다.")
    @ParameterizedTest
    @MethodSource("createParticipant")
    void findOutcomeLose(Participant participant) {
        Dealer dealer = new Dealer(new CardDeck(CardDeck.createCards()),
                ()->List.of(new Card(CardShape.SPACE, CardNumber.KING), new Card(CardShape.SPACE, CardNumber.JACK)));
        Outcome playerOutcome = participant.findOutcome(dealer);

        Assertions.assertThat(playerOutcome).isEqualTo(Outcome.LOSE);
    }

    static Stream<Arguments> createDealer() {
        Dealer underThresholdDealer = new Dealer(new CardDeck(CardDeck.createCards()),
                ()->List.of(new Card(CardShape.SPACE, CardNumber.EIGHT), new Card(CardShape.CLOVER, CardNumber.NINE)));
        Dealer overThresholdDealer = new Dealer(new CardDeck(CardDeck.createCards()),
                ()->List.of(new Card(CardShape.SPACE, CardNumber.EIGHT), new Card(CardShape.CLOVER, CardNumber.NINE)));
        overThresholdDealer.addCard(new Card(CardShape.HEART, CardNumber.NINE));
        return Stream.of(Arguments.of(
                underThresholdDealer,
                overThresholdDealer
        ));
    }

    @DisplayName("딜러만 21을 넘거나, 둘 다 21을 넘지 않았을 때 21과의 차이가 가까운 참가자가 승리한다.")
    @ParameterizedTest
    @MethodSource("createDealer")
    void findOutcomeWin(Dealer dealer) {
        Participant participant = new Participant("켬미",
                List.of(new Card(CardShape.SPACE, CardNumber.KING), new Card(CardShape.SPACE, CardNumber.JACK)));
        Outcome playerOutcome = participant.findOutcome(dealer);

        Assertions.assertThat(playerOutcome).isEqualTo(Outcome.WIN);
    }
}
