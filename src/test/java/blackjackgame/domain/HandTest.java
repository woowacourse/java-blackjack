package blackjackgame.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HandTest {
    private final Card ten = new Card(Symbol.CLOVER, CardValue.KING);
    private final Card nine = new Card(Symbol.CLOVER, CardValue.NINE);
    private final Card ace = new Card(Symbol.CLOVER, CardValue.ACE);
    private final Card two = new Card(Symbol.CLOVER, CardValue.TWO);
    private final Card three = new Card(Symbol.CLOVER, CardValue.THREE);
    private final Card four = new Card(Symbol.CLOVER, CardValue.FOUR);

    @DisplayName("핸드 생성 시 생성자에 입력된 두 장의 카드만 가지는지 확인한다.")
    @Test
    void Should_HandHasTwoCards_When_CreateHand() {
        Card spade5 = new Card(Symbol.SPADE, CardValue.FIVE);
        Card heart8 = new Card(Symbol.HEART, CardValue.EIGHT);
        Hand hand = new Hand(spade5, heart8);
        List<Card> cards = hand.getCards();

        Assertions.assertThat(cards).containsExactlyInAnyOrder(spade5, heart8);
    }


    @DisplayName("핸드가 가진 카드들의 합을 반환한다.")
    @ParameterizedTest(name = "핸드가 가진 카드의 합은 {1}이다.")
    @MethodSource("cardDummy")
    void Should_ReturnScore_When_RequestHand(final Card firstCard, final Card secondCard, final List<Card> hitCards, final int expected) {
        Hand hand = new Hand(firstCard, secondCard);
        for (Card card : hitCards) {
            hand.addCard(card);
        }

        assertThat(hand.getScore()).isEqualTo(expected);
    }

    @DisplayName("핸드에 카드를 추가하면 가지고 있는 카드의 개수가 늘어난다")
    @Test
    void Should_SizePlusOne_When_AddCard() {
        Card spade5 = new Card(Symbol.SPADE, CardValue.FIVE);
        Card clover8 = new Card(Symbol.CLOVER, CardValue.EIGHT);
        Card heartQ = new Card(Symbol.HEART, CardValue.QUEEN);

        Hand hand = new Hand(spade5, clover8);
        hand.addCard(heartQ);

        assertThat(hand.getCards().size()).isEqualTo(3);
    }

    @Nested
    @DisplayName("딜러의 핸드와 참가자의 핸드가 같을 때, ")
    class SameScore {
        @DisplayName("점수가 21을 넘었다면 게스트 패")
        @Test
        void Should_GuestLose_When_Over21() {
            Hand guestHand = new Hand(ten, nine);
            guestHand.addCard(three);

            Hand dealerHand = new Hand(ten, nine);
            dealerHand.addCard(three);

            GameOutcome gameOutcome = guestHand.calculateOutcomeComparedBy(dealerHand);

            assertThat(gameOutcome).isEqualTo(GameOutcome.LOSE);
        }

        @DisplayName("점수가 21이하일때 무승부")
        @Test
        void Should_GuestDraw_When_Below21() {
            Hand guestHand = new Hand(ten, nine);
            guestHand.addCard(two);

            Hand dealerHand = new Hand(ten, nine);
            dealerHand.addCard(two);

            GameOutcome gameOutcome = guestHand.calculateOutcomeComparedBy(dealerHand);

            assertThat(gameOutcome).isEqualTo(GameOutcome.DRAW);
        }
    }

    @Nested
    @DisplayName("딜러의 점수가 높을 때, ")
    class DealerHigherScore {
        @DisplayName("딜러와 게스트의 점수가 21을 초과한 경우 게스트 패")
        @Test
        void Should_GuestLose_When_DealerAndGuestOver21() {
            Hand guestHand = new Hand(ten, nine);
            guestHand.addCard(three);

            Hand dealerHand = new Hand(ten, nine);
            dealerHand.addCard(four);

            GameOutcome gameOutcome = guestHand.calculateOutcomeComparedBy(dealerHand);

            assertThat(gameOutcome).isEqualTo(GameOutcome.LOSE);
        }

        @DisplayName("딜러의 점수가 21을 초과하고 게스트의 점수가 21이하인 경우 게스트 승")
        @Test
        void Should_GuestWin_When_DealerOver21GuestBelow21() {
            Hand guestHand = new Hand(ten, nine);
            guestHand.addCard(two);

            Hand dealerHand = new Hand(ten, nine);
            dealerHand.addCard(three);

            GameOutcome gameOutcome = guestHand.calculateOutcomeComparedBy(dealerHand);

            assertThat(gameOutcome).isEqualTo(GameOutcome.WIN);
        }

        @DisplayName("딜러의 점수가 21이하고 게스트의 점수가 21 초과인 경우 게스트 패")
        @Test
        void Should_GuestLose_When_DealerBelow21GuestOver21() {
            Hand guestHand = new Hand(ten, nine);
            guestHand.addCard(three);

            Hand dealerHand = new Hand(ten, nine);
            dealerHand.addCard(two);

            GameOutcome gameOutcome = guestHand.calculateOutcomeComparedBy(dealerHand);

            assertThat(gameOutcome).isEqualTo(GameOutcome.LOSE);
        }

        @DisplayName("딜러와 게스트의 점수가 21이하인 경우, 게스트 패")
        @Test
        void Should_GuestLose_When_DealerAndGuestBelow21() {
            Hand guestHand = new Hand(ten, nine);
            guestHand.addCard(ace);

            Hand dealerHand = new Hand(ten, nine);
            dealerHand.addCard(two);

            GameOutcome gameOutcome = guestHand.calculateOutcomeComparedBy(dealerHand);

            assertThat(gameOutcome).isEqualTo(GameOutcome.LOSE);
        }
    }

    @Nested
    @DisplayName("게스트의 점수가 높을 때, ")
    class GuestHigherScore {
        @DisplayName("딜러와 게스트의 점수가 21을 초과한 경우 게스트 패")
        @Test
        void Should_GuestLose_When_DealerAndGuestOver21() {
            Hand guestHand = new Hand(ten, nine);
            guestHand.addCard(four);

            Hand dealerHand = new Hand(ten, nine);
            dealerHand.addCard(three);

            GameOutcome gameOutcome = guestHand.calculateOutcomeComparedBy(dealerHand);

            assertThat(gameOutcome).isEqualTo(GameOutcome.LOSE);
        }

        @DisplayName("딜러의 점수가 21이하이고 게스트의 점수가 21초과인 경우 게스트 패")
        @Test
        void Should_GuestWin_When_DealerBelow21GuestOver21() {
            Hand guestHand = new Hand(ten, nine);
            guestHand.addCard(three);

            Hand dealerHand = new Hand(ten, nine);
            dealerHand.addCard(two);

            GameOutcome gameOutcome = guestHand.calculateOutcomeComparedBy(dealerHand);

            assertThat(gameOutcome).isEqualTo(GameOutcome.LOSE);
        }

        @DisplayName("딜러의 점수가 21초과이고 게스트의 점수가 21 이하인 경우 게스트 승")
        @Test
        void Should_GuestLose_When_DealerOver21GuestBelow21() {
            Hand guestHand = new Hand(ten, nine);
            guestHand.addCard(two);

            Hand dealerHand = new Hand(ten, nine);
            dealerHand.addCard(three);

            GameOutcome gameOutcome = guestHand.calculateOutcomeComparedBy(dealerHand);

            assertThat(gameOutcome).isEqualTo(GameOutcome.WIN);
        }

        @DisplayName("딜러와 게스트의 점수가 21이하인 경우, 게스트 승")
        @Test
        void Should_GuestLose_When_DealerAndGuestBelow21() {
            Hand guestHand = new Hand(ten, nine);
            guestHand.addCard(two);

            Hand dealerHand = new Hand(ten, nine);
            dealerHand.addCard(ace);

            GameOutcome gameOutcome = guestHand.calculateOutcomeComparedBy(dealerHand);

            assertThat(gameOutcome).isEqualTo(GameOutcome.WIN);
        }
    }

    @Nested
    @DisplayName("게스트가 블랙잭일 때, ")
    class GuestBlackJack {
        @DisplayName("딜러가 블랙잭일 때 무승부")
        @Test
        void Should_Draw_When_DealerAndGuestBlackJack() {
            Hand guestHand = new Hand(ten, ace);

            Hand dealerHand = new Hand(ten, ace);

            GameOutcome gameOutcome = guestHand.calculateOutcomeComparedBy(dealerHand);

            assertThat(gameOutcome).isEqualTo(GameOutcome.DRAW);
        }

        @DisplayName("딜러의 점수가 21일 때 게스트 승")
        @Test
        void Should_GuestWin_When_Dealer21AndGuestBlackJack() {
            Hand guestHand = new Hand(ten, ace);

            Hand dealerHand = new Hand(ten, nine);
            dealerHand.addCard(two);

            GameOutcome gameOutcome = guestHand.calculateOutcomeComparedBy(dealerHand);

            assertThat(gameOutcome).isEqualTo(GameOutcome.BLACKJACK_WIN);
        }
    }

    static Stream<Arguments> cardDummy() {
        return Stream.of(
                Arguments.arguments(new Card(Symbol.HEART, CardValue.FIVE),
                        new Card(Symbol.DIAMOND, CardValue.TWO), Collections.emptyList(), 7),
                Arguments.arguments(new Card(Symbol.SPADE, CardValue.ACE),
                        new Card(Symbol.SPADE, CardValue.ACE), Collections.emptyList(), 12),
                Arguments.arguments(new Card(Symbol.SPADE, CardValue.FIVE),
                        new Card(Symbol.SPADE, CardValue.ACE), List.of(new Card(Symbol.SPADE, CardValue.ACE)), 17),
                Arguments.arguments(new Card(Symbol.SPADE, CardValue.ACE),
                        new Card(Symbol.SPADE, CardValue.NINE), List.of(new Card(Symbol.SPADE, CardValue.FIVE)), 15)
        );
    }
}
