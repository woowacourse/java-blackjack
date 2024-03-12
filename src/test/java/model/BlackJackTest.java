package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import model.card.Card;
import model.card.CardDeck;
import model.card.CardNumber;
import model.card.CardShape;
import model.player.Dealer;
import model.player.Participant;
import model.player.Participants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackJackTest {

    private Dealer dealer;
    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.SPACE, CardNumber.TWO)));
        cardDeck = new CardDeck(CardDeck.createCards());
    }

    @DisplayName("참가자가 null일 시 예외가 발생한다.")
    @Test
    void validateParticipantIsNotNull() {
        Assertions.assertThatThrownBy(() -> new BlackJack(null, dealer, cardDeck))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("딜러가 null일 시 예외가 발생한다.")
    @Test
    void validateDealerIsNotNull() {
        List<Card> participantCards = List.of(new Card(CardShape.SPACE, CardNumber.NINE),
                new Card(CardShape.SPACE, CardNumber.FIVE));
        Assertions.assertThatThrownBy(
                        () -> new BlackJack(new Participants(List.of(new Participant("배키", participantCards))), null,
                                cardDeck))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> createDealer() {
        Dealer underThresholdDealer = new Dealer(
                List.of(new Card(CardShape.SPACE, CardNumber.EIGHT), new Card(CardShape.CLOVER, CardNumber.NINE)));
        Dealer overThresholdDealer = new Dealer(
                List.of(new Card(CardShape.SPACE, CardNumber.EIGHT), new Card(CardShape.CLOVER, CardNumber.NINE)));
        overThresholdDealer.addCard(new Card(CardShape.HEART, CardNumber.NINE));
        return Stream.of(Arguments.of(
                underThresholdDealer,
                overThresholdDealer
        ));
    }

    @DisplayName("딜러의 카드의 합과 무관하게 참가자 카드의 합이 21을 초과하면 참가자가 패한다.")
    @ParameterizedTest
    @MethodSource("createDealer")
    void findLoseOutcomeParticipantOverThreshold(Dealer dealer) {
        Participant participant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.DIAMOND, CardNumber.NINE)));
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));

        Participants participants = new Participants(List.of(participant));
        BlackJack blackJack = new BlackJack(participants, dealer, cardDeck);

        Map<Participant, Outcome> result = blackJack.matchParticipantsOutcome();
        assertThat(result).isEqualTo(Map.of(participant, Outcome.LOSE));
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
    void findWinOutcomeDealerOverThreshold(Participant participant) {
        Dealer dealer = new Dealer(
                List.of(new Card(CardShape.SPACE, CardNumber.KING), new Card(CardShape.CLOVER, CardNumber.KING)));

        Participants participants = new Participants(List.of(participant));
        BlackJack blackJack = new BlackJack(participants, dealer, cardDeck);

        Map<Participant, Outcome> result = blackJack.matchParticipantsOutcome();
        assertThat(result).isEqualTo(Map.of(participant, Outcome.LOSE));
    }

    @DisplayName("딜러만 21을 넘거나, 둘 다 21을 넘지 않았을 때 21과의 차이가 가까운 참가자가 승리한다.")
    @Test
    void findWinOutComeCloseToThreshold() {
        Participant participant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.DIAMOND, CardNumber.NINE)));
        Dealer dealer = new Dealer(
                List.of(new Card(CardShape.SPACE, CardNumber.EIGHT), new Card(CardShape.CLOVER, CardNumber.NINE)));

        Participants participants = new Participants(List.of(participant));
        BlackJack blackJack = new BlackJack(participants, dealer, cardDeck);

        Map<Participant, Outcome> result = blackJack.matchParticipantsOutcome();
        assertThat(result).isEqualTo(Map.of(participant, Outcome.WIN));
    }

    @DisplayName("참가자 카드의 합이 딜러 카드의 합이 동일하면 무승부다.")
    @Test
    void findDrawOutcome() {
        Participant participant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.DIAMOND, CardNumber.NINE)));
        Dealer dealer = new Dealer(
                List.of(new Card(CardShape.HEART, CardNumber.NINE), new Card(CardShape.CLOVER, CardNumber.NINE)));

        Participants participants = new Participants(List.of(participant));
        BlackJack blackJack = new BlackJack(participants, dealer, cardDeck);

        Map<Participant, Outcome> result = blackJack.matchParticipantsOutcome();
        assertThat(result).isEqualTo(Map.of(participant, Outcome.DRAW));
    }

    @DisplayName("딜러의 합이 16을 넘으면 거짓을 반환한다.")
    @Test
    void isDealerOverThresholdFalse() {
        Participant participant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.SPACE, CardNumber.FIVE)));
        Dealer dealer = new Dealer(
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.CLOVER, CardNumber.NINE)));

        Participants participants = new Participants(List.of(participant));
        BlackJack blackJack = new BlackJack(participants, dealer, cardDeck);

        assertFalse(blackJack.isDealerUnderThreshold());
    }

    @DisplayName("딜러의 합이 16을 넘지 않으면 참을 반환한다.")
    @Test
    void isDealerOverThresholdTrue() {
        Participant participant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.SPACE, CardNumber.FIVE)));
        Dealer dealer = new Dealer(
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.CLOVER, CardNumber.SIX)));

        Participants participants = new Participants(List.of(participant));
        BlackJack blackJack = new BlackJack(participants, dealer, cardDeck);

        assertTrue(blackJack.isDealerUnderThreshold());
    }
}
