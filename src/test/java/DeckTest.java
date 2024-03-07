import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.Deck;
import domain.constants.Score;
import domain.constants.Shape;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    @DisplayName("블랙잭에 필요한 카드들을 생성해둔다.")
    @Test
    void createCards() {
        Deck deck = new Deck();

        assertThat(deck.getTotalSize()).isEqualTo(52);
    }

    @DisplayName("하나의 카드를 뽑는다.")
    @Test
    void pickCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Score.ACE, Shape.CLOVER));
        cards.add(new Card(Score.FIVE, Shape.DIAMOND));
        cards.add(new Card(Score.EIGHT, Shape.HEART));

        Deck createdDeck = new Deck(cards);
        Card picked = createdDeck.pick();
        Assertions.assertThat(picked).isEqualTo(new Card(Score.ACE, Shape.CLOVER));
    }
}
