package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @DisplayName("Deck 객체를 생성한다")
    @Test
    void testGenerate() {
        //given //when
        Deck deck = Deck.generate();

        //then
        assertThat(deck).isNotNull();
    }

    @DisplayName("Deck의 초기 Card 갯수는 52개이다")
    @Test
    void testInitialSize() {
        //given //when
        Deck deck = Deck.generate();

        //then
        assertThat(deck.size()).isEqualTo(52);
    }

    @DisplayName("Deck에서 카드를 뽑는다")
    @Test
    void testDraw() {
        //given
        Deck deck = Deck.generate();

        //when
        deck.draw();

        //then
        assertThat(deck.size()).isEqualTo(51);
    }
}
