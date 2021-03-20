package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.UserDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    private final Card one = new Card(CardNumber.J, CardSymbol.CLOVER);
    private final Card two = new Card(CardNumber.FIVE, CardSymbol.HEART);
    private final UserDeck userDeck = new UserDeck();

    {
        userDeck.draw(one);
        userDeck.draw(two);
    }

    @Test
    @DisplayName("딜러 점수 테스트")
    void getPlayerPoint() {
        Dealer dealer = new Dealer(userDeck);
        int playerScore = 15;
        assertThat(dealer.score()).isEqualTo(playerScore);
    }

    @Test
    @DisplayName("딜러 드로우 성공 테스트")
    void getAvailableDraw() {
        Dealer dealer = new Dealer(userDeck);
        assertThat(dealer.isFinished()).isTrue();
    }

    @Test
    @DisplayName("딜러 드로우 실패 테스트")
    void getUnavailableDraw() {
        Card card3 = new Card(CardNumber.TWO, CardSymbol.DIAMOND);
        userDeck.draw(card3);
        Dealer dealer = new Dealer(userDeck);
        assertThat(dealer.isFinished()).isFalse();
    }
}
