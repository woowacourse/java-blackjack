package domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import domain.TestFixture;
import domain.card.vo.Card;
import domain.card.vo.Rank;
import domain.card.vo.Suit;
import domain.participants.Participant;
import domain.state.finished.Blackjack;
import domain.state.finished.Bust;
import domain.state.running.Hit;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


class RunningTest {


    public static Stream<Arguments> getStartState() {
        return Stream.of(
                Arguments.of(TestFixture.createDefaultPlayerByRank(List.of(Rank.ACE, Rank.KING)), Blackjack.class),
                Arguments.of(TestFixture.createDefaultDealer(List.of(Rank.ACE, Rank.KING)), Blackjack.class),
                Arguments.of(TestFixture.createDefaultPlayerByRank(List.of(Rank.SIX, Rank.KING)), Hit.class),
                Arguments.of(TestFixture.createDefaultDealer(List.of(Rank.SIX, Rank.KING)), Hit.class)

        );
    }

    @ParameterizedTest
    @DisplayName("getStartState(): 첫 카드가 21이라면 블랙잭을 아니라면 Hit를 반환한다.")
    @MethodSource
    void getStartState(Participant participant, Class<?> clazz) {
        assertThat(participant.getState()).isInstanceOf(clazz);
    }

    @Nested
    @DisplayName("drawCard(): ")
    class DrawCard {
        public static Stream<Arguments> drawCard() {
            return Stream.of(
                    // --- 플레이어 ---
                    //21이 넘는다면 버스트를 반환한다.
                    Arguments.of(TestFixture.createDefaultPlayerByRank(List.of(Rank.KING, Rank.KING)),
                            new Card(Rank.KING, Suit.SPADE),
                            Bust.class, "Player", "20"),
                    //21이 넘지 않는다면 다시 Hit을 반환하여 다음 상태에 대기한다.
                    Arguments.of(TestFixture.createDefaultPlayerByRank(List.of(Rank.ACE, Rank.ACE)),
                            new Card(Rank.KING, Suit.SPADE),
                            Hit.class, "Player", "12(Ace 2)"),
                    // --- 딜러 ---
                    // 16 이하라면 무조건 드로우 한다. -> 21 넘으면 버스트 반환
                    Arguments.of(TestFixture.createDefaultDealer(List.of(Rank.KING, Rank.SIX)),
                            new Card(Rank.KING, Suit.SPADE),
                            Bust.class, "Dealer", "16"),
                    // 16 이하이면 무조건 드로우 한다. -> 16 넘으면 Hit 반환 -> Dealer 에서 Hit 전략으로 인해 toStay() 호출
                    Arguments.of(TestFixture.createDefaultDealer(List.of(Rank.THREE, Rank.FOUR)),
                            new Card(Rank.KING, Suit.SPADE),
                            Hit.class, "Dealer", "7"),
                    // 16 이하이면 무조건 드로우 한다. -> 16이하라면 Hit 반환
                    Arguments.of(TestFixture.createDefaultDealer(List.of(Rank.THREE, Rank.THREE)),
                            new Card(Rank.KING, Suit.SPADE),
                            Hit.class, "Dealer", "6")
            );
        }

        @ParameterizedTest(name = "{3}: {4} draw K(10) -> '{'{2}'}'")
        @DisplayName("힛 여부에 따라 다음 상태를 반환한다.")
        @MethodSource
        void drawCard(Participant participant, Card card, Class<?> clazz, String participantName,
                      String score) {
            participant.drawCard(card);
            assertThat(participant.getState()).isInstanceOf(clazz);
        }
    }
}
