package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {
    Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @DisplayName("생성 테스트")
    @Test
    void Should_Create_When_NewDealer() {
        assertDoesNotThrow(Dealer::new);
    }

    @DisplayName("카드 추가 테스트")
    @Test
    void Should_Success_When_AddCard() {
        Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        dealer.addCard(card);

        assertThat(dealer.getAllCards()).contains(card);
    }

    @DisplayName("카드 Get 테스트")
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

    @DisplayName("첫번째 카드만 Get 테스트")
    @Test
    void Should_FirstCard_When_GetFirstCards() {
        Card card = new Card(CardNumber.NINE, CardSymbol.HEARTS);
        dealer.addCard(card);

        assertThat(dealer.getAllCards()).size().isEqualTo(1);
    }

    @DisplayName("카드 리스트에 ACE 존재 여부 확인 테스트")
    @Nested
    class hasACE {
        @DisplayName("ACE가 있을 때")
        @Test
        void Should_True_When_hasACE() {
            Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);
            dealer.addCard(card);

            assertThat(dealer.hasACE()).isTrue();
        }

        @DisplayName("ACE가 없을 때")
        @Test
        void Should_False_When_hasACE() {
            Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
            dealer.addCard(card);

            assertThat(dealer.hasACE()).isFalse();
        }
    }

    @DisplayName("카드 점수 계산")
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
            @DisplayName("ACE의 점수를 11점으로 계산하는 경우 테스트")
            @Test
            void Should_ACEScoreIs11_When_Burst() {
                Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
                Card card2 = new Card(CardNumber.ACE, CardSymbol.HEARTS);
                dealer.addCard(card);
                dealer.addCard(card2);

                assertThat(dealer.calculateScore()).isEqualTo(21);
            }

            @DisplayName("ACE의 점수를 1점으로 계산하는 경우 테스트")
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

    @DisplayName("Hit 테스트")
    @Nested
    class Hit {
        @DisplayName("카드의 합이 17 이상일 경우 Hit 불가능")
        @Test
        void Should_isHitFalse_When_MoreThan17() {
            Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
            Card card2 = new Card(CardNumber.SEVEN, CardSymbol.HEARTS);
            dealer.addCard(card);
            dealer.addCard(card2);

            assertThat(dealer.isHit()).isFalse();
        }

        @DisplayName("딜러는 카드의 합이 17을 초과하지 않는다면 Hit 가능")
        @Test
        void Should_isHitTrue_When_LessThan17() {
            Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
            Card card2 = new Card(CardNumber.SIX, CardSymbol.HEARTS);
            dealer.addCard(card);
            dealer.addCard(card2);

            assertThat(dealer.isHit()).isTrue();
        }
    }
}
