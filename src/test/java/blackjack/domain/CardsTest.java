package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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

        assertThat(card.getRank()).isEqualTo(Symbols.ACE);
    }
}
