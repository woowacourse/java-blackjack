package domain.player.participant;

import domain.card.Card;
import domain.player.Name;
import domain.player.participant.betresult.BetResultState;
import domain.player.participant.betresult.BreakEvenState;
import domain.player.participant.betresult.LoseState;
import domain.player.participant.betresult.WinState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.card.CardShape.CLOVER;
import static domain.card.CardShape.SPADE;
import static domain.card.CardValue.ACE;
import static domain.card.CardValue.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Participant 은")
class ParticipantTest {

    final Participant participant = new Participant(new Name("name"), Money.wons(0));

    @BeforeEach
    void makeCardScoreTwenty() {
        participant.hit(new Card(CLOVER, TEN));
        participant.hit(new Card(SPADE, TEN));
    }

    @Test
    @DisplayName("canHit() : 참여자는 21점 미만일 경우 카드를 더 받을 수 있다.")
    void test_canHit_underScore21() {
        // when & then
        assertTrue(participant.canHit());
    }

    @Test
    @DisplayName("canHit() : 참여자는 21점 이상일 경우 카드를 더 받을 수 없다.")
    void test_canHit_overScore21() {
        // when
        participant.hit(new Card(CLOVER, ACE));

        // then
        assertFalse(participant.canHit());
    }

    @Test
    @DisplayName("determineBetState() : 참여자는 베팅 결과를 결정할 수 있다.")
    void test_determineBetState() throws Exception {
        //given
        assertThat(participant).extracting("betResultState")
                               .isNull();

        //when
        participant.determineBetState(new BreakEvenState());

        //then
        assertThat(participant).extracting("betResultState")
                               .isInstanceOf(BetResultState.class);
    }

    @ParameterizedTest
    @MethodSource("betResultState")
    @DisplayName("determineBetMoney() : 참여자는 베팅 결과에 따라 베팅 금액을 반환할 수 있다.")
    void test_determineBetMoney(final Participant participant, final Money bettingResultMoney) throws Exception {
        //when & then
        assertEquals(bettingResultMoney, participant.determineBetMoney());
    }

    static Stream<Arguments> betResultState() {

        Participant participant1 = new Participant(new Name("name1"), Money.wons(10000));
        participant1.determineBetState(new WinState());

        Participant participant2 = new Participant(new Name("name2"), Money.wons(5000));
        participant2.determineBetState(new LoseState());

        Participant participant3 = new Participant(new Name("name3"), Money.wons(1000));
        participant3.determineBetState(new BreakEvenState());

        return Stream.of(
                Arguments.of(participant1, Money.wons(15000)),
                Arguments.of(participant2, Money.wons(-5000)),
                Arguments.of(participant3, Money.wons(1000))
        );
    }

    @Test
    @DisplayName("hasNotBetState() : 배팅금액상태가 결정되지 않을 수 있다.")
    void test_hasNotBetState() throws Exception {
        //when & then
        assertTrue(participant.hasNotBetState());
    }
}
