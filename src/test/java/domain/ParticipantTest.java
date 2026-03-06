package domain;

import static org.junit.jupiter.api.Assertions.*;

import strategy.AceDrawStrategy;
import strategy.RandomStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ParticipantTest {

    static Hand emptyHand = new Hand(new RandomStrategy(),new ArrayList<>());
    static Hand playingHand = new Hand(
            new RandomStrategy(),
            List.of(new Card(CardRank.QUEEN, CardMark.SPADE),
                    new Card(CardRank.EIGHT, CardMark.HEART)));
    static Hand bustedHand = new Hand(
            new RandomStrategy(),
            List.of(new Card(CardRank.QUEEN, CardMark.SPADE),
                    new Card(CardRank.EIGHT, CardMark.HEART),
                    new Card(CardRank.QUEEN, CardMark.CLOVER)));

    @ParameterizedTest(name = "[{index}] 참여자: {0}")
    @MethodSource("participantsAndNames")
    @DisplayName("참여자는 이름을 반환할 수 있어야 한다.")
    void 참여자_이름_반환(String expected, Participant participant) {
        String actual = participant.name();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("참여자(딜러)는 버스트(패의 합 21 초과) 상태인지 반환할 수 있어야 한다.")
    void 딜러_버스트_상태_확인() {
        Participant participant = new Dealer("딜러", bustedHand);

        assertTrue(participant.isBusted());
    }

    @Test
    @DisplayName("참여자(플레이어)는 버스트(패의 합 21 초과) 상태인지 반환할 수 있어야 한다.")
    void 플레이어_버스트_상태_확인() {
        Participant participant = new Player("pobi", bustedHand);

        assertTrue(participant.isBusted());
    }

    @Test
    @DisplayName("참여자(딜러)는 버스트(패의 합 21 초과) 상태인지 반환할 수 있어야 한다.")
    void 딜러_버스트_아닌_상태_확인() {
        Participant participant = new Dealer("딜러", playingHand);

        assertFalse(participant.isBusted());
    }

    @Test
    @DisplayName("참여자(플레이어)는 버스트(패의 합 21 초과) 상태인지 반환할 수 있어야 한다.")
    void 플레이어_버스트_아닌_상태_확인() {
        Participant participant = new Player("pobi", playingHand);

        assertFalse(participant.isBusted());
    }

    @Test
    @DisplayName("A카드 드로우 시 버스트 상태라면 1로 처리한다.")
    void A카드_1처리_확인() {
        Hand customHand = new Hand(
                new AceDrawStrategy(),
                List.of(new Card(CardRank.QUEEN, CardMark.SPADE),
                        new Card(CardRank.EIGHT, CardMark.HEART)));

        Participant participant = new Player("pobi", customHand);
        participant.draw();
        int expected = 19;
        int actual = participant.scoreSum();

        assertEquals(expected, actual);
        assertTrue(participant.isBusted());
    }

    private static Stream<Arguments> participantsAndNames() {
        return Stream.of(
                Arguments.arguments("딜러", new Dealer("딜러", emptyHand)),
                Arguments.arguments("pobi", new Player("pobi", emptyHand)),
                Arguments.arguments("jason", new Player("jason", emptyHand)),
                Arguments.arguments("tars", new Player("tars", emptyHand))
        );
    }
}
