package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {
    @Test
    void 생성된_덱의_카드_개수_52장_확인() {
        Deck deck = Deck.createDeck();
        assertThat(deck.getCards().size()).isEqualTo(52);
    }

    @Test
    void 카드_한장_뽑으면_덱의_크기_1감소() {
        Deck deck = Deck.createDeck();
        int before = deck.getCards().size();
        deck.draw();
        int after = deck.getCards().size();
        assertThat(after).isEqualTo(before - 1);
    }

    @Test
    void 덱에_카드_없을_경우_예외_발생() {
        Deck deck = Deck.from(List.of());
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("덱에 남은 카드가 없습니다.");
    }
}
