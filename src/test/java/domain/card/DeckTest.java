package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import infra.FakeCardShuffler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("트럼프 카드 덱을 초기화한다.")
    public void 트럼프_덱_초기화_성공() {

        // when
        Deck deck = Deck.initCardDeck(new FakeCardShuffler());

        // then
        List<Card> cards = deck.getDeck();
        assertThat(cards).hasSize(CardSuit.values().length * CardRank.values().length);

        for (CardSuit cardSuit : CardSuit.values()) {
            for (CardRank cardRank : CardRank.values()) {
                assertThat(cards).contains(new Card(cardSuit, cardRank));
            }
        }
    }

    @Test
    @DisplayName("카드를 뽑으면 덱에서 카드 한장이 줄어든다.")
    public void 카드_뽑으면_한장_줄어듦_성공() throws Exception {
        // given
        Deck deck = Deck.initCardDeck(new FakeCardShuffler());
        int originDeckSize = deck.getDeckSize();

        // when
        Card card = deck.draw();

        // then
        assertThat(card).isNotNull();
        assertThat(deck.getDeckSize()).isEqualTo(originDeckSize - 1);
    }

}
