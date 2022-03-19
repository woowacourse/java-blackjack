package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.TestCardGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class UserTest {

    @DisplayName("올바른 유저의 이름을 테스트한다.")
    @ParameterizedTest(name = "{index} {displayName} name = {0}")
    @ValueSource(strings = {"jason", "pobi"})
    void validNameTest(final String name) {
        User user = new User(name);
        assertThat(user.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("유저의 이름이 공백인 경우 예외를 발생시킨다.")
    void invalidNameTest() {
        assertThatThrownBy(() -> new User(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유저의 이름은 한 글자 이상이어야 합니다.");
    }

    @Test
    @DisplayName("유저는 보유한 카드의 합이 21이 넘으면 패배한다.")
    void userLoseExceedTwentyOneTest() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.EIGHT, CardType.CLOVER),
                        new Card(CardNumber.SEVEN, CardType.CLOVER),
                        new Card(CardNumber.QUEEN, CardType.CLOVER)));
        Deck deck = new Deck(cardGenerator);

        User user = new User("Pobi");
        user.betting(10000);
        user.drawCard(deck);
        user.drawCard(deck);
        user.drawCard(deck);

        assertThat(user.calculateProfit(19, false))
                .isEqualTo(-10000);
    }

    @Test
    @DisplayName("유저는 보유한 카드의 합이 딜러보다 작으면 패배한다.")
    void userLoseTest() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.EIGHT, CardType.CLOVER),
                        new Card(CardNumber.QUEEN, CardType.CLOVER)));
        Deck deck = new Deck(cardGenerator);

        User user = new User("Pobi");
        user.betting(10000);
        user.drawCard(deck);
        user.drawCard(deck);
        user.changeToStand();

        assertThat(user.calculateProfit(20, false))
                .isEqualTo(-10000);
    }

    @Test
    @DisplayName("유저는 보유한 카드의 합이 딜러의 카드 합보다 크면 승리한다.")
    void userWinTest() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.EIGHT, CardType.CLOVER),
                        new Card(CardNumber.QUEEN, CardType.CLOVER)));
        Deck deck = new Deck(cardGenerator);

        User user = new User("Pobi");
        user.betting(10000);
        user.drawCard(deck);
        user.drawCard(deck);
        user.changeToStand();

        assertThat(user.calculateProfit(17, false))
                .isEqualTo(10000);
    }

    @Test
    @DisplayName("유저는 보유한 카드의 합이 딜러의 카드의 합과 같으면 무승부이다.")
    void userDrawTest() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.EIGHT, CardType.CLOVER),
                        new Card(CardNumber.QUEEN, CardType.CLOVER)));
        Deck deck = new Deck(cardGenerator);

        User user = new User("Pobi");
        user.betting(10000);
        user.drawCard(deck);
        user.drawCard(deck);
        user.changeToStand();

        assertThat(user.calculateProfit(18, false))
                .isEqualTo(0);
    }

    @Test
    @DisplayName("Ace는 1 또는 11로 계산될 수 있다.")
    void userDrawTest2() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.ACE, CardType.CLOVER),
                        new Card(CardNumber.ACE, CardType.HEART),
                        new Card(CardNumber.ACE, CardType.DIAMOND)));
        Deck deck = new Deck(cardGenerator);

        User user = new User("Pobi");
        user.betting(10000);
        user.drawCard(deck);
        user.drawCard(deck);
        user.drawCard(deck);
        user.changeToStand();

        assertThat(user.calculateProfit(13, false))
                .isEqualTo(0);
    }

    @Test
    @DisplayName("유저가 버스트인 경우를 체크한다.")
    void burstTest() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.QUEEN, CardType.CLOVER),
                        new Card(CardNumber.QUEEN, CardType.HEART),
                        new Card(CardNumber.QUEEN, CardType.DIAMOND)));
        Deck deck = new Deck(cardGenerator);

        User user = new User("Pobi");
        user.drawCard(deck);
        user.drawCard(deck);
        user.drawCard(deck);

        assertThat(user.isBust()).isTrue();
    }

    @Test
    @DisplayName("유저가 버스트가 아닌 경우를 체크한다.")
    void notBurstTest() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.ACE, CardType.CLOVER),
                        new Card(CardNumber.ACE, CardType.HEART),
                        new Card(CardNumber.ACE, CardType.DIAMOND)));
        Deck deck = new Deck(cardGenerator);

        User user = new User("Pobi");
        user.drawCard(deck);
        user.drawCard(deck);
        user.drawCard(deck);

        assertThat(user.isBust()).isFalse();
    }
}
