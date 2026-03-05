package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import constant.Rank;
import constant.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    Deck deck;

    @BeforeEach
    void beforeEach() {
        deck = new Deck();
    }

    @DisplayName("덱 생성 성공")
    @Test
    void 덱에_52장의_카드_생성() {
        deck.init();
        assertThat(deck.cards.size()).isEqualTo(52);
    }

    @DisplayName("덱이 잘 섞였는지 확인")
    @Test
    void 섞인_덱에서_첫번째_카드_추출() {
        Card testCard = new Card(Rank.ACE, Suit.HEART);

        deck.init();
        deck.shuffle();
        Card randomCard = deck.draw();

        assertThat(testCard).isNotEqualTo(randomCard);
    }
}
