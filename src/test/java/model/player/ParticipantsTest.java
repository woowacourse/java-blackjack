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
                                new Participant(new Name("켬미"),
                                        List.of(new Card(CardShape.SPACE, CardNumber.NINE),
                                                new Card(CardShape.SPACE, CardNumber.FIVE)),
                                        new BettingMoney(100)),
                                new Participant(new Name("켬미"),
                                        List.of(new Card(CardShape.SPACE, CardNumber.NINE),
                                                new Card(CardShape.SPACE, CardNumber.FIVE)),
                                        new BettingMoney(200))
                        )))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> createParticipants() {

        CardDeck cardDeck = new CardDeck(CardDeck.createCards());

        return Stream.of(Arguments.of(
                List.of(new Participant(new Name("배키"), cardDeck.selectRandomCards(CardSize.TWO),
                        new BettingMoney(100))),
                List.of(
                        new Participant(new Name("도비"), cardDeck.selectRandomCards(CardSize.TWO),
                                new BettingMoney(100)),
                        new Participant(new Name("리사"), cardDeck.selectRandomCards(CardSize.TWO),
                                new BettingMoney(100)),
                        new Participant(new Name("명오"), cardDeck.selectRandomCards(CardSize.TWO),
                                new BettingMoney(100)),
                        new Participant(new Name("제우스"), cardDeck.selectRandomCards(CardSize.TWO),
                                new BettingMoney(100)),
                        new Participant(new Name("호티"), cardDeck.selectRandomCards(CardSize.TWO),
                                new BettingMoney(100)),
                        new Participant(new Name("초롱"), cardDeck.selectRandomCards(CardSize.TWO),
                                new BettingMoney(100)),
                        new Participant(new Name("조이썬"), cardDeck.selectRandomCards(CardSize.TWO),
                                new BettingMoney(100)),
                        new Participant(new Name("프람"), cardDeck.selectRandomCards(CardSize.TWO),
                                new BettingMoney(100)),
                        new Participant(new Name("폰드"), cardDeck.selectRandomCards(CardSize.TWO),
                                new BettingMoney(100)))));
    }

    @DisplayName("참가자가 2명보다 작거나 8명보다 크면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("createParticipants")
    void offerCardToParticipant(List<Participant> participants) {
        Assertions.assertThatThrownBy(() -> new Participants(participants))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("참가자들의 계산 결과를 반환한다.")
    @Test
    void calculateParticipantProfit() {
        Participant bustParticipant = new Participant(new Name("배키"),
                List.of(new Card(CardShape.SPACE, CardNumber.TEN),
                        new Card(CardShape.SPACE, CardNumber.KING)),
                new BettingMoney(100));
        bustParticipant.addCards(new Card(CardShape.SPACE, CardNumber.NINE));
        Participant participantScore21 = new Participant(new Name("켬미"),
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
