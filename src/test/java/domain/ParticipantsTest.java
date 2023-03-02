package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    private Participants participants;
    private Deck deck;

    @BeforeEach
    void setUp() {
        participants = Participants.from(List.of("leo", "reo", "reoleo"));
        deck = Deck.from((orderedDeck) -> orderedDeck);
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
        List<Player> players = participants.findPlayers();
        for (Player player : players) {
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
    @DisplayName("참가자들의 handValue를 계산한다.")
    void getScores() {
        participants.deal(deck);

        Map<Participant, String> scores = participants.getScores();
        for (Player player : participants.findPlayers()) {
            assertThat(scores.get(player)).isEqualTo("10");
        }
    }

    @Test
    @DisplayName("플레이어의 게임 결과를 반환한다.")
    void getPlayerResult() {
        participants.deal(deck);
        Map<String, Result> playerResults = participants.getPlayerResults();
        for (Map.Entry<String, Result> playerResult : playerResults.entrySet()) {
            assertThat(playerResult.getValue()).isEqualTo(Result.LOSE);
        }
        assertThat(playerResults.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러와 참가자가 버스트가 아니면, 더 높은 점수의 참가자가 승리한다.")
    void NoBustResultTest() {
        //given
        Participants participant = Participants.from(List.of("abc"));
        for (Player player : participant.findPlayers()) {
            player.receiveCard(new Card("Q클로버", 10));
        }

        //when
        participant.findDealer().receiveCard(new Card("A스페이드", 11));

        Map<String, Result> playerResults = participant.getPlayerResults();
        Map<Result, Integer> dealerResults = participant.getDealerResults(playerResults);

        //then
        assertThat(playerResults.get("abc")).isEqualTo(Result.LOSE);
        assertThat(dealerResults.get(Result.WIN)).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러와 참가자가 둘 다 버스트인 경우 무승부를 반환한다.")
    void BothBustTest() {
        //given
        Participants participant = Participants.from(List.of("abc"));
        for (Player player : participant.findPlayers()) {
            player.receiveCard(new Card("Q클로버", 10));
            player.receiveCard(new Card("Q하트", 10));
            player.receiveCard(new Card("2클로버", 2));
        }
        Dealer dealer = participant.findDealer();
        dealer.receiveCard(new Card("Q스페이드", 10));
        dealer.receiveCard(new Card("K스페이드", 10));
        dealer.receiveCard(new Card("3스페이드", 3));

        //when
        Map<String, Result> playerResults = participant.getPlayerResults();
        Map<Result, Integer> dealerResults = participant.getDealerResults(playerResults);

        //then
        assertThat(playerResults.get("abc")).isEqualTo(Result.TIE);
        assertThat(dealerResults.get(Result.TIE)).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 버스트이고 참가자가 버스트가 아닌 경우 참가자가 승리한다.")
    void playerWinWhenDealerBust() {
        //given
        Participants participant = Participants.from(List.of("abc"));
        for (Player player : participant.findPlayers()) {
            player.receiveCard(new Card("Q클로버", 10));
            player.receiveCard(new Card("Q하트", 10));
        }
        Dealer dealer = participant.findDealer();
        dealer.receiveCard(new Card("Q스페이드", 10));
        dealer.receiveCard(new Card("K스페이드", 10));
        dealer.receiveCard(new Card("3스페이드", 3));

        //when
        Map<String, Result> playerResults = participant.getPlayerResults();
        Map<Result, Integer> dealerResults = participant.getDealerResults(playerResults);

        //then
        assertThat(playerResults.get("abc")).isEqualTo(Result.WIN);
        assertThat(dealerResults.get(Result.LOSE)).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 버스트가 아니고 참가자가 버스트가 아닌 경우 딜러가 승리한다.")
    void dealerWinWhenPlayerBust() {
        //given
        Participants participant = Participants.from(List.of("abc"));
        for (Player player : participant.findPlayers()) {
            player.receiveCard(new Card("Q클로버", 10));
            player.receiveCard(new Card("Q하트", 10));
            player.receiveCard(new Card("3스페이드", 3));
        }
        Dealer dealer = participant.findDealer();
        dealer.receiveCard(new Card("Q스페이드", 10));
        dealer.receiveCard(new Card("K스페이드", 10));

        //when
        Map<String, Result> playerResults = participant.getPlayerResults();
        Map<Result, Integer> dealerResults = participant.getDealerResults(playerResults);

        //then
        assertThat(playerResults.get("abc")).isEqualTo(Result.LOSE);
        assertThat(dealerResults.get(Result.WIN)).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러와 참가자가 버스트가 아니고, 동점인 경우 카드 개수가 적은 사람이 승리한다.")
    void sameHandValueBothNoBust() {
        //given
        Participants participant = Participants.from(List.of("abc"));
        for (Player player : participant.findPlayers()) {
            player.receiveCard(new Card("Q클로버", 10));
            player.receiveCard(new Card("Q하트", 10));
        }
        Dealer dealer = participant.findDealer();
        dealer.receiveCard(new Card("4스페이드", 4));
        dealer.receiveCard(new Card("6스페이드", 6));
        dealer.receiveCard(new Card("K스페이드", 10));

        //when
        Map<String, Result> playerResults = participant.getPlayerResults();
        Map<Result, Integer> dealerResults = participant.getDealerResults(playerResults);

        //then
        assertThat(playerResults.get("abc")).isEqualTo(Result.WIN);
        assertThat(dealerResults.get(Result.LOSE)).isEqualTo(1);
    }

}
