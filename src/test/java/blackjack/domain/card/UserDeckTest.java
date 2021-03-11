package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserDeckTest {

    @Test
    @DisplayName("카드 점수 합산 테스트")
    void sumOfCard() {
        Card one = Card.from("8", "클로버");
        Card two = Card.from("5", "하트");
        UserDeck userDeck = generateActiveUserDeck(one, two);

        int cardScore = userDeck.score();

        assertThat(cardScore).isEqualTo(13);
    }

    @Test
    @DisplayName("에이스 점수 테스트")
    void hasA() {
        Card one = Card.from("A", "클로버");
        Card two = Card.from("5", "하트");
        UserDeck userDeck = generateActiveUserDeck(one, two);

        int cardScore = userDeck.score();

        assertThat(cardScore).isEqualTo(16);
    }

    @Test
    @DisplayName("에이스 점수 변환 테스트")
    void makeAtoOne() {
        Card one = Card.from("A", "클로버");
        Card two = Card.from("5", "하트");
        Card three = Card.from("A", "스페이드");
        UserDeck userDeck = generateActiveUserDeck(one, two);
        userDeck.add(three);

        int cardScore = userDeck.score();

        assertThat(cardScore).isEqualTo(17);
    }

    public static UserDeck generateActiveUserDeck(Card one, Card two) {
        UserDeck userDeck = new UserDeck();
        userDeck.add(one);
        userDeck.add(two);
        return userDeck;
    }

}
