package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Clover;
import blackjack.domain.card.Diamond;
import blackjack.domain.card.Heart;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private List<Card> initializeCardsForUser() {
        return new ArrayList<>(Arrays.asList(new Diamond(CardNumber.JACK), new Heart(CardNumber.KING)));
    }

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
        user.pickCard(new Clover(CardNumber.TWO));
        final boolean expected = false;

        final boolean actual = user.isPossibleToPickCard();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 카드 총합이 16이하이면, true를 반환한다.")
    void isPossibleToPickCard() {
        List<Card> cards = Arrays.asList(new Diamond(CardNumber.JACK), new Heart(CardNumber.SIX));
        final Player dealer = new Dealer(cards);
        final boolean expected = true;

        final boolean actual = dealer.isPossibleToPickCard();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 카드 총합이 16을 초과하면, false를 반환한다.")
    void isImpossibleToPickCard() {
        List<Card> cards = Arrays.asList(new Diamond(CardNumber.JACK), new Heart(CardNumber.KING));
        final Player dealer = new Dealer(cards);
        final boolean expected = false;

        final boolean actual = dealer.isPossibleToPickCard();

        assertThat(actual).isEqualTo(expected);
    }
}
