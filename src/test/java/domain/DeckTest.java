package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    private static final List<Card> deckCards = List.of(
            new Card(CardNumber.A, CardShape.CLOVER)
    );

    static class FakeCardsGenerator implements CardsGenerator {
        @Override
        public List<Card> generate() {
            return deckCards;
        }
    }

    @DisplayName("카드를 뽑을 수 있다.")
    @Test
    void test() {
        // given
        Deck deck = new Deck(new RandomCardsGenerator());

        // when
        Card card = deck.pick();

        // then
        assertThat(card).isInstanceOf(Card.class);
    }

    @DisplayName("덱은 항상 다른 카드를 반환한다.")
    @Test
    void 덱은_항상_다른_카드_반환() {
        // given
        Deck deck = new Deck(new RandomCardsGenerator());
        List<Card> outputCards = new ArrayList<>();

        // when
        for (int i = 0; i < 52; ++i) {
            outputCards.add(deck.pick());
        }

        // then
        assertThat(outputCards).doesNotHaveDuplicates();
    }

    @DisplayName("덱은 52장의 카드를 갖고 있다.")
    @Test
    void 덱은_52장() {
        // given
        Deck deck = new Deck(new RandomCardsGenerator());
        for (int i = 0; i < 52; ++i) {
            deck.pick();
        }

        // when & then
        assertThatThrownBy(deck::pick).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("플레이어에게 카드를 할당할 수 있다.")
    @Test
    void 플레이어에게_카드_할당() {
        //given
        Deck deck = new Deck(new FakeCardsGenerator());
        Player player = Player.init("플레이어");

        //when
        deck.giveCardTo(player);

        //then
        assertThat(player.getCards()).contains(deckCards.getLast());
    }
}
