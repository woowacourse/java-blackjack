package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
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
        List<Participant> participantsList = participants.getParticipants();
        for (Participant participant : participantsList) {
            Assertions.assertThat(participant.getCardNames().size()).isEqualTo(1);
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
}
