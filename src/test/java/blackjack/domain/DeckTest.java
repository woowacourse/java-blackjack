package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    @DisplayName("카드를 포함하는지 확인")
    void containsCard() {
        //given
        deck.addCard(new Card(Number.FIVE, Pattern.CLUB));

        //expect
        assertThat(deck.containsCard(new Card(Number.FIVE, Pattern.CLUB))).isTrue();
    }

    @Test
    @DisplayName("뽑은 카드 추가시 예외 발생")
    void addCard() {
        // given
        deck.addCard(new Card(Number.FIVE, Pattern.CLUB));

        // expect
        assertThatIllegalArgumentException().isThrownBy(
                () -> deck.addCard(new Card(Number.FIVE, Pattern.CLUB))
        ).withMessage("[ERROR] 이미 뽑힌 카드입니다.");
    }
}
