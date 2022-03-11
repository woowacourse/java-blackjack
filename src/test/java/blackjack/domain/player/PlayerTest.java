package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("유저의 정상 생성을 확인한다.")
    void createUser() {
        final Player user = new User("pobi", initializeCardsForUser());
        final String expected = "pobi";

        final String actual = user.getName();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("유저의 카드 총합이 21이하이면, true를 반환한다.")
    void isPossibleToPickCardForUser() {
        final Player user = new User("pobi", initializeCardsForUser());
        final boolean expected = true;

        final boolean actual = user.isPossibleToPickCard();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("유저의 카드 총합이 21을 초과하면, false를 반환한다.")
    void isImpossibleToPickCardForUser() {
        final Player user = new User("pobi", initializeCardsForUser());
        user.pickCard(new Card(CardPattern.CLOVER, CardNumber.TWO));
        final boolean expected = false;

        final boolean actual = user.isPossibleToPickCard();

        assertThat(actual).isEqualTo(expected);
    }

    private List<Card> initializeCardsForUser() {
        return new ArrayList<>(Arrays.asList(new Card(CardPattern.DIAMOND, CardNumber.JACK), new Card(CardPattern.HEART, CardNumber.KING)));
    }

    @Test
    @DisplayName("딜러의 카드 총합이 16이하이면, true를 반환한다.")
    void isPossibleToPickCardForDealer() {
        List<Card> cards = Arrays.asList(new Card(CardPattern.DIAMOND, CardNumber.JACK), new Card(CardPattern.HEART, CardNumber.SIX));
        final Player dealer = new Dealer(cards);
        final boolean expected = true;

        final boolean actual = dealer.isPossibleToPickCard();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 카드 총합이 16을 초과하면, false를 반환한다.")
    void isImpossibleToPickCardForDealer() {
        List<Card> cards = Arrays.asList(new Card(CardPattern.DIAMOND, CardNumber.JACK), new Card(CardPattern.HEART, CardNumber.KING));
        final Player dealer = new Dealer(cards);
        final boolean expected = false;

        final boolean actual = dealer.isPossibleToPickCard();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("유저가 처음 받은 카드가 2장이 아니라면, 예외를 발생한다.")
    void checkFirstReceivedCardsSizeForUser() {
        assertThatThrownBy(() -> new User("pobi", Arrays.asList(
                new Card(CardPattern.DIAMOND, CardNumber.JACK),
                new Card(CardPattern.HEART, CardNumber.KING),
                new Card(CardPattern.HEART, CardNumber.KING))
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("처음 제공받는 카드는 2장이어야 합니다.");
    }

    @Test
    @DisplayName("딜러가 처음 받은 카드가 2장이 아니라면, 예외를 발생한다.")
    void checkFirstReceivedCardsSizeForDealer() {
        assertThatThrownBy(() -> new Dealer(Arrays.asList(
                new Card(CardPattern.DIAMOND, CardNumber.JACK),
                new Card(CardPattern.HEART, CardNumber.KING),
                new Card(CardPattern.HEART, CardNumber.KING))
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("처음 제공받는 카드는 2장이어야 합니다.");
    }

    @Test
    @DisplayName("딜러가 블랙잭인 경우를 확인한다.")
    void isBlackJack() {
        List<Card> cards = Arrays.asList(new Card(CardPattern.DIAMOND, CardNumber.ACE), new Card(CardPattern.HEART, CardNumber.KING));
        Player dealer = new Dealer(cards);
        final boolean expected = true;

        final boolean actual = dealer.isBlackJack();

        assertThat(actual).isEqualTo(expected);
    }
}
