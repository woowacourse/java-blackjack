package domain;

import domain.card.*;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.result.GameResult;
import domain.result.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ParticipantsTest {

    private Participants participants;
    private Participant leo;

    @BeforeEach
    void setUp() {
        participants = Participants.from(List.of("leo", "reo", "reoleo"));
        leo = participants.findPlayers().get(0);
    }

    @Test
    @DisplayName("참가자들의 이름에 문제가 없으면 참가자들을 정상적으로 생성한다.")
    void participantsTest() {
        assertThatCode(() -> Participants.from(List.of("leo", "reo", "reoleo"))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("참가자들에게 카드를 한 장씩 나누어준다.")
    void participantsDealTest() {
        participants.deal(Deck.from(new RandomShuffleStrategy()));
        List<Participant> players = participants.findPlayers();
        for (Participant player : players) {
            assertThat(player.getCardNames().size()).isEqualTo(1);
        }
    }

    @Test
    @DisplayName("플레이어(딜러 제외)의 목록을 반환한다.")
    void findPlayersTest() {
        assertThat(participants.findPlayers().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러를 반환한다.")
    void findDealerTest() {
        assertThat(participants.findDealer()).isInstanceOf(Dealer.class);
    }

    @Test
    @DisplayName("딜러와 참가자가 버스트가 아니면, 더 높은 점수의 참가자가 승리한다.")
    void NoBustResultTest() {
        //given
        leo.receiveCard(new Card(Suit.CLOVER, Rank.QUEEN));
        participants.findDealer().receiveCard(new Card(Suit.CLOVER, Rank.ACE));

        final Map<Participant, Integer> participantIntegerMap = participants.makePlayerFinalHandValue();
        final GameResult gameResult = new GameResult(participantIntegerMap, participants.findDealer());

        //when
        Map<Participant, Result> playerResults = gameResult.getPlayerStatus();
        //then
        assertThat(playerResults.get(leo)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러와 참가자가 둘 다 버스트인 경우 무승부를 반환한다.")
    void BothBustTest() {
        //given
        leo.receiveCard(new Card(Suit.CLOVER, Rank.QUEEN));
        leo.receiveCard(new Card(Suit.HEART, Rank.QUEEN));
        leo.receiveCard(new Card(Suit.CLOVER, Rank.TWO));

        Participant dealer = participants.findDealer();
        dealer.receiveCard(new Card(Suit.CLOVER, Rank.QUEEN));
        dealer.receiveCard(new Card(Suit.CLOVER, Rank.KING));
        dealer.receiveCard(new Card(Suit.CLOVER, Rank.THREE));

        //when
        final Map<Participant, Integer> participantIntegerMap = participants.makePlayerFinalHandValue();
        final GameResult gameResult = new GameResult(participantIntegerMap, dealer);

        Map<Participant, Result> playerResults = gameResult.getPlayerStatus();

        //then
        assertThat(playerResults.get(leo)).isEqualTo(Result.TIE);
    }

    @Test
    @DisplayName("딜러가 버스트이고 참가자가 버스트가 아닌 경우 참가자가 승리한다.")
    void playerWinWhenDealerBust() {
        //given
        leo.receiveCard(new Card(Suit.CLOVER, Rank.QUEEN));
        leo.receiveCard(new Card(Suit.HEART, Rank.QUEEN));

        Participant dealer = participants.findDealer();
        dealer.receiveCard(new Card(Suit.CLOVER, Rank.QUEEN));
        dealer.receiveCard(new Card(Suit.CLOVER, Rank.KING));
        dealer.receiveCard(new Card(Suit.CLOVER, Rank.THREE));

        //when
        final Map<Participant, Integer> participantIntegerMap = participants.makePlayerFinalHandValue();
        final GameResult gameResult = new GameResult(participantIntegerMap, dealer);

        Map<Participant, Result> playerResults = gameResult.getPlayerStatus();

        //then
        assertThat(playerResults.get(leo)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("딜러가 버스트가 아니고 참가자가 버스트가 아닌 경우 딜러가 승리한다.")
    void dealerWinWhenPlayerBust() {
        //given
        leo.receiveCard(new Card(Suit.CLOVER, Rank.QUEEN));
        leo.receiveCard(new Card(Suit.HEART, Rank.QUEEN));
        leo.receiveCard(new Card(Suit.CLOVER, Rank.THREE));

        Participant dealer = participants.findDealer();
        dealer.receiveCard(new Card(Suit.CLOVER, Rank.QUEEN));
        dealer.receiveCard(new Card(Suit.CLOVER, Rank.KING));

        //when
        final Map<Participant, Integer> participantIntegerMap = participants.makePlayerFinalHandValue();
        final GameResult gameResult = new GameResult(participantIntegerMap, dealer);

        Map<Participant, Result> playerResults = gameResult.getPlayerStatus();

        //then
        assertThat(playerResults.get(leo)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러와 참가자가 버스트가 아니고, 동점인 경우 카드 개수가 적은 사람이 승리한다.")
    void sameHandValueBothNoBust() {
        //given
        leo.receiveCard(new Card(Suit.CLOVER, Rank.QUEEN));
        leo.receiveCard(new Card(Suit.HEART, Rank.QUEEN));
        Participant dealer = participants.findDealer();
        dealer.receiveCard(new Card(Suit.CLOVER, Rank.FOUR));
        dealer.receiveCard(new Card(Suit.CLOVER, Rank.SIX));
        dealer.receiveCard(new Card(Suit.CLOVER, Rank.KING));

        //when
        final Map<Participant, Integer> participantIntegerMap = participants.makePlayerFinalHandValue();
        final GameResult gameResult = new GameResult(participantIntegerMap, dealer);

        Map<Participant, Result> playerResults = gameResult.getPlayerStatus();
        //then
        assertThat(playerResults.get(leo)).isEqualTo(Result.WIN);
    }

}
