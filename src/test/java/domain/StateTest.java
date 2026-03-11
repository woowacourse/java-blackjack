package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Deck;
import domain.card.vo.Rank;
import domain.state.BlackJack;
import domain.state.Bust;
import domain.state.Hit;
import domain.state.State;
import domain.state.Stay;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


class StateTest {


    public static Stream<Arguments> getStartState() {
        return Stream.of(
                Arguments.of(TestFixture.createDefaultPlayerStateByRank(List.of(Rank.ACE, Rank.KING)), BlackJack.class),
                Arguments.of(TestFixture.createDefaultDealerState(List.of(Rank.ACE, Rank.KING)), BlackJack.class),
                Arguments.of(TestFixture.createDefaultPlayerStateByRank(List.of(Rank.SIX, Rank.KING)), Hit.class),
                Arguments.of(TestFixture.createDefaultDealerState(List.of(Rank.SIX, Rank.KING)), Hit.class)

        );
    }

    @ParameterizedTest
    @DisplayName("getStartState(): 첫 카드가 21이라면 블랙잭을 아니라면 Hit를 반환한다.")
    @MethodSource
    void getStartState(State state, Class<?> clazz) {
        assertThat(state).isInstanceOf(clazz);
    }

    @Nested
    @DisplayName("drawCard(): ")
    class DrawCard {
        public static Stream<Arguments> drawCard() {
            return Stream.of(
                    // --- 플레이어 ---
                    //Hit을 원하지 않는다면 Stay를 반환한다.
                    Arguments.of(TestFixture.createDefaultPlayerStateByRank(List.of(Rank.KING, Rank.KING)),
                            Deck.createFromList(TestFixture.createCards()),
                            false, Stay.class, "Player", "21"),
                    //21이 넘는다면 버스트를 반환한다.
                    Arguments.of(TestFixture.createDefaultPlayerStateByRank(List.of(Rank.KING, Rank.KING)),
                            Deck.createFromList(TestFixture.createCards()),
                            true, Bust.class, "Player", "20"),
                    //21이 넘지 않는다면 다시 Hit을 반환하여 다음 상태에 대기한다.
                    Arguments.of(TestFixture.createDefaultPlayerStateByRank(List.of(Rank.ACE, Rank.ACE)),
                            Deck.createFromList(TestFixture.createCards()),
                            true, Hit.class, "Player", "12(Ace 2)"),
                    // --- 딜러 ---
                    // 16 이하라면 무조건 드로우 한다. -> 21 넘으면 버스트 반환
                    Arguments.of(TestFixture.createDefaultDealerState(List.of(Rank.KING, Rank.SIX)),
                            Deck.createFromList(TestFixture.createCards()),
                            true, Bust.class, "Dealer", "16"),
                    // 16 이하이면 무조건 드로우 한다. -> 16 넘으면 Stay 반환
                    Arguments.of(TestFixture.createDefaultDealerState(List.of(Rank.THREE, Rank.FOUR)),
                            Deck.createFromList(TestFixture.createCards()),
                            true, Stay.class, "Dealer", "14"),
                    // 16 이하이면 무조건 드로우 한다. -> 16이하라면 Hit 반환
                    Arguments.of(TestFixture.createDefaultDealerState(List.of(Rank.THREE, Rank.THREE)),
                            Deck.createFromList(TestFixture.createCards()),
                            true, Hit.class, "Dealer", "6")
            );
        }

        @ParameterizedTest(name = "{4}'{'K 추가'}' \t 핸드 점수'{'{5}'}'  hit'{'{2}'}' -> {3}")
        @DisplayName("힛 여부에 따라 다음 상태를 반환한다.")
        @MethodSource
        void drawCard(State state, Deck deck, boolean toHit, Class<?> clazz, String participantName, String score) {
            assertThat(state.drawCard(deck, toHit))
                    .isInstanceOf(clazz);
        }
    }
}
