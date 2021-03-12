package blakcjack.domain.card;

import blakcjack.domain.shufflestrategy.RandomShuffleStrategy;
import blakcjack.domain.shufflestrategy.ShuffleStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {
    @DisplayName("덱 사이즈 체크 성공")
    @Test
    void create_size_check() {
        final Deck deck = new Deck(new RandomShuffleStrategy());

        assertThatCode(() -> consumeAllCards(deck))
                .doesNotThrowAnyException();
        assertThatThrownBy(deck::drawCard).isInstanceOf(EmptyDeckException.class);
    }

    private void consumeAllCards(final Deck deck) {
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }
    }

    @DisplayName("덱 내 중복 체크 성공")
    @Test
    void create_duplication_check() {
        final int cardSize = 52;
        final Deck deck = new Deck(new RandomShuffleStrategy());
        final Set<Card> cards = new HashSet<>();

        for (int i = 0; i < cardSize; i++) {
            try {
                cards.add(deck.drawCard());
            } catch (RuntimeException e) {
                break;
            }
        }

        assertThat(cards.size())
                .isEqualTo(cardSize);
    }

    @DisplayName("카드 뽑기 성공")
    @Test
    void draw() {
        final ShuffleStrategy nonShuffleStrategy = cards -> {
        };
        final Deck deck = new Deck(nonShuffleStrategy);
        final CardSymbol spade = CardSymbol.values()[3];
        final CardNumber k = CardNumber.values()[12];
        final CardNumber q = CardNumber.values()[11];
        final CardNumber j = CardNumber.values()[10];

        assertThat(deck.drawCard()).isEqualTo(Card.of(spade, k));
        assertThat(deck.drawCard()).isEqualTo(Card.of(spade, q));
        assertThat(deck.drawCard()).isEqualTo(Card.of(spade, j));
    }
}