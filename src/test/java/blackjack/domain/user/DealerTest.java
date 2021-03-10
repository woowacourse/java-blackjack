package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.UserDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    private final Card one = new Card("J", "클로버");
    private final Card two = new Card("5", "하트");
    private final UserDeck userDeck = new UserDeck();
    {
        userDeck.add(one);
        userDeck.add(two);
    }

    @Test
    @DisplayName("딜러 점수 테스트")
    void getPlayerPoint() {
        Dealer dealer = new Dealer(userDeck);
        int playerScore = 15;
        assertThat(dealer.getScore()).isEqualTo(playerScore);
    }

    @Test
    @DisplayName("딜러 드로우 성공 테스트")
    void getAvailableDraw() {
        Dealer dealer = new Dealer(userDeck);
        assertThat(dealer.isAvailableDraw()).isTrue();
    }

    @Test
    @DisplayName("딜러 드로우 실패 테스트")
    void getUnavailableDraw() {
        Card card3 = new Card("2", "다이아몬드");
        userDeck.add(card3);
        Dealer dealer = new Dealer(userDeck);
        System.out.println(dealer.getScore());
        assertThat(dealer.isAvailableDraw()).isFalse();
    }
}
