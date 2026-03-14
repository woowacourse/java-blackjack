package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import exception.BlackjackException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayersTest {

    @Nested
    class ConstructorTest {

        @Nested
        class Success {

            @ParameterizedTest
            @MethodSource("successCases")
            void 인원수와_이름이_유효하면_생성된다(List<String> names, int expectedSize) {
                // when
                Players actual = new Players(names);

                // then
                assertThat(actual.getPlayers()).hasSize(expectedSize);
            }

            static Stream<Arguments> successCases() {
                return Stream.of(
                        Arguments.of(List.of("aa", "bb"), 2),
                        Arguments.of(List.of("a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"), 8)
                );
            }
        }

        @Nested
        class Fail {

            @ParameterizedTest
            @ValueSource(ints = {1, 9})
            void 인원수가_범위를_벗어나면_예외가_발생한다(int size) {
                // given
                List<String> names = java.util.stream.IntStream.range(0, size)
                        .mapToObj(i -> String.format("p%d", i))
                        .toList();

                // when & then
                assertThatThrownBy(() -> new Players(names))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(BlackjackException.ERROR_PREFIX + Players.PLAYER_COUNT_OUT_OF_RANGE);
            }

            @ParameterizedTest
            @MethodSource("duplicatedCases")
            void 이름이_중복되면_예외가_발생한다(List<String> names) {
                // when & then
                assertThatThrownBy(() -> new Players(names))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(BlackjackException.ERROR_PREFIX + Players.PLAYER_DUPLICATED);
            }

            static Stream<Arguments> duplicatedCases() {
                return Stream.of(
                        Arguments.of(List.of("aa", "aa")),
                        Arguments.of(List.of("aa", "bb", "aa"))
                );
            }
        }
    }

    @Nested
    class GetPlayerTest {

        @ParameterizedTest
        @MethodSource("successCases")
        void 이름으로_플레이어를_조회한다(String targetName) {
            // given
            Players players = new Players(List.of("jacob", "seoye"));

            // when
            Player actual = players.getPlayer(targetName);

            // then
            assertThat(actual.getName()).isEqualTo(targetName);
        }

        static Stream<Arguments> successCases() {
            return Stream.of(
                    Arguments.of("jacob"),
                    Arguments.of("seoye")
            );
        }

        @ParameterizedTest
        @ValueSource(strings = {"brown", "jason"})
        void 없는_이름이면_예외가_발생한다(String targetName) {
            // given
            Players players = new Players(List.of("jacob", "seoye"));

            // when & then
            assertThatThrownBy(() -> players.getPlayer(targetName))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(BlackjackException.ERROR_PREFIX + Players.NOT_FOUND_PLAYER);
        }
    }

    @Nested
    class GetPlayersTest {

        @ParameterizedTest
        @MethodSource("sizeCases")
        void 전체_플레이어_목록을_조회한다(List<String> names, int expectedSize) {
            // when
            Players actual = new Players(names);

            // then
            assertThat(actual.getPlayers()).hasSize(expectedSize);
        }

        static Stream<Arguments> sizeCases() {
            return Stream.of(
                    Arguments.of(List.of("aa", "bb"), 2),
                    Arguments.of(List.of("aa", "bb", "cc"), 3)
            );
        }

        @ParameterizedTest
        @ValueSource(strings = {"zz"})
        void 반환_목록은_수정할_수_없다(String newPlayerName) {
            // given
            Players players = new Players(List.of("aa", "bb"));
            Player newPlayer = new Player(newPlayerName + "1");

            // when & then
            assertThatThrownBy(() -> players.getPlayers().add(newPlayer))
                    .isInstanceOf(UnsupportedOperationException.class);
        }
    }

    @Nested
    class DrawInitialCardsTest {

        @ParameterizedTest
        @MethodSource("drawCases")
        void 모든_플레이어는_초기_카드를_2장씩_받는다(List<String> names) {
            // given
            Players players = new Players(names);
            Supplier<Card> supplier = fixedCardSupplier(List.of(
                    card(Rank.TEN, Suit.HEART),
                    card(Rank.SIX, Suit.SPADE),
                    card(Rank.ACE, Suit.CLOVER),
                    card(Rank.K, Suit.DIAMOND),
                    card(Rank.TWO, Suit.HEART),
                    card(Rank.THREE, Suit.SPADE),
                    card(Rank.FOUR, Suit.CLOVER),
                    card(Rank.FIVE, Suit.DIAMOND)
            ));

            // when
            players.drawInitialCards(supplier);

            // then
            assertThat(players.getPlayers())
                    .allSatisfy(player -> assertThat(player.getHand()).hasSize(2));
        }

        static Stream<Arguments> drawCases() {
            return Stream.of(
                    Arguments.of(List.of("aa", "bb")),
                    Arguments.of(List.of("aa", "bb", "cc", "dd"))
            );
        }
    }

    @Nested
    class AddCardTest {

        @ParameterizedTest
        @MethodSource("successCases")
        void 이름으로_찾은_플레이어에게_카드를_추가한다(String targetName) {
            // given
            Players players = new Players(List.of("jacob", "seoye"));
            Card card = card(Rank.NINE, Suit.HEART);

            // when
            players.addCard(targetName, card);

            // then
            assertThat(players.getPlayer(targetName).getHand()).containsExactly(card);
        }

        static Stream<Arguments> successCases() {
            return Stream.of(
                    Arguments.of("jacob"),
                    Arguments.of("seoye")
            );
        }

        @ParameterizedTest
        @ValueSource(strings = {"brown"})
        void 없는_플레이어에게_추가하면_예외가_발생한다(String targetName) {
            // given
            Players players = new Players(List.of("jacob", "seoye"));

            // when & then
            assertThatThrownBy(() -> players.addCard(targetName, card(Rank.NINE, Suit.HEART)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(BlackjackException.ERROR_PREFIX + Players.NOT_FOUND_PLAYER);
        }
    }

    private static Supplier<Card> fixedCardSupplier(List<Card> cards) {
        Deque<Card> deque = new ArrayDeque<>(cards);
        return deque::removeFirst;
    }

    private static Card card(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }
}
