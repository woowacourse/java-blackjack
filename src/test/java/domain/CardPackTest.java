package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CardPackTest {

    @Test
    void 카드팩은_Rank와_Shape의_모든_조합의_카드를_가지고_있다() {
        CardPack pack = new CardPack();

        var cards = pack.getCards();

        for (Rank rank : Rank.values()) {
            for (Shape shape : Shape.values()) {
                Card card = new Card(rank, shape);
                assertThat(cards).contains(card);
            }
        }
    }
}
