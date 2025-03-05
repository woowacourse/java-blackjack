package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardManager;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.random.CardGenerator;
import blackjack.domain.random.CardRandomGenerator;
import blackjack.fixture.TestFixture.TestCardGeneratorGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardManagerTest {

    @DisplayName("카드를 1장씩 분배한다.")
    @Test
    void spreadOneCard() {
        // given
        final CardGenerator cardGenerator = new CardRandomGenerator();
        final CardManager cardManager = new CardManager(cardGenerator);

        // when
        final Card card = cardManager.spreadOneCard();

        // then
        assertThat(card).isNotNull();
    }

    @DisplayName("사용한 카드를 뽑았을 경우 다시 뽑는다.")
    @Test
    void spreadAgain() {
        // given
        final CardGenerator cardGenerator = new TestCardGeneratorGenerator();
        final CardManager cardManager = new CardManager(cardGenerator);

        // when
        final Card firstCard = cardManager.spreadOneCard();
        final Card secondCard = cardManager.spreadOneCard();

        // then
        assertAll(
                () -> assertThat(firstCard).isEqualTo(new Card(Shape.DIAMOND, Denomination.TWO)),
                () -> assertThat(secondCard).isEqualTo(new Card(Shape.DIAMOND, Denomination.THREE))
        );
    }
}