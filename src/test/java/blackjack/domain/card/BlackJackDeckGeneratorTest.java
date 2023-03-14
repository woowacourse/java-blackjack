package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BlackJackDeckGeneratorTest {

    @DisplayName("블랙잭 게임 덱은 카드의 중복이 존재하지 않는다.")
    @Test
    void should_HasAllUniqueCards() {
        BlackJackDeckGenerator blackJackDeckGenerator = new BlackJackDeckGenerator();
        Deck deck = blackJackDeckGenerator.generate();

        List<Card> allCards = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            allCards.add(deck.draw());
        }

        int setSize = new HashSet<>(allCards).size();
        assertThat(allCards).hasSize(setSize);
    }

    @DisplayName("블랙잭 게임 덱은 52장의 카드를 가진다.")
    @Test
    void should_Has52Cards() {
        BlackJackDeckGenerator blackJackDeckGenerator = new BlackJackDeckGenerator();
        Deck deck = blackJackDeckGenerator.generate();

        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThatThrownBy(() -> deck.draw())
                .isInstanceOf(IllegalStateException.class);
    }
}
