package domain.card;

import domain.CardShuffler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeckTest {

    private CardShuffler mockShuffler;

    @BeforeAll
    void init() {
        mockShuffler = targetCard -> targetCard;
    }

    @Test
    @DisplayName("create()는 호출하면 섞인 52장의 카드 뭉치를 생성한다.")
    void create_whenCall_thenSuccess() {
        final Deck deck = assertDoesNotThrow(() -> Deck.create(mockShuffler));

        assertThat(deck)
                .isExactlyInstanceOf(Deck.class);
    }

    @Test
    @DisplayName("draw()는 호출하면 카드 한 장을 뽑는다.")
    void draw_whenCall_thenReturnCard() {
        // given
        final Deck deck = Deck.create(mockShuffler);
        final Card expected = Card.create(CardPattern.HEART, CardNumber.ACE);

        // when
        final Card actual = deck.draw();

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }
}
