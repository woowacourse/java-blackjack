package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import model.card.Card;
import model.card.CardDeck;
import model.card.CardNumber;
import model.card.CardShape;
import model.player.Dealer;
import model.player.Participant;
import model.player.Participants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {

    @DisplayName("참가자가 null일 시 예외가 발생한다.")
    @Test
    void validateParticipantIsNotNull() {
        Dealer dealer = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.SPACE, CardNumber.NINE),
                        new Card(CardShape.SPACE, CardNumber.TWO)));

        Assertions.assertThatThrownBy(() -> new BlackJack(null, dealer))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("딜러가 null일 시 예외가 발생한다.")
    @Test
    void validateDealerIsNotNull() {
        List<Card> participantCards = List.of(new Card(CardShape.SPACE, CardNumber.NINE),
                new Card(CardShape.SPACE, CardNumber.FIVE));
        Assertions.assertThatThrownBy(
                        () -> new BlackJack(
                                new Participants(
                                        List.of(new Participant("배키", participantCards, 100))),
                                null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("딜러는 버스트가 아니고 참가자가 버스트라면, 참가자의 수익은 베팅금액의 -1배다.")
    @Test
    void findLoseOutcomeParticipantOverThreshold() {
        Participant participant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE),
                        new Card(CardShape.DIAMOND, CardNumber.NINE)),
                100);
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        Participant participant2 = new Participant("켬미",
                List.of(new Card(CardShape.CLOVER, CardNumber.EIGHT),
                        new Card(CardShape.HEART, CardNumber.SEVEN)),
                200);
        participant2.addCard(new Card(CardShape.SPACE, CardNumber.KING));

        Dealer notBustDealer = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.SPACE, CardNumber.KING),
                        new Card(CardShape.CLOVER, CardNumber.KING)));

        Participants participants = new Participants(List.of(participant, participant2));
        BlackJack blackJack = new BlackJack(participants, notBustDealer);

        Map<Participant, Double> playerProfits = blackJack.matchParticipantProfit();
        //todo : double이 아닌 Money 객체 만들기
        assertThat(playerProfits).isEqualTo(Map.of(participant, -100.0, participant2, -200.0));
    }

    @DisplayName("딜러와 참가자 모두 버스트가 안된 경우, 딜러 점수가 21과 가까우면 참가자 수익률은 베팅금액의 -1배다.")
    @Test
    void loseWhenDealerScoreCloseToThreshold() {
        Participant participant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.FIVE),
                        new Card(CardShape.DIAMOND, CardNumber.SIX)),
                100);
        Participant participant2 = new Participant("켬미",
                List.of(new Card(CardShape.CLOVER, CardNumber.EIGHT),
                        new Card(CardShape.HEART, CardNumber.SEVEN)),
                200);

        Dealer notBustDealer = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.SPACE, CardNumber.KING),
                        new Card(CardShape.CLOVER, CardNumber.KING)));

        Participants participants = new Participants(List.of(participant, participant2));
        BlackJack blackJack = new BlackJack(participants, notBustDealer);

        Map<Participant, Double> playerProfits = blackJack.matchParticipantProfit();
        assertThat(playerProfits).isEqualTo(Map.of(participant, -100.0, participant2, -200.0));
    }

    @DisplayName("딜러가 버스트면 참가자의 버스트여부와 관련없이 참가자 수익은 베팅금액의 1배다.")
    @Test
    void participantWinWhenDealerBust() {
        Participant bustParticipant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE),
                        new Card(CardShape.DIAMOND, CardNumber.NINE)),
                100);
        bustParticipant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        Participant notBustParticipant = new Participant("켬미",
                List.of(new Card(CardShape.CLOVER, CardNumber.EIGHT),
                        new Card(CardShape.HEART, CardNumber.SEVEN)),
                200);

        Dealer bustDealer = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.SPACE, CardNumber.KING),
                        new Card(CardShape.CLOVER, CardNumber.KING)));
        bustDealer.addCard(new Card(CardShape.SPACE, CardNumber.FIVE));

        Participants participants = new Participants(List.of(bustParticipant, notBustParticipant));
        BlackJack blackJack = new BlackJack(participants, bustDealer);

        Map<Participant, Double> playerProfits = blackJack.matchParticipantProfit();
        assertThat(playerProfits).isEqualTo(Map.of(bustParticipant, 100.0, notBustParticipant, 200.0));

    }

    @DisplayName("딜러와 참가자 모두 버스트가 안된 경우, 참가자 점수가 21과 가까우면 참가자 수익률은 베팅금액의 1배다.")
    @Test
    void findWinOutcomeDealerOverThreshold() {
        Participant notBustParticipant1 = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.KING),
                        new Card(CardShape.DIAMOND, CardNumber.JACK)),
                100);
        Participant notBustParticipant2 = new Participant("켬미",
                List.of(new Card(CardShape.CLOVER, CardNumber.KING),
                        new Card(CardShape.HEART, CardNumber.TEN)),
                200);

        Dealer notBustDealer = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.SPACE, CardNumber.SEVEN),
                        new Card(CardShape.CLOVER, CardNumber.KING)));

        Participants participants = new Participants(List.of(notBustParticipant1, notBustParticipant2));
        BlackJack blackJack = new BlackJack(participants, notBustDealer);

        Map<Participant, Double> playerProfit = blackJack.matchParticipantProfit();
        assertThat(playerProfit)
                .isEqualTo(Map.of(notBustParticipant1, 100.0, notBustParticipant2, 200.0));
    }

    @DisplayName("참가자, 딜러 둘다 버스트가 아닐 때, 참가자 카드의 합이 딜러 카드의 합이 동일하면 참가자의 수익은 0이다.")
    @Test
    void findDrawOutcome() {
        Participant notBustParticipant1 = new Participant("켬미",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE),
                        new Card(CardShape.DIAMOND, CardNumber.NINE)),
                100);
        Participant notBustParticipant2 = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.EIGHT),
                        new Card(CardShape.DIAMOND, CardNumber.TEN)),
                200);
        Dealer notBustDealer = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.HEART, CardNumber.NINE),
                        new Card(CardShape.CLOVER, CardNumber.NINE)));

        Participants participants = new Participants(List.of(notBustParticipant1, notBustParticipant2));
        BlackJack blackJack = new BlackJack(participants, notBustDealer);

        Map<Participant, Double> playerProfit = blackJack.matchParticipantProfit();
        assertThat(playerProfit).isEqualTo(
                Map.of(notBustParticipant1, 0.0, notBustParticipant2, 0.0));
    }
}
