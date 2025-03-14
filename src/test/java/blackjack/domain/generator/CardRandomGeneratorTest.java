package blackjack.domain.generator;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.CardGenerator;
import blackjack.domain.card.generator.ShuffleCardGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardRandomGeneratorTest {

    private CardGenerator cardGenerator;

    @BeforeEach
    void setUp() {
        cardGenerator = new ShuffleCardGenerator();
    }

    @DisplayName("카드를 랜덤으로 한장 뽑는다.")
    @Test
    void pickOneRandomCard() {
        // given

        // when
        final Card card = cardGenerator.pickCard();

        // then
        assertThat(card).isNotNull();
    }

    @Test
    @DisplayName("카드가 더이상 없을 경우 예외가 발생한다.")
    void pickRandomCardWhenEmpty() {
        // Given
        final Deck deck = new Deck(cardGenerator);
        final int deckSize = 52;
        deck.spreadCards(deckSize);

        // When & Then
        Assertions.assertThatThrownBy(() -> cardGenerator.pickCard())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("[ERROR] 카드가 더이상 없습니다.");
    }
}
