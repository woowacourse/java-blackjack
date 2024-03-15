package model.player;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import model.BettingMoney;
import model.card.Card;
import model.card.CardDeck;
import model.card.CardNumber;
import model.card.CardShape;
import model.card.CardSize;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ParticipantsTest {

    @DisplayName("중복되는 이름 가진 참가자가 있으면 예외가 발생한다.")
    @Test
    void validate() {
        Assertions.assertThatThrownBy(() ->
                        new Participants(List.of(
                                new Participant("켬미",
                                        List.of(new Card(CardShape.SPACE, CardNumber.NINE),
                                                new Card(CardShape.SPACE, CardNumber.FIVE)),
                                        new BettingMoney(100)),
                                new Participant("켬미",
                                        List.of(new Card(CardShape.SPACE, CardNumber.NINE),
                                                new Card(CardShape.SPACE, CardNumber.FIVE)),
                                        new BettingMoney(200))
                        )))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> createParticipants() {

        CardDeck cardDeck = new CardDeck(CardDeck.createCards());

        return Stream.of(Arguments.of(
                List.of(new Participant("배키", cardDeck.selectRandomCards(CardSize.TWO), new BettingMoney(100))),
                List.of(
                        new Participant("도비", cardDeck.selectRandomCards(CardSize.TWO), new BettingMoney(100)),
                        new Participant("리사", cardDeck.selectRandomCards(CardSize.TWO), new BettingMoney(100)),
                        new Participant("명오", cardDeck.selectRandomCards(CardSize.TWO), new BettingMoney(100)),
                        new Participant("제우스", cardDeck.selectRandomCards(CardSize.TWO), new BettingMoney(100)),
                        new Participant("호티", cardDeck.selectRandomCards(CardSize.TWO), new BettingMoney(100)),
                        new Participant("초롱", cardDeck.selectRandomCards(CardSize.TWO), new BettingMoney(100)),
                        new Participant("조이썬", cardDeck.selectRandomCards(CardSize.TWO), new BettingMoney(100)),
                        new Participant("프람", cardDeck.selectRandomCards(CardSize.TWO), new BettingMoney(100)),
                        new Participant("폰드", cardDeck.selectRandomCards(CardSize.TWO), new BettingMoney(100)))));
    }

    @DisplayName("참가자가 2명보다 작거나 8명보다 크면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("createParticipants")
    void offerCardToParticipant(List<Participant> participants) {
        Assertions.assertThatThrownBy(() -> new Participants(participants))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("참가자들의 결과로 딜러의 수익을 구한다 - 1승 1패")
    @Test
    void calculateDealerProfit() {
        Participant bustParticipant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.TEN),
                        new Card(CardShape.SPACE, CardNumber.KING)),
                new BettingMoney(100));
        bustParticipant.addCard(new Card(CardShape.SPACE, CardNumber.NINE));
        Participant participantScore21 = new Participant("켬미",
                List.of(new Card(CardShape.CLOVER, CardNumber.KING),
                        new Card(CardShape.CLOVER, CardNumber.TEN)),
                new BettingMoney(200));
        participantScore21.addCard(new Card(CardShape.SPACE, CardNumber.ACE));

        Participants participants = new Participants(List.of(bustParticipant, participantScore21));

        Dealer dealerScore20 = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.CLOVER, CardNumber.TEN),
                        new Card(CardShape.DIAMOND, CardNumber.TEN)));

        Double dealerProfit = participants.sumAllParticipantProfit(dealerScore20);

        assertThat(dealerProfit).isEqualTo(-100.0);
    }

    @DisplayName("참가자들의 결과로 딜러의 수익을 구한다. - 1승 1무")
    @Test
    void calculateDealerProfit2() {
        Participant bustParticipant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.TEN),
                        new Card(CardShape.SPACE, CardNumber.KING)),
                new BettingMoney(100));
        bustParticipant.addCard(new Card(CardShape.SPACE, CardNumber.NINE));
        Participant participantScore21 = new Participant("켬미",
                List.of(new Card(CardShape.CLOVER, CardNumber.KING),
                        new Card(CardShape.CLOVER, CardNumber.TEN)),
                new BettingMoney(200));

        Participants participants = new Participants(List.of(bustParticipant, participantScore21));

        Dealer dealerScore20 = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.CLOVER, CardNumber.TEN),
                        new Card(CardShape.DIAMOND, CardNumber.TEN)));

        Double dealerProfit = participants.sumAllParticipantProfit(dealerScore20);

        assertThat(dealerProfit).isEqualTo(100.0);
    }

    @DisplayName("참가자들의 계산 결과를 반환한다.")
    @Test
    void calculateParticipantProfit() {
        Participant bustParticipant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.TEN),
                        new Card(CardShape.SPACE, CardNumber.KING)),
                new BettingMoney(100));
        bustParticipant.addCard(new Card(CardShape.SPACE, CardNumber.NINE));
        Participant participantScore21 = new Participant("켬미",
                List.of(new Card(CardShape.CLOVER, CardNumber.KING),
                        new Card(CardShape.CLOVER, CardNumber.TEN)),
                new BettingMoney(200));

        Participants participants = new Participants(List.of(bustParticipant, participantScore21));
        Dealer dealerScore20 = new Dealer(new CardDeck(CardDeck.createCards()), () ->
                List.of(new Card(CardShape.CLOVER, CardNumber.TEN),
                        new Card(CardShape.DIAMOND, CardNumber.TEN)));

        Map<Participant, Double> participantProfits = participants.calculateParticipantProfit(dealerScore20);

        assertThat(participantProfits).isEqualTo(Map.of(bustParticipant, -100.0, participantScore21, 0.0));
    }
}
