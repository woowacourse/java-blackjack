package blackjack.domain.participant;

import blackjack.domain.card.Pattern;
import blackjack.domain.card.Card;
import blackjack.domain.card.StandardCard;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private static final Player player = new Player("무민");

    @Test
    void createInstance() {
        Assertions.assertThat(player).isInstanceOf(Player.class);
    }

    @Test
    void testHit() {
        Card card = new StandardCard(Pattern.CLUB, "4");
        Assertions.assertThat(player.getCards().getCardCount()).isEqualTo(0);

        player.hit(card);

        Assertions.assertThat(player.getCards().getCardCount()).isEqualTo(1);
        Assertions.assertThat(player.getCards().getCards().contains(card)).isTrue();
    }
}
