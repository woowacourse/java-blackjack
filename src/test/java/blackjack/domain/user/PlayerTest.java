package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.UserDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private final Card one = new Card(CardNumber.J, CardSymbol.CLOVER);
    private final Card two = new Card(CardNumber.FIVE, CardSymbol.HEART);
    private final UserDeck userDeck = new UserDeck();

    {
        userDeck.draw(one);
        userDeck.draw(two);
    }

    private final Money money = new Money(100);

    @Test
    @DisplayName("플레이어 점수 테스트")
    void getPlayerPoint() {
        String name = "Sorong";
        Player player = new Player(userDeck, name, money);
        int playerScore = 15;
        assertThat(player.score()).isEqualTo(playerScore);
    }

    @Test
    @DisplayName("플레이어 드로우 성공 테스트")
    void getAvailableDraw() {
        String name = "Sorong";
        Player player = new Player(userDeck, name, money);
        assertThat(player.isFinished()).isTrue();
    }

    @Test
    @DisplayName("플레이어 드로우 실패 테스트")
    void getUnavailableDraw() {
        String name = "Sorong";
        Card card3 = new Card(CardNumber.J, CardSymbol.DIAMOND);
        userDeck.draw(card3);
        Player player = new Player(userDeck, name, money);
        assertThat(!player.isFinished()).isTrue();
    }
}
