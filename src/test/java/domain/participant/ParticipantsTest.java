package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

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
    class FromTest {

        @Test
        void 참가자_이름으로_딜러와_플레이어_묶음을_생성한다() {
            // given
            List<String> names = List.of("jacob", "seoye");

            // when
            Participants actual = Participants.from(names);

            // then
            assertThat(actual.dealer()).isNotNull();
            assertThat(actual.players().getPlayers()).hasSize(2);
            assertThat(actual.players().getPlayers().get(0).getName()).isEqualTo("jacob");
            assertThat(actual.players().getPlayers().get(1).getName()).isEqualTo("seoye");
        }
    }

    @Nested
    class DealerShouldHitTest {

        @Test
        void 딜러_점수가_16_이하면_true를_반환한다() {
            // given
            Participants participants = Participants.from(List.of("jacob", "seoye"));
            participants.dealer().addCard(card(Rank.TEN, Suit.HEART));
            participants.dealer().addCard(card(Rank.SIX, Suit.SPADE));

            // when
            boolean actual = participants.dealerShouldHit();

            // then
            assertThat(actual).isTrue();
        }

        @Test
        void 딜러_점수가_17_이상이면_false를_반환한다() {
            // given
            Participants participants = Participants.from(List.of("jacob", "seoye"));
            participants.dealer().addCard(card(Rank.TEN, Suit.HEART));
            participants.dealer().addCard(card(Rank.SEVEN, Suit.SPADE));

            // when
            boolean actual = participants.dealerShouldHit();

            // then
            assertThat(actual).isFalse();
        }
    }

    @Nested
    class DrawInitialCardsTest {

        @Test
        void 딜러는_2장_모든_플레이어도_각각_2장을_받는다() {
            // given
            Participants participants = Participants.from(List.of("jacob", "seoye"));
            Supplier<Card> supplier = fixedCardSupplier(List.of(
                    card(Rank.TEN, Suit.HEART),
                    card(Rank.SIX, Suit.SPADE),
                    card(Rank.ACE, Suit.CLOVER),
                    card(Rank.K, Suit.DIAMOND),
                    card(Rank.NINE, Suit.HEART),
                    card(Rank.THREE, Suit.CLOVER)
            ));

            // when
            participants.drawInitialCards(supplier);

            // then
            assertThat(participants.dealer().getHand()).hasSize(2);
            assertThat(participants.players().getPlayer("jacob").getHand()).hasSize(2);
            assertThat(participants.players().getPlayer("seoye").getHand()).hasSize(2);
            assertThat(participants.dealer().getHand()).containsExactly(
                    card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE)
            );
        }
    }

    @Nested
    class DrawCardsByDealerTest {

        @Test
        void 딜러에게_카드를_한_장_추가한다() {
            // given
            Participants participants = Participants.from(List.of("jacob", "seoye"));
            participants.dealer().addCard(card(Rank.TEN, Suit.HEART));
            participants.dealer().addCard(card(Rank.SIX, Suit.SPADE));
            Supplier<Card> supplier = fixedCardSupplier(List.of(card(Rank.ACE, Suit.CLOVER)));

            // when
            participants.drawCardsByDealer(supplier);

            // then
            assertThat(participants.dealer().getHand()).hasSize(3);
            assertThat(participants.dealer().getHand().get(2)).isEqualTo(card(Rank.ACE, Suit.CLOVER));
        }
    }

    @Nested
    class DrawCardsByPlayerTest {

        @Test
        void 이름으로_찾은_플레이어에게_카드를_추가하고_플레이어를_반환한다() {
            // given
            Participants participants = Participants.from(List.of("jacob", "seoye"));
            Supplier<Card> supplier = fixedCardSupplier(List.of(card(Rank.FOUR, Suit.DIAMOND)));

            // when
            Player actual = participants.drawCardsByPlayer("jacob", supplier);

            // then
            assertThat(actual.getName()).isEqualTo("jacob");
            assertThat(actual.getHand()).containsExactly(card(Rank.FOUR, Suit.DIAMOND));
        }
    }

    @Nested
    class GetPlayerTest {

        @Test
        void 이름으로_플레이어를_조회한다() {
            // given
            Participants participants = Participants.from(List.of("jacob", "seoye"));

            // when
            Player actual = participants.getPlayer("seoye");

            // then
            assertThat(actual.getName()).isEqualTo("seoye");
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
