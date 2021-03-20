package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserDeckTest {

    @Test
    @DisplayName("카드 점수 합산 테스트")
    void sumOfCard() {
        Card one = new Card(CardNumber.EIGHT, CardSymbol.HEART);
        Card two = new Card(CardNumber.FIVE, CardSymbol.HEART);
        UserDeck userDeck = new UserDeck();
        userDeck.draw(one);
        userDeck.draw(two);
        assertThat(userDeck.score()).isEqualTo(13);
    }

    @Test
    @DisplayName("에이스 점수 테스트")
    void hasA() {
        Card one = new Card(CardNumber.A, CardSymbol.CLOVER);
        Card two = new Card(CardNumber.FIVE, CardSymbol.HEART);
        UserDeck userDeck = new UserDeck();
        userDeck.draw(one);
        userDeck.draw(two);
        assertThat(userDeck.score()).isEqualTo(16);
    }

    @Test
    @DisplayName("에이스 점수 변환 테스트")
    void makeAtoOne() {
        Card one = new Card(CardNumber.A, CardSymbol.CLOVER);
        Card two = new Card(CardNumber.FIVE, CardSymbol.HEART);
        Card three = new Card(CardNumber.A, CardSymbol.SPADE);
        UserDeck userDeck = new UserDeck();
        userDeck.draw(one);
        userDeck.draw(two);
        userDeck.draw(three);
        assertThat(userDeck.score()).isEqualTo(17);
    }

}
