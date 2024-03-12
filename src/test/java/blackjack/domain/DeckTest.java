package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("덱에서 카드를 한 장 뽑는다")
    @Test
    public void draw() {
        Deck deck = new Deck(List.of(CardFixture.diamond3()), Collections::shuffle);

        assertThat(deck.draw()).isEqualTo(new Card(CardSuit.DIAMOND, CardNumber.THREE));
    }

    @DisplayName("덱에 카드가 존재하지 않을 때 카드를 뽑으면 에러가 발생한다")
    @Test
    public void deckEmptyThrowException() {
        Deck deck = new Deck(List.of(), Collections::shuffle);

        assertThatCode(deck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("덱에 카드가 존재하지 않습니다.");
    }

    @DisplayName("덱에서 카드를 shuffle 해도 가지는 원소는 같다")
    @Test
    public void testShuffle() {
        List<Card> cards = new ArrayList<>(List.of(CardFixture.heartJack(), CardFixture.diamond3()));
        Deck deck = new Deck(cards, Collections::shuffle);

        deck.shuffle();

        Assertions.assertAll(
                () -> assertThat(deck.draw()).isIn(cards),
                () -> assertThat(deck.draw()).isIn(cards),
                () -> assertThatThrownBy(deck::draw)
                        .isInstanceOf(IllegalStateException.class)
                        .hasMessage("덱에 카드가 존재하지 않습니다.")
        );
    }
}

