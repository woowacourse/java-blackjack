package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardBundleTest {

    @Test
    @DisplayName("카드는 52장의 서로 다른 카드를 반환한다.")
    void should_return_52_other_cards() {
        //given
        CardBundle cardBundle = new CardBundle();

        //when
        List<Card> allCards = cardBundle.getAllCards();
        
        //then
        Set<Card> testAllCards = new HashSet<>(allCards);
        assertThat(allCards).hasSize(testAllCards.size());
    }

    @Test
    @DisplayName("카드는 52장의 서로 다른 카드를 섞어서 반환한다.")
    void should_return_52_other_cards_shuffled() {
        //given
        CardBundle cardBundle = new CardBundle();

        //when
        List<Card> shuffledAllCards = cardBundle.getShuffledAllCards();

        //then
        List<Card> expected = cardBundle.getAllCards();
        assertThat(shuffledAllCards).containsExactlyInAnyOrderElementsOf(expected);
    }
}
