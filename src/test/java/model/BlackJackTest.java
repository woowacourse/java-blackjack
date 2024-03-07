package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.player.Dealer;
import model.player.Participant;
import model.player.Participants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {

    @DisplayName("참가자에게 카드를 준다.")
    @Test
    void offerCardToPlayers() {
        Participants participants = new Participants(List.of(new Participant("배키")));
        BlackJack blackJack = new BlackJack(participants, new Dealer());
        blackJack.offerCardToPlayers(2);
        List<Participant> result = participants.getParticipants();

        assertAll(
                () -> assertThat(result.get(0).getCards()).hasSize(2)
        );
    }


    @DisplayName("이름이 일치하는 참가자에게만 카드를 줄 수 있다.")
    @Test
    void offerCardToPlayer() {
        Participants participants = new Participants(List.of(new Participant("배키")));
        BlackJack blackJack = new BlackJack(participants, new Dealer());
        blackJack.offerCardToPlayer(new Participant("배키"), 1);
        List<Participant> result = participants.getParticipants();

        assertAll(
                () -> assertThat(result.get(0).getCards()).hasSize(1)
        );
    }

    @DisplayName("카드의 합이 21을 초과하면 패한다.")
    @Test
    void findLoseResult() {
        Participant participant = new Participant("배키");
        participant.addCard(new Card(CardShape.SPACE, CardNumber.NINE));
        participant.addCard(new Card(CardShape.DIAMOND, CardNumber.NINE));

        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardShape.SPACE, CardNumber.EIGHT));
        dealer.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));

        Participants participants = new Participants(List.of(participant));
        BlackJack blackJack = new BlackJack(participants, dealer);

        Map<Participant, GameResult> result = blackJack.findResult();
        Assertions.assertThat(result).isEqualTo(Map.of(participant, GameResult.WIN));
    }

    @DisplayName("참가자 카드의 합이 딜러와 동일하면 무승부다.")
    @Test
    void findDrawResult() {
        Participant participant = new Participant("배키");
        participant.addCard(new Card(CardShape.SPACE, CardNumber.NINE));
        participant.addCard(new Card(CardShape.DIAMOND, CardNumber.NINE));

        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardShape.HEART, CardNumber.NINE));
        dealer.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));

        Participants participants = new Participants(List.of(participant));
        BlackJack blackJack = new BlackJack(participants, dealer);

        Map<Participant, GameResult> result = blackJack.findResult();
        Assertions.assertThat(result).isEqualTo(Map.of(participant, GameResult.DRAW));
    }

    @DisplayName("딜러의 합이 16을 넘으면 거짓을 반환한다.")
    @Test
    void isDealerOverThresholdTrue() {
        Participant participant = new Participant("배키");

        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardShape.SPACE, CardNumber.NINE));
        dealer.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));

        Participants participants = new Participants(List.of(participant));
        BlackJack blackJack = new BlackJack(participants, dealer);

        assertFalse(blackJack.isDealerUnderThreshold());
    }

    @DisplayName("딜러의 합이 16을 넘지 않으면 참을 반환한다.")
    @Test
    void isDealerOverThresholdFalse() {
        Participant participant = new Participant("배키");

        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardShape.SPACE, CardNumber.NINE));
        dealer.addCard(new Card(CardShape.CLOVER, CardNumber.SIX));

        Participants participants = new Participants(List.of(participant));
        BlackJack blackJack = new BlackJack(participants, dealer);

        assertTrue(blackJack.isDealerUnderThreshold());
    }
}
