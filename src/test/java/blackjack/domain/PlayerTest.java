package blackjack.domain;

import static blackjack.domain.card.Shape.DIAMOND;
import static blackjack.domain.card.Value.FOUR;
import static blackjack.domain.card.Value.THREE;
import static blackjack.domain.card.Value.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    @DisplayName("덱으로 부터 카드 한장을 받아올 수 있다.")
    void drawCardTest() {
        List<Card> cards = List.of(new Card(DIAMOND, TWO), new Card(DIAMOND, THREE), new Card(DIAMOND, FOUR));
        Deck deck = Deck.from(cards);

        Player player = new Player();
        player.draw(deck);

        List<Card> playerCards = player.getCards();
        assertThat(playerCards).hasSize(1);
    }

}