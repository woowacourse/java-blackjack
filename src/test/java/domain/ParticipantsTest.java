package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ParticipantsTest {

    private Participants participants;
    private Deck deck;
    private Participant leo;

    @BeforeEach
    void setUp() {
        participants = Participants.from(List.of("leo", "reo", "reoleo"));
        deck = Deck.from((orderedDeck) -> orderedDeck);
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
    @DisplayName("플레이어의 게임 결과를 반환한다.")
    void getPlayerResult() {
        participants.deal(deck);
        final GameResultManager gameResultManager = new GameResultManager(participants.makePlayerFinalHandValue(),
                participants.findDealer());
        Map<String, Result> playerResults = gameResultManager.getPlayerStatus();
        for (Map.Entry<String, Result> playerResult : playerResults.entrySet()) {
            assertThat(playerResult.getValue()).isEqualTo(Result.LOSE);
        }
        assertThat(playerResults.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러와 참가자가 버스트가 아니면, 더 높은 점수의 참가자가 승리한다.")
    void NoBustResultTest() {
        //given
        leo.receiveCard(new Card("Q클로버", 10));
        participants.findDealer().receiveCard(new Card("A스페이드", 11));

        final Map<Participant, Integer> participantIntegerMap = participants.makePlayerFinalHandValue();
        final GameResultManager gameResultManager = new GameResultManager(participantIntegerMap, participants.findDealer());

        //when
        Map<String, Result> playerResults = gameResultManager.getPlayerStatus();
        //then
        assertThat(playerResults.get("leo")).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러와 참가자가 둘 다 버스트인 경우 무승부를 반환한다.")
    void BothBustTest() {
        //given
        leo.receiveCard(new Card("Q클로버", 10));
        leo.receiveCard(new Card("Q하트", 10));
        leo.receiveCard(new Card("2클로버", 2));

        Participant dealer = participants.findDealer();
        dealer.receiveCard(new Card("Q스페이드", 10));
        dealer.receiveCard(new Card("K스페이드", 10));
        dealer.receiveCard(new Card("3스페이드", 3));

        //when
        final Map<Participant, Integer> participantIntegerMap = participants.makePlayerFinalHandValue();
        final GameResultManager gameResultManager = new GameResultManager(participantIntegerMap, dealer);

        Map<String, Result> playerResults = gameResultManager.getPlayerStatus();

        //then
        assertThat(playerResults.get("leo")).isEqualTo(Result.TIE);
    }

    @Test
    @DisplayName("딜러가 버스트이고 참가자가 버스트가 아닌 경우 참가자가 승리한다.")
    void playerWinWhenDealerBust() {
        //given
        leo.receiveCard(new Card("Q클로버", 10));
        leo.receiveCard(new Card("Q하트", 10));

        Participant dealer = participants.findDealer();
        dealer.receiveCard(new Card("Q스페이드", 10));
        dealer.receiveCard(new Card("K스페이드", 10));
        dealer.receiveCard(new Card("3스페이드", 3));

        //when
        final Map<Participant, Integer> participantIntegerMap = participants.makePlayerFinalHandValue();
        final GameResultManager gameResultManager = new GameResultManager(participantIntegerMap, dealer);

        Map<String, Result> playerResults = gameResultManager.getPlayerStatus();

        //then
        assertThat(playerResults.get("leo")).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("딜러가 버스트가 아니고 참가자가 버스트가 아닌 경우 딜러가 승리한다.")
    void dealerWinWhenPlayerBust() {
        //given
        leo.receiveCard(new Card("Q클로버", 10));
        leo.receiveCard(new Card("Q하트", 10));
        leo.receiveCard(new Card("3스페이드", 3));

        Participant dealer = participants.findDealer();
        dealer.receiveCard(new Card("Q스페이드", 10));
        dealer.receiveCard(new Card("K스페이드", 10));

        //when
        final Map<Participant, Integer> participantIntegerMap = participants.makePlayerFinalHandValue();
        final GameResultManager gameResultManager = new GameResultManager(participantIntegerMap, dealer);

        Map<String, Result> playerResults = gameResultManager.getPlayerStatus();

        //then
        assertThat(playerResults.get("leo")).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러와 참가자가 버스트가 아니고, 동점인 경우 카드 개수가 적은 사람이 승리한다.")
    void sameHandValueBothNoBust() {
        //given
        leo.receiveCard(new Card("Q클로버", 10));
        leo.receiveCard(new Card("Q하트", 10));

        Participant dealer = participants.findDealer();
        dealer.receiveCard(new Card("4스페이드", 4));
        dealer.receiveCard(new Card("6스페이드", 6));
        dealer.receiveCard(new Card("K스페이드", 10));

        //when
        final Map<Participant, Integer> participantIntegerMap = participants.makePlayerFinalHandValue();
        final GameResultManager gameResultManager = new GameResultManager(participantIntegerMap, dealer);

        Map<String, Result> playerResults = gameResultManager.getPlayerStatus();

        //then
        assertThat(playerResults.get("leo")).isEqualTo(Result.WIN);
    }

}
