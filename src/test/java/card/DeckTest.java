package card;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    static class BrokenShuffleMachine implements ShuffleMachine {

        @Override
        public void shuffle(List<Card> cards) {

        }
    }

    @DisplayName("덱에서 첫번째 카드를 뽑으면 제일 앞에 있는 카드가 나온다.")
    @Test
    void draw() {
        Deck deck = new Deck(new BrokenShuffleMachine());
        Card drawedCard = deck.draw();
        Assertions.assertThat(drawedCard.getDenomination()).isEqualTo(Denomination.ACE);
        Assertions.assertThat(drawedCard).extracting("suit").isEqualTo(Suit.CLOVER);
    }

    @DisplayName("52 초과 갯수의 카드를 뽑을 경우 오류를 던진다.")
    @Test
    void usedAll() {
        Deck deck = new Deck(new BrokenShuffleMachine());
        Assertions.assertThatThrownBy(() -> {
                for (int i = 0; i < 53; i++) {
                    deck.draw();
                }
            }).isInstanceOf(IllegalStateException.class)
            .hasMessage("남은 카드가 없습니다.");
    }
}
