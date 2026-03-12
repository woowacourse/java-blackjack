import domain.Card;
import domain.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    @DisplayName("덱을 셔플하면 카드의 순서가 바뀐다.")
    public void shuffled_card_must_different() {
        CardDeck deck = new CardDeck();
        ArrayList<Card> beforeShuffle = new ArrayList<>(deck.getCards());

        deck.shuffle();
        List<Card> afterShuffle = deck.getCards();

        assertThat(afterShuffle).isNotEqualTo(beforeShuffle);
        assertThat(afterShuffle).containsExactlyInAnyOrderElementsOf(beforeShuffle);
    }

    @Test
    @DisplayName("카드를 분배하면 덱에 있는 카드 수가 하나 줄어든다")
    public void deal_card_decrease_deck_card_1(){
        CardDeck deck = new CardDeck();
        List<Card> cards = new ArrayList<>(deck.getCards());
        deck.draw();
        assertThat(deck.getCards().size()).isEqualTo(cards.size() - 1);
    }

    @Test
    @DisplayName("카드를 분배하면 덱의 맨 앞 카드가 나온다.")
    public void deal_card_match_first(){
        CardDeck deck = new CardDeck();
        assertThat(deck.getCards().getFirst()).isEqualTo(deck.draw());
    }
}
