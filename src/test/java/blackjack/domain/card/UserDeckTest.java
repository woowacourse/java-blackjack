package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserDeckTest {

    @Test
    @DisplayName("카드 점수 합산 테스트")
    void sumOfCard() {
        Card one = new Card("8", "클로버");
        Card two = new Card("5", "하트");
        UserDeck userDeck = new UserDeck();
        userDeck.draw(one);
        userDeck.draw(two);
        assertThat(userDeck.score()).isEqualTo(13);
    }

    @Test
    @DisplayName("에이스 점수 테스트")
    void hasA() {
        Card one = new Card("A", "클로버");
        Card two = new Card("5", "하트");
        UserDeck userDeck = new UserDeck();
        userDeck.draw(one);
        userDeck.draw(two);
        assertThat(userDeck.score()).isEqualTo(16);
    }

    @Test
    @DisplayName("에이스 점수 변환 테스트")
    void makeAtoOne() {
        Card one = new Card("A", "클로버");
        Card two = new Card("5", "하트");
        Card three = new Card("A", "스페이드");
        UserDeck userDeck = new UserDeck();
        userDeck.draw(one);
        userDeck.draw(two);
        userDeck.draw(three);
        assertThat(userDeck.score()).isEqualTo(17);
    }

}
