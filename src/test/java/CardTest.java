import domain.card.Card;
import domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    @DisplayName("덱을 셔플하면 카드의 순서가 바뀐다.")
    void shuffled_card_must_different() {
        List<Card> originalOrder = new ArrayList<>(Arrays.asList(Card.values()));
        CardDeck deck = new CardDeck(Collections::reverse);
        List<Card> shuffledOrder = new ArrayList<>(deck.getCards());

        assertThat(shuffledOrder).containsExactlyInAnyOrderElementsOf(originalOrder);
        assertThat(shuffledOrder).isNotEqualTo(originalOrder);
        assertThat(shuffledOrder.getFirst()).isEqualTo(originalOrder.getLast());
    }

    @Test
    @DisplayName("카드를 분배하면 덱에 있는 카드 수가 하나 줄어든다")
    public void deal_card_decrease_deck_card_1(){
        CardDeck deck = new CardDeck(cards -> {});
        List<Card> cards = new ArrayList<>(deck.getCards());
        deck.deal();
        assertThat(deck.getCards().size()).isEqualTo(cards.size() - 1);
    }

    @Test
    @DisplayName("카드를 분배하면 덱의 맨 앞 카드가 나온다.")
    public void deal_card_match_first(){
        CardDeck deck = new CardDeck(cards -> {});
        assertThat(deck.getCards().getFirst()).isEqualTo(deck.deal());
    }
}
