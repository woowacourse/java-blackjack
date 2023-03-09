package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @DisplayName("생성 테스트")
    @Test
    void Should_Create_When_NewPlayer() {
        assertDoesNotThrow(() -> new Player(new Name("name")));
    }

    @DisplayName("카드 추가 테스트")
    @Test
    void Should_Success_When_AddCard() {
        Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        Player tori = new Player(new Name("tori"));
        tori.addCard(card);
        assertThat(tori.getAllCards()).contains(card);
    }

    @DisplayName("카드 Get 테스트")
    @Test
    void Should_Success_When_GetAllCards() {
        Card card = new Card(CardNumber.NINE, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.TWO, CardSymbol.HEARTS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);

        Player tori = new Player(new Name("tori"));

        tori.addCard(card);
        tori.addCard(card2);
        tori.addCard(card3);

        assertThat(tori.getAllCards()).containsAll(List.of(card, card2, card3));
    }

    @DisplayName("카드 점수 계산")
    @Nested
    class calculateTest {
        @DisplayName("ACE가 없을 때")
        @Test
        void Should_Success_When_CalculateScore() {
            Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
            Card card2 = new Card(CardNumber.KING, CardSymbol.HEARTS);

            Player tori = new Player(new Name("tori"));

            tori.addCard(card);
            tori.addCard(card2);

            assertThat(tori.calculateScore()).isEqualTo(20);
        }

        @DisplayName("ACE를 가지고 있을 때")
        @Nested
        class HavaACE {
            @DisplayName("ACE의 점수를 11점으로 계산하는 경우 테스트")
            @Test
            void Should_ACEScoreIs11_When_Burst() {
                Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
                Card card2 = new Card(CardNumber.ACE, CardSymbol.HEARTS);

                Player tori = new Player(new Name("tori"));

                tori.addCard(card);
                tori.addCard(card2);

                assertThat(tori.calculateScore()).isEqualTo(21);
            }

            @DisplayName("ACE의 점수를 1점으로 계산하는 경우 테스트")
            @Test
            void Should_AIs1_When_Burst() {
                Card card = new Card(CardNumber.NINE, CardSymbol.HEARTS);
                Card card2 = new Card(CardNumber.TWO, CardSymbol.HEARTS);
                Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);

                Player tori = new Player(new Name("tori"));

                tori.addCard(card);
                tori.addCard(card2);
                tori.addCard(card3);

                assertThat(tori.calculateScore()).isEqualTo(12);
            }
        }
    }

    @DisplayName("Hit 테스트")
    @Nested
    class Hit {
        @DisplayName("카드의 합이 21 이상일 경우 Hit 불가능")
        @Test
        void Should_isHitFalse_When_MoreThan17() {
            Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
            Card card2 = new Card(CardNumber.NINE, CardSymbol.HEARTS);
            Card card3 = new Card(CardNumber.TWO, CardSymbol.HEARTS);

            Player tori = new Player(new Name("tori"));

            tori.addCard(card);
            tori.addCard(card2);
            tori.addCard(card3);

            assertThat(tori.isHit()).isFalse();
        }

        @DisplayName("딜러는 카드의 합이 21을 초과하지 않는다면 Hit 가능")
        @Test
        void Should_isHitTrue_When_LessThan17() {
            Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
            Card card2 = new Card(CardNumber.KING, CardSymbol.HEARTS);

            Player tori = new Player(new Name("tori"));

            tori.addCard(card);
            tori.addCard(card2);

            assertThat(tori.isHit()).isTrue();
        }
    }
}
