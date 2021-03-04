package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserDeckTest {

    @Test
    @DisplayName("카드 점수 합산 테스트")
    void sumOfCard() {
        Card one = new Card(CardNumber.from("8"), CardSymbol.from("클로버"));
        Card two = new Card(CardNumber.from("5"), CardSymbol.from("하트"));
        UserDeck userDeck = new UserDeck();
        userDeck.add(one);
        userDeck.add(two);
        assertThat(userDeck.deckScore()).isEqualTo(13);
    }

    @Test
    @DisplayName("에이스 점수 테스트")
    void hasA() {
        Card one = new Card(CardNumber.from("A"), CardSymbol.from("클로버"));
        Card two = new Card(CardNumber.from("5"), CardSymbol.from("하트"));
        UserDeck userDeck = new UserDeck();
        userDeck.add(one);
        userDeck.add(two);
        assertThat(userDeck.deckScore()).isEqualTo(16);
    }

    @Test
    @DisplayName("에이스 점수 변환 테스트")
    void makeAtoOne() {
        Card one = new Card(CardNumber.from("A"), CardSymbol.from("클로버"));
        Card two = new Card(CardNumber.from("5"), CardSymbol.from("하트"));
        Card three = new Card(CardNumber.from("A"), CardSymbol.from("스페이드"));
        UserDeck userDeck = new UserDeck();
        userDeck.add(one);
        userDeck.add(two);
        userDeck.add(three);
        assertThat(userDeck.deckScore()).isEqualTo(17);
    }

}
