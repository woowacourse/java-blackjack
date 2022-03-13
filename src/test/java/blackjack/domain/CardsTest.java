package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
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
        Card compareCard = new Card(Suit.SPADE, Rank.ACE);
        assertThat(card).isEqualTo(compareCard);
    }
}
