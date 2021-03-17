package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.UserDeck;
import blackjack.domain.card.UserDeckTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerHitTest {

    private final Card basicOne = Card.of("8", "클로버");
    private final Card basicTwo = Card.of("5", "하트");

    @Test
    @DisplayName("카드 뽑기 시에 다음 Hit 반환")
    void returnHit() {
        UserDeck userDeck = UserDeckTest.generateActiveUserDeck(basicOne, basicTwo);
        PlayerHit playerHitState = new PlayerHit(userDeck);
        Card smallValueCard = Card.of("2", "다이아몬드");

        State nextState = playerHitState.draw(smallValueCard);
        boolean isHit = nextState instanceof PlayerHit;

        assertThat(isHit).isTrue();
    }

    @Test
    @DisplayName("카드 뽑기 시에 버스트")
    void returnBust() {
        UserDeck userDeck = UserDeckTest.generateActiveUserDeck(basicOne, basicTwo);
        PlayerHit playerHitState = new PlayerHit(userDeck);
        Card bigValueCard = Card.of("J", "다이아몬드");

        State nextState = playerHitState.draw(bigValueCard);
        boolean isBust = nextState instanceof Bust;

        assertThat(isBust).isTrue();
    }

    @Test
    @DisplayName("카드 뽑고 난 후 유지")
    void returnStay() {
        UserDeck userDeck = UserDeckTest.generateActiveUserDeck(basicOne, basicTwo);
        PlayerHit playerHitState = new PlayerHit(userDeck);

        State nextState = playerHitState.stay();
        boolean isStay = nextState instanceof Stay;

        assertThat(isStay).isTrue();
    }
}
