package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import dto.Card;
import exception.GameException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BlackJackDeckTest {

    @Test
    public void 카드_드로우_정상_작동() {
        Card first = new Card(Shape.HEART, CardNumber.ACE);
        Card second = new Card(Shape.SPADE, CardNumber.KING);
        BlackJackDeck deck = new BlackJackDeck(List.of(first, second));

        assertThat(deck.draw()).isEqualTo(first);
        assertThat(deck.draw()).isEqualTo(second);
    }

    @Test
    public void 남은_카드가_0장_일_때_드로우() {
        BlackJackDeck deck = new BlackJackDeck(List.of());

        assertThatThrownBy(deck::draw)
                .isExactlyInstanceOf(GameException.class)
                .hasMessage("카드 뭉치에 더 이상 남아있는 카드가 없습니다.");
    }
}
