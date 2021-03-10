package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    private List<Card> deck;
    private Cards cards;

    @BeforeEach
    void init() {
        deck = new ArrayList<>(Arrays.asList(
            new Card(Denomination.THREE, Suit.SPADE),
            new Card(Denomination.SEVEN, Suit.DIAMOND)
        ));
        cards = new Cards();
        cards.addCards(deck);
    }

    @Test
    @DisplayName("비어 있는 카드 리스트 생성한다.")
    void createEmptyCards() {
        Cards cards = new Cards();
        assertThat(cards.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("카드가 들어 있는 리스트를 생성한다.")
    void createCards() {
        Cards actual = new Cards();
        actual.addCards(deck);
        assertThat(cards).isEqualTo(actual);
    }

    @Test
    @DisplayName("리스트에 든 카드(들) 보여준다.")
    void showOne() {
        List<Card> deck = new ArrayList<>(
            Collections.singletonList(new Card(Denomination.THREE, Suit.SPADE)));
        Cards cards = new Cards();
        cards.addCards(deck);
        assertThat(cards.loadCards()).isEqualTo("3스페이드");

        cards.addCard(new Card(Denomination.SEVEN, Suit.DIAMOND));
        assertThat(cards.loadCards()).isEqualTo("3스페이드, 7다이아몬드");
    }

    @DisplayName("카드들의 점수 총합을 계산한다.")
    @Test
    void calculate() {
        assertThat(cards.calculateTotalScore()).isEqualTo(new Score(10));
    }
}
