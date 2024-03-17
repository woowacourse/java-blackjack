package model.player;

import model.GameMoney;
import model.Outcome;
import model.card.Card;
import model.card.Cards;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParticipantTest {

    @DisplayName("카드의 합이 21이하일 때는 참을 반환한다.")
    @Test
    void isHitTrue() {
        Participant participant = new Participant(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.TEN),
                Card.of(Suit.SPACE, Denomination.KING))),
                new ParticipantProfile(new Name("배키"), new GameMoney(1000)));

        assertFalse(participant.isNotHit());
    }

    @DisplayName("카드의 합이 21초과일 때는 거짓을 반환한다.")
    @Test
    void isHitFalse() {
        Participant participant = new Participant(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.TEN),
                Card.of(Suit.SPACE, Denomination.KING))),
                new ParticipantProfile(new Name("배키"), new GameMoney(1000)));
        participant.addCard(Card.of(Suit.CLOVER, Denomination.THREE));

        assertTrue(participant.isNotHit());
    }

    @DisplayName("참가자와 딜러 카드의 합이 같을 때 무승부이다.")
    @Test
    void findOutcomeDraw() {
        Participant participant = new Participant(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.NINE),
                Card.of(Suit.SPACE, Denomination.FIVE))),
                new ParticipantProfile(new Name("배키"), new GameMoney(1000)));
        Dealer dealer = new Dealer(
                new Cards(List.of(
                        Card.of(Suit.CLOVER, Denomination.NINE),
                        Card.of(Suit.HEART, Denomination.FIVE))));

        Outcome playerOutcome = participant.findOutcome(dealer);

        Assertions.assertThat(playerOutcome).isEqualTo(Outcome.DRAW);
    }

    static Stream<Arguments> createParticipant() {
        Participant underThresholdParticipant = new Participant(new Cards(List.of(
                Card.of(Suit.HEART, Denomination.TEN),
                Card.of(Suit.CLOVER, Denomination.TEN))),
                new ParticipantProfile(new Name("켬미"), new GameMoney(1000)));

        Participant overThresholdParticipant = new Participant(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.KING),
                Card.of(Suit.CLOVER, Denomination.KING))),
                new ParticipantProfile(new Name("켬미"), new GameMoney(1000)));
        overThresholdParticipant.addCard(Card.of(Suit.HEART, Denomination.TWO));
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
                new Cards(List.of(
                        Card.of(Suit.SPACE, Denomination.TEN),
                        Card.of(Suit.SPACE, Denomination.ACE))));
        Outcome playerOutcome = participant.findOutcome(dealer);

        Assertions.assertThat(playerOutcome).isEqualTo(Outcome.LOSE);
    }

    static Stream<Arguments> createDealer() {
        Dealer underThresholdDealer = new Dealer(
                new Cards(List.of(
                        Card.of(Suit.HEART, Denomination.TEN),
                        Card.of(Suit.CLOVER, Denomination.TEN))));

        Dealer overThresholdDealer = new Dealer(
                new Cards(List.of(
                        Card.of(Suit.SPACE, Denomination.KING),
                        Card.of(Suit.CLOVER, Denomination.KING))));
        overThresholdDealer.addCard(Card.of(Suit.HEART, Denomination.TWO));

        return Stream.of(Arguments.of(
                underThresholdDealer,
                overThresholdDealer
        ));
    }

    @DisplayName("딜러만 21을 넘거나, 둘 다 21을 넘지 않았을 때 21과의 차이가 가까운 참가자가 승리한다.")
    @ParameterizedTest
    @MethodSource("createDealer")
    void findOutcomeWin(Dealer dealer) {
        Participant participant = new Participant(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.TEN),
                Card.of(Suit.SPACE, Denomination.ACE))),
                new ParticipantProfile(new Name("켬미"), new GameMoney(1000)));
        Outcome playerOutcome = participant.findOutcome(dealer);

        Assertions.assertThat(playerOutcome).isEqualTo(Outcome.WIN);
    }
}
