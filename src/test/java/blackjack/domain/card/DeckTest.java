package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.shuffle.CardGenerator;
import blackjack.domain.shuffle.ShuffleCardGenerator;
import blackjack.fixture.TestFixture.TestCardGeneratorGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck(new ShuffleCardGenerator());
    }

    @DisplayName("카드를 여러장 분배한다.")
    @Test
    void spreadCards() {
        // given
        final int count = 2;

        // when
        final Hand hand = deck.spreadCards(count);

        // then
        assertThat(hand.getHand()).hasSize(count);
    }

    @DisplayName("사용한 카드를 뽑았을 경우 다시 뽑는다.")
    @Test
    void spreadAgain() {
        // given
        final CardGenerator cardGenerator = new TestCardGeneratorGenerator();
        final Deck deck = new Deck(cardGenerator);

        // when
        final Card firstCard = deck.spreadCards(1).getFirstCard();
        final Card secondCard = deck.spreadCards(1).getFirstCard();

        // then
        assertAll(
                () -> assertThat(firstCard).isEqualTo(new Card(Shape.DIAMOND, CardScore.TWO)),
                () -> assertThat(secondCard).isEqualTo(new Card(Shape.DIAMOND, CardScore.THREE))
        );
    }
}
