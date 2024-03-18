package blackjack.domain.deck;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class DeckGeneratorTest {

    @DisplayName("패턴과 숫자가 모두 다른 52장의 카드를 만든다.")
    @Test
    void generateDeck() {
        //given
        List<Card> deck = DeckGenerator.generateDeck();

        //when
        Set<Card> deckToValidate = new HashSet<>(deck);

        //then
        assertThat(deckToValidate).hasSize(52);
    }
}
