package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Nested
    class OfTest {

        @Test
        void 플레이어_이름과_배팅_금액으로_참가자를_생성한다() {
            // given
            List<PlayerName> playerNames = playerNames();
            List<BetAmount> betAmounts = betAmounts("1000", "500");

            // when
            Participants actual = Participants.of(playerNames, betAmounts);

            // then
            assertThat(actual.dealer()).isNotNull();
            assertThat(actual.players().getPlayers()).hasSize(2);
            assertThat(actual.players().getPlayers().get(0).getName()).isEqualTo("jacob");
            assertThat(actual.players().getPlayers().get(1).getName()).isEqualTo("seoye");
        }
    }

    @Nested
    class DrawInitialCardsTest {

        @Test
        void 딜러는_2장_모든_플레이어는_각각_2장을_받는다() {
            // given
            Participants participants = participants();
            Supplier<Card> cardSupplier = fixedCardSupplier(List.of(
                    card(Rank.TEN, Suit.HEART),
                    card(Rank.SIX, Suit.SPADE),
                    card(Rank.ACE, Suit.CLOVER),
                    card(Rank.K, Suit.DIAMOND),
                    card(Rank.NINE, Suit.HEART),
                    card(Rank.THREE, Suit.CLOVER)
            ));

            // when
            participants.drawInitialCards(cardSupplier);

            // then
            assertThat(participants.dealer().getHand())
                    .containsExactly(card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE));
            assertThat(participants.players().getPlayer("jacob").getHand())
                    .containsExactly(card(Rank.ACE, Suit.CLOVER), card(Rank.K, Suit.DIAMOND));
            assertThat(participants.players().getPlayer("seoye").getHand())
                    .containsExactly(card(Rank.NINE, Suit.HEART), card(Rank.THREE, Suit.CLOVER));
        }
    }

    @Nested
    class DrawCardsByDealerTest {

        @Test
        void 딜러에게_카드를_한_장_추가한다() {
            // given
            Participants participants = participants();
            participants.dealer().addCard(card(Rank.TEN, Suit.HEART));
            participants.dealer().addCard(card(Rank.SIX, Suit.SPADE));
            Supplier<Card> cardSupplier = fixedCardSupplier(List.of(card(Rank.ACE, Suit.CLOVER)));

            // when
            participants.drawCardsByDealer(cardSupplier);

            // then
            assertThat(participants.dealer().getHand())
                    .containsExactly(card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE), card(Rank.ACE, Suit.CLOVER));
        }
    }

    @Nested
    class DrawCardsByPlayerTest {

        @Test
        void 이름으로_찾은_플레이어에게_카드를_추가하고_플레이어를_반환한다() {
            // given
            Participants participants = participants();
            Supplier<Card> cardSupplier = fixedCardSupplier(List.of(card(Rank.FOUR, Suit.DIAMOND)));

            // when
            Player actual = participants.drawCardsByPlayer("jacob", cardSupplier);

            // then
            assertThat(actual.getName()).isEqualTo("jacob");
            assertThat(actual.getHand()).containsExactly(card(Rank.FOUR, Suit.DIAMOND));
        }

        @Test
        void 존재하지_않는_플레이어_이름이면_예외를_던진다() {
            // given
            Participants participants = participants();
            Supplier<Card> cardSupplier = fixedCardSupplier(List.of(card(Rank.FOUR, Suit.DIAMOND)));

            // when & then
            assertThatThrownBy(() -> participants.drawCardsByPlayer("brown", cardSupplier))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(Players.NOT_FOUND_PLAYER);
        }
    }

    private static Participants participants() {
        return Participants.of(playerNames(), betAmounts("1000", "500"));
    }

    private static List<PlayerName> playerNames() {
        return List.of(new PlayerName("jacob"), new PlayerName("seoye"));
    }

    private static List<BetAmount> betAmounts(String first, String second) {
        return List.of(new BetAmount(first), new BetAmount(second));
    }

    private static Supplier<Card> fixedCardSupplier(List<Card> cards) {
        Deque<Card> deque = new ArrayDeque<>(cards);
        return deque::removeFirst;
    }

    private static Card card(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }
}
