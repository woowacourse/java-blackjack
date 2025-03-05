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
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardManagerTest {

    private CardManager cardManager;

    @BeforeEach
    void setUp() {
        cardManager = new CardManager(new CardRandomGenerator());
    }

    @DisplayName("카드를 여러장 분배한다.")
    @Test
    void spreadCards() {
        // given
        final int count = 2;

        // when
        final List<Card> cards = cardManager.spreadCards(count);

        // then
        assertThat(cards).hasSize(count);
    }

    @DisplayName("사용한 카드를 뽑았을 경우 다시 뽑는다.")
    @Test
    void spreadAgain() {
        // given
        final CardGenerator cardGenerator = new TestCardGeneratorGenerator();
        final CardManager cardManager = new CardManager(cardGenerator);

        // when
        final Card firstCard = cardManager.spreadCards(1).getFirst();
        final Card secondCard = cardManager.spreadCards(1).getFirst();

        // then
        assertAll(
                () -> assertThat(firstCard).isEqualTo(new Card(Shape.DIAMOND, Denomination.TWO)),
                () -> assertThat(secondCard).isEqualTo(new Card(Shape.DIAMOND, Denomination.THREE))
        );
    }
}