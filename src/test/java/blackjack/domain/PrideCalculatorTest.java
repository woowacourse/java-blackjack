package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.card.Card;
import blackjack.domain.cards.card.denomination.Denomination;
import blackjack.domain.cards.card.denomination.Suit;
import blackjack.domain.participant.human.Dealer;
import blackjack.domain.participant.human.Human;
import blackjack.domain.participant.human.Player;
import blackjack.domain.result.PrideCalculator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PrideCalculatorTest {
    private final Player player = Player.fromText("pobi").initBetting(10000);

    void addCardList(Human human, List<String> cards) {
        for (String card : cards) {
            human.addCard(Card.of(Denomination.fromInitial(card), Suit.CLOVER));
        }
    }

    @Nested
    @DisplayName("플레이어 1.5배 우승 결과 테스트")
    class PlayerWinMaxTest {
        PrideCalculator prideCalculator;

        @BeforeEach
        void setup() {
            // given
            addCardList(player, List.of("A", "10"));

            Dealer dealer = new Dealer(List.of(
                    Card.of(Denomination.EIGHT, Suit.CLOVER),
                    Card.of(Denomination.TEN, Suit.CLOVER),
                    Card.of(Denomination.TEN, Suit.SPADE)
            ));

            // when
            prideCalculator = new PrideCalculator(player, dealer);
        }

        @Test
        void resultTest() {
            // then
            assertThat(prideCalculator.get())
                    .isEqualTo(15000);
        }
    }

    @Nested
    @DisplayName("플레이어 2장블랙잭/딜러블랙잭 무승부 결과 테스트")
    class PlayerWinMax2Test {
        PrideCalculator prideCalculator;

        @BeforeEach
        void setup() {
            // given
            addCardList(player, List.of("A", "10"));

            Dealer dealer = new Dealer(List.of(
                    Card.of(Denomination.ACE, Suit.CLOVER),
                    Card.of(Denomination.TEN, Suit.CLOVER)
            ));

            // when
            prideCalculator = new PrideCalculator(player, dealer);
        }

        @Test
        void resultTest() {
            // then
            assertThat(prideCalculator.get())
                    .isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("딜러 버스트 결과 테스트")
    class DealerOverTest {
        PrideCalculator prideCalculator;

        @BeforeEach
        void setup() {
            // given
            addCardList(player, List.of("5", "10"));

            Dealer dealer = new Dealer(List.of(
                    Card.of(Denomination.FIVE, Suit.CLOVER),
                    Card.of(Denomination.TEN, Suit.CLOVER),
                    Card.of(Denomination.NINE, Suit.CLOVER)
            ));

            // when
            prideCalculator = new PrideCalculator(player, dealer);
        }

        @Test
        void resultTest() {
            // then
            assertThat(prideCalculator.get())
                    .isEqualTo(10000);
        }
    }

    @Nested
    @DisplayName("딜러 21이하, 플레이어 21이하 결과 딜러승리 테스트")
    class DealerPlayerUnder1Test {
        PrideCalculator prideCalculator;

        @BeforeEach
        void setup() {
            // given
            addCardList(player, List.of("5", "10"));

            Dealer dealer = new Dealer(List.of(
                    Card.of(Denomination.NINE, Suit.CLOVER),
                    Card.of(Denomination.TEN, Suit.CLOVER),
                    Card.of(Denomination.ACE, Suit.CLOVER)
            ));

            // when
            prideCalculator = new PrideCalculator(player, dealer);
        }

        @Test
        void resultTest() {
            // then
            assertThat(prideCalculator.get())
                    .isEqualTo(-10000);
        }
    }

    @Nested
    @DisplayName("딜러 21이하, 플레이어 21이하 결과 플레이어 승리 테스트")
    class DealerPlayerUnder2Test {
        PrideCalculator prideCalculator;

        @BeforeEach
        void setup() {
            // given
            addCardList(player, List.of("5", "10"));

            Dealer dealer = new Dealer(List.of(
                    Card.of(Denomination.TWO, Suit.CLOVER),
                    Card.of(Denomination.TEN, Suit.CLOVER)
            ));

            // when
            prideCalculator = new PrideCalculator(player, dealer);
        }

        @Test
        void resultTest() {
            // then
            assertThat(prideCalculator.get())
                    .isEqualTo(10000);
        }
    }

    @Nested
    @DisplayName("딜러 21이하, 플레이어 21이하 결과 무승부 테스트")
    class DealerPlayerUnder3Test {
        PrideCalculator prideCalculator;

        @BeforeEach
        void setup() {
            // given
            addCardList(player, List.of("5", "10"));

            Dealer dealer = new Dealer(List.of(
                    Card.of(Denomination.SEVEN, Suit.CLOVER),
                    Card.of(Denomination.EIGHT, Suit.CLOVER)
            ));

            // when
            prideCalculator = new PrideCalculator(player, dealer);
        }

        @Test
        void resultTest() {
            // then
            assertThat(prideCalculator.get())
                    .isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("딜러 21이하, 플레이어 버스트 결과 테스트")
    class DealerUnderPlayerHighTest {
        PrideCalculator prideCalculator;

        @BeforeEach
        void setup() {
            // given
            addCardList(player, List.of("5", "10", "9"));

            Dealer dealer = new Dealer(List.of(
                    Card.of(Denomination.SEVEN, Suit.CLOVER),
                    Card.of(Denomination.EIGHT, Suit.CLOVER)
            ));

            // when
            prideCalculator = new PrideCalculator(player, dealer);
        }

        @Test
        void resultTest() {
            // then
            assertThat(prideCalculator.get())
                    .isEqualTo(-10000);
        }
    }
}
