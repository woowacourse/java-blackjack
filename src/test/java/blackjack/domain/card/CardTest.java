package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드 인스턴스 생성 확인")
    public void createCard() {
        Suit suit = Suit.SPADE;
        Rank rank = Rank.FOUR;
        Card card = new Card(suit, rank);
        assertThat(card).isInstanceOf(Card.class);
    }


}
