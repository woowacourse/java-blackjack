package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @DisplayName("덱 생성 테스트")
    @Test
    void create() {
        Deck deck = new Deck();
        assertThat(deck).isNotNull();
    }

    @DisplayName("덱에서 카드를 1장 가져온다.")
    @Test
    void getCard() {
        Deck deck = new Deck();
        assertThat(deck.getCard(0)).isEqualTo(new Card(Type.SPADE, Denomination.ACE));
        assertThat(deck.getCard(51)).isEqualTo(new Card(Type.CLUB, Denomination.KING));
    }
}
