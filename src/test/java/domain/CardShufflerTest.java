package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardShufflerTest {

    @Test
    void 카드들을_섞는다() {
        //given
        CardShuffler cardShuffler = new CardShuffler();
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());

        //when
        List<Card> actual = cardShuffler.shuffle(cardDeck.getDeck());

        //then
        Assertions.assertThat(actual).isNotEqualTo(cardDeck.getDeck());
    }
}
