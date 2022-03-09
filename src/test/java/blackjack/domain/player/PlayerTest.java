package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("플레이어 정상 생성을 확인한다.")
    void createUser() {
        final Player user = new Player("pobi");
        final String expected = "pobi";

        final String actual = user.getName();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어는 카드덱에서 카드를 뽑는다.")
    void pickCard() {
        CardDeck cardDeck = CardDeckGenerator.createCardDeckByCardNumber();
        Player user = new Player("pobi");
        user.pickCard(cardDeck.drawCard());

        assertThat(user.getCards().size()).isEqualTo(1);
    }
}
