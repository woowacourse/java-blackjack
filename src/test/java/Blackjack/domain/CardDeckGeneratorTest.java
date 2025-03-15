package Blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import Blackjack.domain.game.CardDeck;
import Blackjack.domain.game.CardDeckGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckGeneratorTest {
    @DisplayName("CardDeck 생성 성공")
    @Test
    void createCardDeck() {
        assertThatCode(CardDeckGenerator::generate).doesNotThrowAnyException();
    }

    @DisplayName("CardDeck을 생성할 때마다 카드 순서가 다른 새로운 CardDeck이 생성된다")
    @Test
    void createDifferentCardDeck() {
        CardDeck firstCardDeck = CardDeckGenerator.generate();
        CardDeck secondCardDeck = CardDeckGenerator.generate();

        assertThat(firstCardDeck).isNotEqualTo(secondCardDeck);
        assertThat(firstCardDeck.pickCard()).isNotEqualTo(secondCardDeck.pickCard());
    }
}
