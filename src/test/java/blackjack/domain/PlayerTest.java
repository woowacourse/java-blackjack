package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.StandardCard;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void createInstance() {
        Participant p1 = new Player("무민");
        Assertions.assertThat(p1).isInstanceOf(Player.class);
    }

    @Test
    void testHit() {
        Participant p1 = new Player("무민");
        Card card = new StandardCard(Pattern.CLUB, "4");
        Assertions.assertThat(p1.getCardDeck().getCardCount()).isEqualTo(0);

        p1.hit(card);

        Assertions.assertThat(p1.getCardDeck().getCardCount()).isEqualTo(1);
        Assertions.assertThat(p1.getCardDeck().getCards().contains(card)).isTrue();
    }
}