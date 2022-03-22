package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    private Cards cards;

    @BeforeEach
    public void setUp() {
        cards = new Cards();
    }

    @Test
    @DisplayName("덱 객체 생성 확인")
    public void createDeck() {
        assertThat(cards).isInstanceOf(Cards.class);
    }

    @Test
    @DisplayName("deck cards에 card 더하는 로직 확인")
    public void checkAddCard() {
        cards.addCard(new Card(Suit.SPADE, Rank.EIGHT));
        Cards comparedCards = new Cards();
        comparedCards.addCard(new Card(Suit.SPADE, Rank.EIGHT));
        assertThat(cards).isEqualTo(comparedCards);
    }
}
