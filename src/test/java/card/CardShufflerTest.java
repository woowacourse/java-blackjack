package card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CardShufflerTest {

    @Test
    void 카드들을_섞는다() {
        //given
        CardShuffler randomCardShuffler = new RandomCardShuffler();
        CardDeck cardDeck1 = CardDeck.prepareDeck(randomCardShuffler);
        CardDeck cardDeck2 = CardDeck.prepareDeck(randomCardShuffler);

        //when & then
        assertThat(cardDeck1.getDeck()).isNotEqualTo(cardDeck2.getDeck());
    }
}
