package model.player;

import model.Outcome;
import model.card.Card;
import model.card.Denomination;
import model.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
                List.of(Card.of(Suit.SPACE, Denomination.NINE), Card.of(Suit.SPACE, Denomination.FIVE)));
        assertFalse(participant.isNotHit());
    }

    @DisplayName("카드의 합이 21초과일 때는 거짓을 반환한다.")
    @Test
    void noticeFalse() {
        Participant participant = new Participant("배키",
                List.of(Card.of(Suit.SPACE, Denomination.NINE), Card.of(Suit.SPACE, Denomination.FIVE)));
        participant.addCards(List.of(Card.of(Suit.CLOVER, Denomination.NINE), Card.of(Suit.CLOVER, Denomination.NINE), Card.of(Suit.CLOVER, Denomination.NINE)));
        assertTrue(participant.isNotHit());
    }

    @DisplayName("참가자와 딜러 카드의 합이 같을 때 무승부이다.")
    @Test
    void findOutcomeDraw() {
        Participant participant = new Participant("배키",
                List.of(Card.of(Suit.SPACE, Denomination.NINE), Card.of(Suit.SPACE, Denomination.FIVE)));
        Dealer dealer = new Dealer(
                List.of(Card.of(Suit.CLOVER, Denomination.NINE), Card.of(Suit.HEART, Denomination.FIVE)));

        Outcome playerOutcome = participant.findOutcome(dealer.isNotHit(), dealer.findPlayerDifference());

        Assertions.assertThat(playerOutcome).isEqualTo(Outcome.DRAW);
    }

    static Stream<Arguments> createParticipant() {
        Participant underThresholdParticipant = new Participant("켬미",
                List.of(Card.of(Suit.SPACE, Denomination.EIGHT), Card.of(Suit.CLOVER, Denomination.NINE)));
        Participant overThresholdParticipant = new Participant("켬미",
                List.of(Card.of(Suit.SPACE, Denomination.EIGHT), Card.of(Suit.CLOVER, Denomination.NINE)));
        underThresholdParticipant.addCards(List.of(Card.of(Suit.HEART, Denomination.NINE)));
        return Stream.of(Arguments.of(
                underThresholdParticipant,
                overThresholdParticipant
        ));
    }

    @DisplayName("참가자 카드이 합이 21을 넘거나, 둘 다 21을 넘지 않았을 때 21과의 차이가 먼 참가자가 패한다.")
    @ParameterizedTest
    @MethodSource("createParticipant")
    void findOutcomeLose(Participant participant) {
        Dealer dealer = new Dealer(
                List.of(Card.of(Suit.SPACE, Denomination.KING), Card.of(Suit.SPACE, Denomination.JACK)));
        Outcome playerOutcome = participant.findOutcome(dealer.isNotHit(), dealer.findPlayerDifference());


        Assertions.assertThat(playerOutcome).isEqualTo(Outcome.LOSE);
    }

    static Stream<Arguments> createDealer() {
        Dealer underThresholdDealer = new Dealer(
                List.of(Card.of(Suit.SPACE, Denomination.EIGHT), Card.of(Suit.CLOVER, Denomination.NINE)));
        Dealer overThresholdDealer = new Dealer(
                List.of(Card.of(Suit.SPACE, Denomination.EIGHT), Card.of(Suit.CLOVER, Denomination.NINE)));
        overThresholdDealer.addCards(List.of(Card.of(Suit.HEART, Denomination.NINE)));
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
                List.of(Card.of(Suit.SPACE, Denomination.KING), Card.of(Suit.SPACE, Denomination.JACK)));
        Outcome playerOutcome = participant.findOutcome(dealer.isNotHit(), dealer.findPlayerDifference());


        Assertions.assertThat(playerOutcome).isEqualTo(Outcome.WIN);
    }
}
