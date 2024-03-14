package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import model.card.*;
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
        dealer = new Dealer(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.NINE),
                Card.of(Suit.SPACE, Denomination.TWO))));
        cardDeck = new CardDeck(Card.createCardDeck());
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
        Cards participantCards = new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.NINE),
                Card.of(Suit.SPACE, Denomination.FIVE)));
        Assertions.assertThatThrownBy(
                        () -> new BlackJack(new Participants(List.of(new Participant("배키", participantCards, new GameMoney(1000))))
                                , null
                                , cardDeck))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> createDealer() {
        Dealer underThresholdDealer = new Dealer(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.KING),
                Card.of(Suit.CLOVER, Denomination.KING))));

        Dealer overThresholdDealer = new Dealer(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.EIGHT),
                Card.of(Suit.CLOVER, Denomination.NINE))));
        overThresholdDealer.addCard(Card.of(Suit.HEART, Denomination.NINE));

        return Stream.of(Arguments.of(
                overThresholdDealer,
                underThresholdDealer
        ));
    }

    @DisplayName("딜러의 카드의 합과 무관하게 참가자 카드의 합이 21을 초과하면 참가자가 패한다.")
    @ParameterizedTest
    @MethodSource("createDealer")
    void findLoseOutcomeParticipantOverThreshold(Dealer dealer) {
        Participant participant = new Participant("배키",
                new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.NINE),
                Card.of(Suit.DIAMOND, Denomination.NINE)))
        , new GameMoney(1000));

        Participant participant2 = new Participant("켬미",
                new Cards(List.of(
                        Card.of(Suit.CLOVER, Denomination.EIGHT),
                        Card.of(Suit.HEART, Denomination.SEVEN)))
        , new GameMoney(1000));

        participant.addCard(Card.of(Suit.CLOVER, Denomination.NINE));
        participant2.addCard(Card.of(Suit.SPACE, Denomination.KING));

        Participants participants = new Participants(List.of(participant, participant2));
        BlackJack blackJack = new BlackJack(participants, dealer, cardDeck);

        Map<Participant, Outcome> result = blackJack.matchParticipantsOutcome();
        assertThat(result).isEqualTo(Map.of(participant, Outcome.LOSE, participant2, Outcome.LOSE));
    }



    @DisplayName("참가자 카드이 합이 21을 넘거나, 둘 다 21을 넘지 않았을 때 21과의 차이가 먼 참가자가 패한다.")
    @Test
    void findWinOutcomeDealerOverThreshold() {
        Participant overThresholdParticipant = new Participant("켬미",
                new Cards(List.of(
                        Card.of(Suit.SPACE, Denomination.EIGHT),
                        Card.of(Suit.CLOVER, Denomination.NINE))), new GameMoney(1000));

        Participant underThresholdParticipant = new Participant("배키",
                new Cards(List.of(
                        Card.of(Suit.SPACE, Denomination.EIGHT),
                        Card.of(Suit.CLOVER, Denomination.NINE))), new GameMoney(1000));
        underThresholdParticipant.addCard(Card.of(Suit.HEART, Denomination.NINE));

        Dealer dealer = new Dealer(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.KING),
                Card.of(Suit.CLOVER, Denomination.KING))));

        Participants participants = new Participants(List.of(underThresholdParticipant, overThresholdParticipant));
        BlackJack blackJack = new BlackJack(participants, dealer, cardDeck);

        Map<Participant, Outcome> result = blackJack.matchParticipantsOutcome();
        assertThat(result)
                .isEqualTo(Map.of(underThresholdParticipant, Outcome.LOSE, overThresholdParticipant, Outcome.LOSE));
    }


    @DisplayName("딜러만 21을 넘거나, 둘 다 21을 넘지 않았을 때 21과의 차이가 가까운 참가자가 승리한다.")
    @ParameterizedTest
    @MethodSource("createDealer")
    void findWinOutComeCloseToThreshold(Dealer dealer) {
        Participant participant = new Participant("배키",
                new Cards(List.of(
                        Card.of(Suit.SPACE, Denomination.NINE),
                        Card.of(Suit.DIAMOND, Denomination.NINE))), new GameMoney(1000));
        Participant participant2 = new Participant("켬미",
                new Cards(List.of(
                        Card.of(Suit.CLOVER, Denomination.EIGHT),
                        Card.of(Suit.HEART, Denomination.NINE))), new GameMoney(1000));

        Participants participants = new Participants(List.of(participant, participant2));
        BlackJack blackJack = new BlackJack(participants, dealer, cardDeck);

        Map<Participant, Outcome> result = blackJack.matchParticipantsOutcome();
        assertThat(result).isEqualTo(Map.of(participant, Outcome.WIN, participant2, Outcome.WIN));
    }

    @DisplayName("참가자 카드의 합이 딜러 카드의 합이 동일하면 무승부다.")
    @Test
    void findDrawOutcome() {
        Participant participant = new Participant("켬미",
                new Cards(List.of(
                        Card.of(Suit.SPACE, Denomination.NINE),
                        Card.of(Suit.DIAMOND, Denomination.NINE))), new GameMoney(1000));
        Participant participant2 = new Participant("배키",
                new Cards(List.of(
                        Card.of(Suit.SPACE, Denomination.EIGHT),
                        Card.of(Suit.DIAMOND, Denomination.TEN))), new GameMoney(1000));
        Dealer dealer = new Dealer(
                new Cards(List.of(
                        Card.of(Suit.HEART, Denomination.NINE),
                        Card.of(Suit.CLOVER, Denomination.NINE))));

        Participants participants = new Participants(List.of(participant, participant2));
        BlackJack blackJack = new BlackJack(participants, dealer, cardDeck);

        Map<Participant, Outcome> result = blackJack.matchParticipantsOutcome();
        assertThat(result).isEqualTo(Map.of(participant, Outcome.DRAW, participant2, Outcome.DRAW));
    }
}
