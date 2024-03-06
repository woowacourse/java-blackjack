package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("덱에서 카드를 한 장 뽑는다")
    @Test
    public void draw() {
        Deck deck = new Deck(new CardFactory().createBlackJackCard());

        assertThat(deck.draw()).isEqualTo(new Card(CardSuit.SPADE, CardNumber.KING));
    }

    @DisplayName("덱에 카드가 존재하지 않을 때 카드를 뽑으면 에러가 발생한다")
    @Test
    public void deckEmptyThrowException() {
        Deck deck = new Deck(List.of());

        assertThatCode(deck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("덱에 카드가 존재하지 않습니다.");
    }
}

//1. 우리가 원하는대로 섞고 테스트
//2. 섞고 원본과 다른지 테스트
//3. 섞었다 치고 원본과 원소들이 같은지 테스트
//4.
