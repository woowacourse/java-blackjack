package domain.participant.dealer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import static domain.card.CardFixture.cardOf;
import static domain.card.Rank.ACE;
import static domain.card.Rank.FIVE;
import static domain.card.Rank.JACK;
import static domain.card.Rank.KING;
import static domain.card.Rank.NINE;
import static domain.card.Rank.SEVEN;
import static domain.card.Rank.SIX;
import static domain.card.Rank.THREE;
import static domain.card.Rank.TWO;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Hand;
import domain.participant.Participant;
import domain.participant.player.Player;
import domain.participant.player.PlayerFixture;
import domain.participant.player.Players;

class DealerTest {

    @Nested
    class DealTest {
        static Stream<Arguments> deal() {
            return Stream.of(
                    Arguments.of(cardOf(ACE), PlayerFixture.from("Zeus")),
                    Arguments.of(cardOf(TWO), new Dealer(Deck.create()))
            );
        }

        @DisplayName("딜러는 카드를 참가자에게 줄 수 있다.")
        @MethodSource
        @ParameterizedTest
        void deal(Card card, Participant participant) {
            Dealer dealer = new Dealer(new Deck(List.of(card)));

            dealer.deal(participant);
            Hand hand = participant.hand();

            assertThat(hand.cards()).containsExactlyElementsOf(List.of(card));
        }

        @DisplayName("딜러는 게임을 시작할 때 본인과 플레이어에게 카드를 나누어준다.")
        @Test
        void dealInitialCards() {
            Player player1 = PlayerFixture.from("제우스");
            Player player2 = PlayerFixture.from("조이썬");
            Deck deck = new Deck(List.of(
                    cardOf(ACE), cardOf(THREE), cardOf(FIVE), cardOf(SEVEN), cardOf(NINE), cardOf(ACE)));
            Dealer dealer = new Dealer(deck);

            dealer.dealInitialCards(Players.from(new LinkedHashSet<>(List.of(player1,  player2))));

            assertAll(
                    () -> assertThat(dealer.hand().cards()).containsExactlyElementsOf(List.of(cardOf(ACE), cardOf(THREE))),
                    () -> assertThat(player1.hand().cards()).containsExactlyElementsOf(List.of(cardOf(FIVE), cardOf(SEVEN))),
                    () -> assertThat(player2.hand().cards()).containsExactlyElementsOf(List.of(cardOf(NINE), cardOf(ACE)))
            );
        }

        static Stream<Arguments> dealInitialCardsSelf() {
            return Stream.of(
                    Arguments.of(new Deck(List.of(cardOf(ACE), cardOf(ACE))), 12),
                    Arguments.of(new Deck(List.of(cardOf(ACE), cardOf(TWO))), 13),
                    Arguments.of(new Deck(List.of(cardOf(ACE), cardOf(JACK))), 21)
            );
        }

        @DisplayName("딜러는 처음에 받는 에이스 카드 한 장을 11로 계산한다.")
        @MethodSource
        @ParameterizedTest
        void dealInitialCardsSelf(Deck deck, int expected) {
            Dealer dealer = new Dealer(deck);
            dealer.deal(dealer, 2);
            assertThat(dealer.score()).isEqualTo(expected);
        }

        static Stream<Arguments> dealAdditionalCard() {
            return Stream.of(
                    Arguments.of(new Deck(List.of(cardOf(FIVE), cardOf(FIVE), cardOf(ACE))), 21),
                    Arguments.of(new Deck(List.of(cardOf(FIVE), cardOf(SIX), cardOf(ACE))), 12),
                    Arguments.of(new Deck(List.of(cardOf(ACE), cardOf(SIX), cardOf(ACE))), 18)
            );
        }

        @DisplayName("딜러는 추가 에이스 카드를 11로 계산하되, 그렇게 할 때 버스트이거나 이미 에이스 카드를 보유한 경우 1로 계산한다.")
        @MethodSource
        @ParameterizedTest
        void dealAdditionalCard(Deck deck, int expected) {
            Dealer dealer = new Dealer(deck);
            dealer.deal(dealer, 3);
            assertThat(dealer.score()).isEqualTo(expected);
        }
    }

    static Stream<Arguments> isBust() {
        return Stream.of(
                Arguments.of(new Deck(List.of(cardOf(KING), cardOf(SIX), cardOf(SIX))), 22, true),
                Arguments.of(new Deck(List.of(cardOf(FIVE), cardOf(SIX), cardOf(ACE))), 12, false)
        );
    }

    @DisplayName("딜러의 카드 합계를 계산해 버스트를 판단한다.")
    @MethodSource
    @ParameterizedTest
    void isBust(Deck deck, int expectedTotal, boolean expectedBust) {
        Dealer dealer = new Dealer(deck);
        dealer.deal(dealer, 3);
        assertAll(
                () -> assertThat(dealer.score()).isEqualTo(expectedTotal),
                () -> assertThat(dealer.isBust()).isEqualTo(expectedBust)
        );
    }

    static Stream<Arguments> canHit() {
        return Stream.of(
                Arguments.of(new Deck(List.of(cardOf(TWO), cardOf(THREE))), 5, true),
                Arguments.of(new Deck(List.of(cardOf(ACE), cardOf(FIVE))), 16, true),
                Arguments.of(new Deck(List.of(cardOf(ACE), cardOf(SIX))), 17, false)
        );
    }

    @DisplayName("딜러의 카드 합계를 계산해 추가로 카드를 받을 수 있는지 판단한다.")
    @MethodSource
    @ParameterizedTest
    void canHit(Deck deck, int expectedTotal, boolean expectedHit) {
        Dealer dealer = new Dealer(deck);
        dealer.deal(dealer, 2);
        assertAll(
                () -> assertThat(dealer.score()).isEqualTo(expectedTotal),
                () -> assertThat(dealer.canHit()).isEqualTo(expectedHit)
        );
    }
}
