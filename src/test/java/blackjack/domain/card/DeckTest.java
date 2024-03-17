package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.fixture.CardFixture;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("덱에서 카드를 뒤에서 부터 한 장 뽑는다")
    @Test
    void draw() {
        Deck deck = new Deck(List.of(CardFixture.diamond3(), CardFixture.heartJack()));

        assertThat(deck.draw()).isEqualTo(new Card(CardSuit.HEART, CardNumber.JACK));
    }

    @DisplayName("덱에 카드가 존재하지 않을 때 카드를 뽑으면 에러가 발생한다")
    @Test
    void deckEmptyThrowException() {
        Deck deck = new Deck(List.of());

        assertThatCode(deck::draw)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("덱에 카드가 존재하지 않습니다.");
    }
}

