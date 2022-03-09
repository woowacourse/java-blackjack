package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckGenerator;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Clover;
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
        final CardDeck cardDeck = CardDeckGenerator.createCardDeckByCardNumber();
        final Player user = new Player("pobi");
        user.pickCard(cardDeck.drawCard());

        assertThat(user.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어는 보유하고 있는 카드의 총합을 구한다.")
    void calculateScore() {
        final Player player = new Player("pobi");
        player.pickCard(new Clover(CardNumber.KING));
        player.pickCard(new Clover(CardNumber.JACK));
        final int expected = 20;

        final int actual = player.calculateScore();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어의 카드 총합이 21이하이면, true를 반환한다.")
    void isPossibleToPickCard() {
        final Player player = new Player("pobi");
        player.pickCard(new Clover(CardNumber.KING));
        player.pickCard(new Clover(CardNumber.JACK));
        final boolean expected = true;

        final boolean actual = player.isPossibleToPickCard();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어의 카드 총합이 21을 초과하면, false를 반환한다.")
    void isImpossibleToPickCard() {
        final Player player = new Player("pobi");
        player.pickCard(new Clover(CardNumber.KING));
        player.pickCard(new Clover(CardNumber.JACK));
        player.pickCard(new Clover(CardNumber.TWO));
        final boolean expected = false;

        final boolean actual = player.isPossibleToPickCard();

        assertThat(actual).isEqualTo(expected);
    }
}
