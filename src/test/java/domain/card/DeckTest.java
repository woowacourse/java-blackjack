package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static domain.card.CardFixture.cardOf;
import static domain.card.Rank.ACE;
import static domain.card.Rank.TWO;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("카드덱에서 카드를 한 장 뽑을 수 있다.")
    @Test
    void draw() {
        Card ace = cardOf(ACE);
        Deck deck = new Deck(List.of(ace, cardOf(TWO)));
        assertThat(deck.draw()).isEqualTo(ace);
    }

    @DisplayName("카드가 없는 경우 예외를 던진다.")
    @Test
    void empty() {
        Deck deck = new Deck(List.of());
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드가 없습니다. 카드를 추가하세요.");
    }
}
