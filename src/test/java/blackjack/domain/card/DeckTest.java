package blackjack.domain.card;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

    @Order(1)
    @DisplayName("Deck의 초기 Card 갯수는 52개이다")
    @Test
    void testInitialSize() {
        //then
        assertThat(deck.size()).isEqualTo(52);
    }

    @Order(2)
    @DisplayName("Deck에서 카드를 뽑는다")
    @Test
    void testDraw() {
        //when
        deck.draw();

        //then
        assertThat(deck.size()).isEqualTo(51);
    }

    @Order(3)
    @DisplayName("초기 패를 두장 뽑는다")
    @Test
    void testHandOutInitCards() {
        //when
        List<Card> cards = deck.handOutInitCards();

        //then
        assertThat(cards).hasSize(2);
    }
}
