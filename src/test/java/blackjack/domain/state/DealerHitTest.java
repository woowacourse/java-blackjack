package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.UserDeck;
import blackjack.domain.card.UserDeckTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerHitTest {

    private final Card basicOne = new Card(CardNumber.from("8"), CardSymbol.from("클로버"));
    private final Card basicTwo = new Card(CardNumber.from("5"), CardSymbol.from("하트"));

    @Test
    @DisplayName("카드 뽑기 시에 다음 Hit 반환")
    void returnHit() {
        UserDeck userDeck = UserDeckTest.generateActiveUserDeck(basicOne, basicTwo);
        DealerHit dealerHitState = new DealerHit(userDeck);
        Card smallValueCard = new Card(CardNumber.from("2"), CardSymbol.from("다이아몬드"));

        State nextState = dealerHitState.draw(smallValueCard);
        boolean isHit = nextState instanceof DealerHit;

        assertThat(isHit).isTrue();
    }

    @Test
    @DisplayName("카드 뽑기 시에 버스트")
    void returnBust() {
        UserDeck userDeck = UserDeckTest.generateActiveUserDeck(basicOne, basicTwo);
        DealerHit dealerHitState = new DealerHit(userDeck);
        Card bigValueCard = new Card(CardNumber.from("J"), CardSymbol.from("다이아몬드"));

        State nextState = dealerHitState.draw(bigValueCard);
        boolean isBust = nextState instanceof Bust;

        assertThat(isBust).isTrue();
    }

    @Test
    @DisplayName("카드 뽑고 난 후 유지")
    void returnStay() {
        UserDeck userDeck = UserDeckTest.generateActiveUserDeck(basicOne, basicTwo);
        DealerHit dealerHitState = new DealerHit(userDeck);
        Card stayCard = new Card(CardNumber.from("4"), CardSymbol.from("하트"));

        State nextState = dealerHitState.draw(stayCard);
        boolean isStay = nextState instanceof Stay;

        assertThat(isStay).isTrue();
    }
}
