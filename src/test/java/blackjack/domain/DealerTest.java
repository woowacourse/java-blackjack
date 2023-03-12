package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @DisplayName("생성되었을 때 가지고 있는 카드가 없다.")
    @Test
    void Should_Create_When_NewDealer() {
        assertThat(dealer.getAllCards().size()).isEqualTo(0);
    }

    @DisplayName("딜러에게 카드를 추가할 수 있다.")
    @Test
    void Should_Success_When_AddCard() {
        Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        dealer.addCard(card);

        assertThat(dealer.getAllCards()).contains(card);
    }

    @DisplayName("딜러가 가지고 있는 모든 카드를 확인할 수 있다.")
    @Test
    void Should_Success_When_GetAllCards() {
        Card card = new Card(CardNumber.NINE, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.TWO, CardSymbol.HEARTS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        dealer.addCard(card);
        dealer.addCard(card2);
        dealer.addCard(card3);

        assertThat(dealer.getAllCards()).containsAll(List.of(card, card2, card3));
    }

    @DisplayName("딜러가 가지고 있는 첫번째 카드만 확인할 수 있다.")
    @Test
    void Should_FirstCard_When_GetFirstCards() {
        Card card = new Card(CardNumber.NINE, CardSymbol.HEARTS);
        dealer.addCard(card);

        assertThat(dealer.getAllCards()).size().isEqualTo(1);
    }

    @DisplayName("딜러가 가지고 있는 카드의 점수를 계산한다.")
    @Nested
    class calculateTest {
        @DisplayName("ACE가 없을 때")
        @Test
        void Should_Success_When_CalculateScore() {
            Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
            Card card2 = new Card(CardNumber.KING, CardSymbol.HEARTS);
            dealer.addCard(card);
            dealer.addCard(card2);

            assertThat(dealer.calculateScore()).isEqualTo(20);
        }

        @DisplayName("ACE를 가지고 있을 때")
        @Nested
        class HavaACE {
            @DisplayName("카드의 점수의 합이 21점을 넘지 않으면, ACE의 점수를 11점으로 계산한다.")
            @Test
            void Should_ACEScoreIs11_When_Burst() {
                Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
                Card card2 = new Card(CardNumber.ACE, CardSymbol.HEARTS);
                dealer.addCard(card);
                dealer.addCard(card2);

                assertThat(dealer.calculateScore()).isEqualTo(21);
            }

            @DisplayName("카드 점수의 합이 21점을 넘는다면, ACE의 점수를 1점으로 계산한다.")
            @Test
            void Should_AIs1_When_Burst() {
                Card card = new Card(CardNumber.NINE, CardSymbol.HEARTS);
                Card card2 = new Card(CardNumber.TWO, CardSymbol.HEARTS);
                Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);
                dealer.addCard(card);
                dealer.addCard(card2);
                dealer.addCard(card3);

                assertThat(dealer.calculateScore()).isEqualTo(12);
            }
        }
    }

    @DisplayName("카드의 합이 17을 넘지 않을 경우 Hit을 할 수 있다.")
    @Nested
    class Hit {
        @DisplayName("카드의 합이 17일 경우 Hit을 할 수 없다.")
        @Test
        void Should_isHitFalse_When_MoreThan17() {
            Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
            Card card2 = new Card(CardNumber.SEVEN, CardSymbol.HEARTS);
            dealer.addCard(card);
            dealer.addCard(card2);

            assertThat(dealer.isHitPossible()).isFalse();
        }

        @DisplayName("카드의 합이 16일 경우 Hit을 할 수 있다.")
        @Test
        void Should_isHitTrue_When_LessThan17() {
            Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
            Card card2 = new Card(CardNumber.SIX, CardSymbol.HEARTS);
            dealer.addCard(card);
            dealer.addCard(card2);

            assertThat(dealer.isHitPossible()).isTrue();
        }
    }
}
