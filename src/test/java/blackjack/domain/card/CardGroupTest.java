package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.result.Score;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CardGroupTest {

    @Test
    @DisplayName("카드 그룹에 카드 하나를 추가하는 기능 테스트")
    void addCardTest() {
        final Card firstCard = new Card(CardShape.SPADE, CardNumber.ACE);
        final Card secondCard = new Card(CardShape.SPADE, CardNumber.EIGHT);
        final Card thirdCard = new Card(CardShape.HEART, CardNumber.TWO);
        final CardGroup cards = new CardGroup(firstCard, secondCard);

        cards.add(thirdCard);

        assertThat(cards).extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .contains(firstCard, secondCard, thirdCard);
    }

    @Nested
    @DisplayName("점수를 계산하는 기능 테스트들")
    class calculateScore {
        final CardGroup cardGroup = new CardGroup(
                new Card(CardShape.SPADE, CardNumber.TEN),
                new Card(CardShape.SPADE, CardNumber.THREE));

        @Test
        @DisplayName("에이스가 없는 경우")
        void calculateScoreTest() {
            final Score score = cardGroup.getScore();

            assertThat(score.getValue()).isEqualTo(13);
        }

        @Test
        @DisplayName("Ace를 포함하고, 21이 넘는 경우, Ace는 1로 계산되는 기능 테스트")
        void calculateScoreIfContainAceAndOver21Test() {
            cardGroup.add(new Card(CardShape.CLOVER, CardNumber.ACE));

            final Score score = cardGroup.getScore();

            assertThat(score.getValue()).isEqualTo(14);
        }
    }
}

