package domain;

import domain.card.Deck;
import domain.card.RandomShuffleStrategy;
import domain.participant.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ParticipantsTest {

    private Participants participants;

    @BeforeEach
    void setUp() {
        participants = Participants.from(
                List.of(
                        Player.create(new Name("reo"), new BetAmount(new BigDecimal(1500))),
                        Player.create(new Name("leo"), new BetAmount(new BigDecimal(1500))),
                        Player.create(new Name("reoleo"), new BetAmount(new BigDecimal(1500)))));
    }

    @Test
    @DisplayName("참가자들의 이름에 문제가 없으면 참가자들을 정상적으로 생성한다.")
    void participantsTest() {
        assertThatCode(() -> Participants.from(
                List.of(
                        Player.create(new Name("leo"), new BetAmount(new BigDecimal(1500))),
                        Player.create(new Name("reo"), new BetAmount(new BigDecimal(1500))),
                        Player.create(new Name("reoleo"), new BetAmount(new BigDecimal(1500)))))
                ).doesNotThrowAnyException();
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

}
