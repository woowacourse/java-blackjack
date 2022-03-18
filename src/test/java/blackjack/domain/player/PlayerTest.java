package blackjack.domain.player;

import static blackjack.domain.Fixtures.ACE_DIAMOND;
import static blackjack.domain.Fixtures.JACK_DIAMOND;
import static blackjack.domain.Fixtures.KING_DIAMOND;
import static blackjack.domain.Fixtures.SEVEN_DIAMOND;
import static blackjack.domain.Fixtures.SIX_DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Fixtures;
import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("유저의 정상 생성을 확인한다.")
    void createUser() {
        final User user = new User("pobi", initializeCardsForUser());
        final String expected = "pobi";

        final String actual = user.getName();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("유저의 카드 총합이 21이하이면, true를 반환한다.")
    void canDrawCarddForUser() {
        final User user = new User("pobi", initializeCardsForUser());
        final boolean expected = true;

        final boolean actual = user.canDrawCard();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("유저의 카드 총합이 21을 초과하면, false를 반환한다.")
    void cannotDrawCardForUser() {
        final User user = new User("pobi", initializeCardsForUser());
        user.drawCard(Fixtures.TWO_DIAMOND);
        final boolean expected = false;

        final boolean actual = user.canDrawCard();
        assertThat(actual).isEqualTo(expected);
    }

    private List<Card> initializeCardsForUser() {
        return new ArrayList<>(Arrays.asList(JACK_DIAMOND, KING_DIAMOND));
    }

    @Test
    @DisplayName("딜러의 카드 총합이 16이하이면, true를 반환한다.")
    void canDrawCardForDealer() {
        List<Card> cards = Arrays.asList(JACK_DIAMOND, SIX_DIAMOND);
        final Dealer dealer = new Dealer(cards);
        final boolean expected = true;

        final boolean actual = dealer.canDrawCard();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 카드 총합이 16을 초과하면, false를 반환한다.")
    void cannotDrawCardForDealer() {
        List<Card> cards = Arrays.asList(JACK_DIAMOND, SEVEN_DIAMOND);
        final Dealer dealer = new Dealer(cards);
        final boolean expected = false;

        final boolean actual = dealer.canDrawCard();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("유저가 처음 받은 카드가 2장이 아니라면, 예외를 발생한다.")
    void checkInitialCardSizeForUser() {
        assertThatThrownBy(() ->
                new User("pobi", Arrays.asList(JACK_DIAMOND, KING_DIAMOND, ACE_DIAMOND)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("처음 제공받는 카드는 2장이어야 합니다.");
    }

    @Test
    @DisplayName("딜러가 처음 받은 카드가 2장이 아니라면, 예외를 발생한다.")
    void checkInitialCardsSizeForDealer() {
        assertThatThrownBy(() ->
                new Dealer(Arrays.asList(JACK_DIAMOND, KING_DIAMOND, ACE_DIAMOND)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("처음 제공받는 카드는 2장이어야 합니다.");
    }
}
