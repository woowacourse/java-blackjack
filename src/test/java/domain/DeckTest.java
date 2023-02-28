package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void 덱은_52가지_종류의_카드가_있다() {
        //given
        List<Card> expectedCards = Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Suit.values())
                                .map(suit -> new Card(rank, suit)))
                .collect(Collectors.toUnmodifiableList());

        Deck deck = new Deck();

        //when

        // then
        assertThat(deck.getCards()).isEqualTo(expectedCards);
    }

}