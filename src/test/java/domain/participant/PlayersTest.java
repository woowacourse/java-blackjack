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
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Nested
    class ConstructorTest {

        @Test
        void 유효한_이름과_배팅_금액이면_플레이어_목록을_생성한다() {
            // when
            Players actual = players(
                    List.of("jacob", "seoye"),
                    List.of("1000", "500")
            );

            // then
            assertThat(actual.getPlayers()).hasSize(2);
            assertThat(actual.getPlayers().get(0).getName()).isEqualTo("jacob");
            assertThat(actual.getPlayers().get(1).getName()).isEqualTo("seoye");
        }

        @Test
        void 플레이어_수가_2명_미만이면_예외가_발생한다() {
            // when & then
            assertThatThrownBy(() -> players(
                    List.of("jacob"),
                    List.of("1000")
            ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(BlackjackException.ERROR_PREFIX + Players.PLAYER_COUNT_OUT_OF_RANGE);
        }

        @Test
        void 플레이어_수가_8명_초과이면_예외가_발생한다() {
            // when & then
            assertThatThrownBy(() -> players(
                    List.of("aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii"),
                    List.of("100", "100", "100", "100", "100", "100", "100", "100", "100")
            ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(BlackjackException.ERROR_PREFIX + Players.PLAYER_COUNT_OUT_OF_RANGE);
        }

        @Test
        void 플레이어_이름이_중복되면_예외가_발생한다() {
            // when & then
            assertThatThrownBy(() -> players(
                    List.of("jacob", "jacob"),
                    List.of("1000", "500")
            ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(BlackjackException.ERROR_PREFIX + Players.PLAYER_DUPLICATED);
        }
    }

    @Nested
    class GetPlayerTest {

        @Test
        void 이름으로_플레이어를_조회한다() {
            // given
            Players players = players(
                    List.of("jacob", "seoye"),
                    List.of("1000", "500")
            );

            // when
            Player actual = players.getPlayer("seoye");

            // then
            assertThat(actual.getName()).isEqualTo("seoye");
            assertThat(actual.getBetAmount()).isEqualTo(500);
        }

        @Test
        void 존재하지_않는_이름이면_예외가_발생한다() {
            // given
            Players players = players(
                    List.of("jacob", "seoye"),
                    List.of("1000", "500")
            );

            // when & then
            assertThatThrownBy(() -> players.getPlayer("brown"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(BlackjackException.ERROR_PREFIX + Players.NOT_FOUND_PLAYER);
        }
    }

    @Nested
    class GetPlayersTest {

        @Test
        void 전체_플레이어_목록을_조회한다() {
            // given
            Players players = players(
                    List.of("jacob", "seoye"),
                    List.of("1000", "500")
            );

            // when
            List<Player> actual = players.getPlayers();

            // then
            assertThat(actual).hasSize(2);
            assertThat(actual.get(0).getName()).isEqualTo("jacob");
            assertThat(actual.get(1).getName()).isEqualTo("seoye");
        }

        @Test
        void 반환_목록은_수정할_수_없다() {
            // given
            Players players = players(
                    List.of("jacob", "seoye"),
                    List.of("1000", "500")
            );

            // when & then
            assertThatThrownBy(() -> players.getPlayers().add(new Player(new PlayerName("brown"), new BetAmount("700"))))
                    .isInstanceOf(UnsupportedOperationException.class);
        }
    }

    @Nested
    class DrawInitialCardsTest {

        @Test
        void 모든_플레이어는_초기_카드를_2장씩_받는다() {
            // given
            Players players = players(
                    List.of("jacob", "seoye"),
                    List.of("1000", "500")
            );
            Supplier<Card> cardSupplier = fixedCardSupplier(List.of(
                    card(Rank.TEN, Suit.HEART),
                    card(Rank.SIX, Suit.SPADE),
                    card(Rank.ACE, Suit.CLOVER),
                    card(Rank.K, Suit.DIAMOND)
            ));

            // when
            players.drawInitialCards(cardSupplier);

            // then
            assertThat(players.getPlayer("jacob").getHand())
                    .containsExactly(card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE));
            assertThat(players.getPlayer("seoye").getHand())
                    .containsExactly(card(Rank.ACE, Suit.CLOVER), card(Rank.K, Suit.DIAMOND));
        }
    }

    @Nested
    class AddCardTest {

        @Test
        void 이름으로_찾은_플레이어에게_카드를_추가한다() {
            // given
            Players players = players(
                    List.of("jacob", "seoye"),
                    List.of("1000", "500")
            );
            Card card = card(Rank.NINE, Suit.HEART);

            // when
            players.addCard("jacob", card);

            // then
            assertThat(players.getPlayer("jacob").getHand()).containsExactly(card);
        }

        @Test
        void 없는_플레이어에게_카드를_추가하면_예외가_발생한다() {
            // given
            Players players = players(
                    List.of("jacob", "seoye"),
                    List.of("1000", "500")
            );

            // when & then
            assertThatThrownBy(() -> players.addCard("brown", card(Rank.NINE, Suit.HEART)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(BlackjackException.ERROR_PREFIX + Players.NOT_FOUND_PLAYER);
        }
    }

    @Nested
    class PlayerIsBustTest {

        @Test
        void 플레이어_점수가_21_초과면_true를_반환한다() {
            // given
            Players players = players(
                    List.of("jacob", "seoye"),
                    List.of("1000", "500")
            );
            players.addCard("jacob", card(Rank.TEN, Suit.HEART));
            players.addCard("jacob", card(Rank.NINE, Suit.SPADE));
            players.addCard("jacob", card(Rank.THREE, Suit.CLOVER));

            // when
            boolean actual = players.playerIsBust("jacob");

            // then
            assertThat(actual).isTrue();
        }

        @Test
        void 플레이어_점수가_21_이하면_false를_반환한다() {
            // given
            Players players = players(
                    List.of("jacob", "seoye"),
                    List.of("1000", "500")
            );
            players.addCard("jacob", card(Rank.TEN, Suit.HEART));
            players.addCard("jacob", card(Rank.SEVEN, Suit.SPADE));

            // when
            boolean actual = players.playerIsBust("jacob");

            // then
            assertThat(actual).isFalse();
        }
    }

    private static Players players(List<String> names, List<String> betAmounts) {
        return new Players(toPlayerNames(names), toBetAmounts(betAmounts));
    }

    private static List<PlayerName> toPlayerNames(List<String> names) {
        return names.stream()
                .map(PlayerName::new)
                .toList();
    }

    private static List<BetAmount> toBetAmounts(List<String> betAmounts) {
        return betAmounts.stream()
                .map(BetAmount::new)
                .toList();
    }

    private static Supplier<Card> fixedCardSupplier(List<Card> cards) {
        Deque<Card> deque = new ArrayDeque<>(cards);
        return deque::removeFirst;
    }

    private static Card card(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }
}
