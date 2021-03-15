package blackjack.domain.card;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = Deck.generate();
    }

    @DisplayName("Deck 객체를 생성한다")
    @Test
    void testGenerate() {
        //then
        assertThat(deck).isNotNull();
    }

    @DisplayName("Deck의 초기 Card 갯수는 52개이다")
    @Test
    void testInitialSize() {
        //when
        Deck.generate();
        //then
        assertThat(deck.size()).isEqualTo(52);
    }

    @DisplayName("Deck에서 카드를 뽑는다")
    @Test
    void testDraw() {
        //given
        int currentDeckSize = deck.size();
        //when
        deck.draw();

        //then
        assertThat(deck.size()).isEqualTo(currentDeckSize - 1);
    }

    @DisplayName("초기 패를 두장 뽑는다")
    @Test
    void testHandOutInitCards() {
        //when
        List<Card> cards = deck.handOutInitCards();

        //then
        assertThat(cards).hasSize(2);
    }
}
