package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {
    private Player tori;

    @BeforeEach
    void setUp() {
        tori = Player.from("tori");
    }

    @DisplayName("이름을 입력받아 Player를 생성한다.")
    @Test
    void Should_Create_When_NewPlayer() {
        assertThat(tori.getName()).isEqualTo("tori");
    }

    @DisplayName("플레이어에게 카드를 추가할 수 있다.")
    @Test
    void Should_Success_When_AddCard() {
        Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        tori.addCard(card);

        assertThat(tori.getAllCards()).contains(card);
    }

    @DisplayName("플레이어가 가지고 있는 모든 카드를 확인한다.")
    @Test
    void Should_Success_When_GetAllCards() {
        Card card = new Card(CardNumber.NINE, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.TWO, CardSymbol.HEARTS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        tori.addCard(card);
        tori.addCard(card2);
        tori.addCard(card3);

        assertThat(tori.getAllCards()).containsAll(List.of(card, card2, card3));
    }

    @DisplayName("플레이어가 가지고 있는 카드의 점수를 계산한다.")
    @Nested
    class calculateTest {
        @DisplayName("ACE가 없으면 카드의 숫자로 합을 계산한다.")
        @Test
        void Should_Success_When_CalculateScore() {
            Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
            Card card2 = new Card(CardNumber.KING, CardSymbol.HEARTS);
            tori.addCard(card);
            tori.addCard(card2);

            assertThat(tori.calculateScore()).isEqualTo(20);
        }

        @DisplayName("ACE를 가지고 있을 때")
        @Nested
        class HavaACE {
            @DisplayName("카드 점수의 합이 21점을 넘지 않으면, ACE의 점수를 11점으로 계산한다.")
            @Test
            void Should_ACEScoreIs11_When_Burst() {
                Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
                Card card2 = new Card(CardNumber.ACE, CardSymbol.HEARTS);
                tori.addCard(card);
                tori.addCard(card2);

                assertThat(tori.calculateScore()).isEqualTo(21);
            }

            @DisplayName("카드 점수의 합이 21점을 넘는다면, ACE의 점수를 1점으로 계산한다.")
            @Test
            void Should_AIs1_When_Burst() {
                Card card = new Card(CardNumber.NINE, CardSymbol.HEARTS);
                Card card2 = new Card(CardNumber.TWO, CardSymbol.HEARTS);
                Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);
                tori.addCard(card);
                tori.addCard(card2);
                tori.addCard(card3);

                assertThat(tori.calculateScore()).isEqualTo(12);
            }
        }
    }

    @DisplayName("카드의 합이 21을 넘지 않을 경우 Hit을 할 수 있다.")
    @Nested
    class Hit {
        @DisplayName("카드의 합이 21일 경우 Hit을 할 수 없다.")
        @Test
        void Should_isHitFalse_When_MoreThan17() {
            Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
            Card card2 = new Card(CardNumber.NINE, CardSymbol.HEARTS);
            Card card3 = new Card(CardNumber.TWO, CardSymbol.HEARTS);
            tori.addCard(card);
            tori.addCard(card2);
            tori.addCard(card3);

            assertThat(tori.isHitPossible()).isFalse();
        }

        @DisplayName("카드의 합이 20일 경우 Hit을 할 수 있다.")
        @Test
        void Should_isHitTrue_When_LessThan17() {
            Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
            Card card2 = new Card(CardNumber.KING, CardSymbol.HEARTS);
            tori.addCard(card);
            tori.addCard(card2);

            assertThat(tori.isHitPossible()).isTrue();
        }
    }
}
