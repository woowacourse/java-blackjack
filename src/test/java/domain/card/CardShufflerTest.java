package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CardShufflerTest {

    @Test
    void 카드들을_섞는다() {
        //given
        CardShuffler randomCardShuffler = new RandomCardShuffler();
        CardDeck cardDeck1 = CardDeck.createCards(randomCardShuffler);
        CardDeck cardDeck2 = CardDeck.createCards(randomCardShuffler);

        //when & then
        assertThat(cardDeck1.getDeck()).isNotEqualTo(cardDeck2.getDeck());
    }
}
