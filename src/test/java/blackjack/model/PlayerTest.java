package blackjack.model;

import static blackjack.model.CardCreator.createCard;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 카드를_받으면_자신의_카드에_추가한다() {
        Player player = new Player();

        player.receiveCards(new Cards(
                List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        ));

        assertThat(player.getCards().getValues()).hasSize(3);
    }
}
