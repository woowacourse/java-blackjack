package model.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import model.card.Card;
import model.card.Rank;
import model.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {
    Participant player;

    @BeforeEach
    void setUp() {
        player = Player.of("pobi");
    }

    @Test
    void 플레이어의_카드_오픈_테스트() {
        // given
        Card card1 = Card.of(Suit.SPADE, Rank.THREE);
        Card card2 = Card.of(Suit.SPADE, Rank.FOUR);
        player.receive(card1);
        player.receive(card2);

        // when
        player.open();
        List<Card> opened = player.open();

        // then
        assertThat(opened).contains(card1, card2);
        assertThat(opened).containsAll(List.of(card1, card2));
        assertThat(opened).hasSize(2);
    }

    @Nested
    class 승패_판정 {
        @Test
        void 플레이어와의_승패_판정_대상이_딜러가_아니면_예외를_발생한다() {
            Participant otherPlayer = Player.of("jason");
            assertThatThrownBy(() -> player.beats(otherPlayer))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("딜러");
        }

        @ParameterizedTest
        @MethodSource("provideDealerCases")
        void 플레이어가_버스트이면_딜러의_패와_관계없이_패배한다(Dealer dealer) {
            Participant playerWithBust = Player.of("playerWithBust");
            playerWithBust.receive(Card.of(Suit.SPADE, Rank.JACK));
            playerWithBust.receive(Card.of(Suit.SPADE, Rank.QUEEN));
            playerWithBust.receive(Card.of(Suit.SPADE, Rank.KING));

            assertThat(playerWithBust.beats(dealer)).isFalse();
        }

        @ParameterizedTest
        @MethodSource("provideDealerCases")
        void 플레이어가_블랙잭이면_딜러의_패와_관계없이_승리한다(Dealer dealer) {
            Participant playerWithBlackjack = Player.of("playerWithBlackjack");
            playerWithBlackjack.receive(Card.of(Suit.SPADE, Rank.ACE));
            playerWithBlackjack.receive(Card.of(Suit.SPADE, Rank.JACK));

            assertThat(playerWithBlackjack.beats(dealer)).isTrue();
        }

        @ParameterizedTest
        @MethodSource("provideAllCase")
        void 플레이어가_버스트와_블랙잭이_아니라면_점수에_따라_승패를_계산한다(Player player, Dealer dealer, boolean expected) {
            assertThat(player.beats(dealer)).isSameAs(expected);
        }

        private static Stream<Arguments> provideAllCase() {
            return Stream.of(
                    Arguments.of(createPlayerWithScore21(), createDealerWithScore20(), true),
                    Arguments.of(createPlayerWithScore21(), createDealerWithBust(), true),
                    Arguments.of(createPlayerWithScore21(), createDealerWithBlackjack(), false),
                    Arguments.of(createPlayerWithScore20(), createDealerWithScore20(), true),
                    Arguments.of(createPlayerWithScore19(), createDealerWithScore20(), false)
            );
        }

        private static Player createPlayerWithScore21() {
            Player player = Player.of("player");
            player.receive(Card.of(Suit.SPADE, Rank.ACE));
            player.receive(Card.of(Suit.SPADE, Rank.JACK));
            player.receive(Card.of(Suit.SPADE, Rank.QUEEN));

            return player;
        }

        private static Player createPlayerWithScore20() {
            Player player = Player.of("player");
            player.receive(Card.of(Suit.SPADE, Rank.JACK));
            player.receive(Card.of(Suit.SPADE, Rank.QUEEN));

            return player;
        }

        private static Player createPlayerWithScore19() {
            Player player = Player.of("player");
            player.receive(Card.of(Suit.SPADE, Rank.JACK));
            player.receive(Card.of(Suit.SPADE, Rank.NINE));

            return player;
        }

        private static Stream<Arguments> provideDealerCases() {
            return Stream.of(
                    Arguments.of(createDealerWithBust()),
                    Arguments.of(createDealerWithBlackjack()),
                    Arguments.of(createDealerWithScore20())
            );
        }

        private static Dealer createDealerWithBust() {
            Dealer dealer = Dealer.from("딜러");
            dealer.receive(Card.of(Suit.SPADE, Rank.JACK));
            dealer.receive(Card.of(Suit.SPADE, Rank.QUEEN));
            dealer.receive(Card.of(Suit.SPADE, Rank.KING));

            return dealer;
        }

        private static Dealer createDealerWithBlackjack() {
            Dealer dealer = Dealer.from("딜러");
            dealer.receive(Card.of(Suit.SPADE, Rank.ACE));
            dealer.receive(Card.of(Suit.SPADE, Rank.JACK));

            return dealer;
        }

        private static Dealer createDealerWithScore20() {
            Dealer dealer = Dealer.from("딜러");
            dealer.receive(Card.of(Suit.SPADE, Rank.JACK));
            dealer.receive(Card.of(Suit.SPADE, Rank.QUEEN));

            return dealer;
        }
    }
}
