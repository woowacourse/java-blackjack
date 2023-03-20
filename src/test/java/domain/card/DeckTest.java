package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {

    private final CardSelector cardSelector = new RandomUniqueCardSelector();

    @Test
    @DisplayName("create()는 호출하면 섞인 52장의 카드 뭉치를 생성한다.")
    void create_givenCardSelector_thenSuccess() {
        final Deck deck = assertDoesNotThrow(() -> Deck.create(cardSelector));

        assertThat(deck)
                .isExactlyInstanceOf(Deck.class);
    }

    @Nested
    @DisplayName("draw() 테스트")
    class DrawMethodTest {

        @Test
        @DisplayName("호출하면 카드 한 장을 뽑는다.")
        void draw_whenCall_thenReturnCard() {
            // given
            final Deck deck = Deck.create(cardSelector);

            // when
            final Card actual = assertDoesNotThrow(deck::draw);

            // then
            assertThat(actual)
                    .isNotNull();
        }

        @Test
        @DisplayName("호출하면 52장의 카드 중 고유한 카드를 뽑는다.")
        void draw_whenCall_thenReturnUniqueCard() {
            // given
            final Set<Card> cards = new HashSet<>();
            final Deck deck = Deck.create(cardSelector);
            int count = 0;

            // when
            while (count++ < 52) {
                Card drawCard = deck.draw();
                cards.add(drawCard);
            }

            // then
            assertThat(cards.size())
                    .isSameAs(52);
        }
    }
}
