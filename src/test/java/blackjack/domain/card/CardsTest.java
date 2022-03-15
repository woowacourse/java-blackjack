package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    private Cards cards;
    @BeforeEach
    public void setUp() {
        cards = new Cards(new AlwaysAscNumberMachine());
    }
    @Test
    @DisplayName("Cards 객체 생성 확인")
    public void createCards() {
        assertThat(cards).isInstanceOf(Cards.class);
    }

    @Test
    @DisplayName("Cards 반환 확인")
    public void checkCardReturn() {
        Card card = cards.assignCard();
        Card compareCard = new Card(Suit.SPADE, Rank.ACE);
        assertThat(card).isEqualTo(compareCard);
    }
}
