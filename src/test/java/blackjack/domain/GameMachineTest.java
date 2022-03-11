package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameMachineTest {

    private CardDeck cardDeck = CardDeckGenerator.createCardDeckByCardNumber();

    @Test
    @DisplayName("유저들을 생성을 확인한다.")
    void createUsers() {
        GameMachine gameMachine = new GameMachine(cardDeck);
        List<String> users = Arrays.asList("pobi", "jun", "kay");
        final int expected = 3;

        final int actual = gameMachine.createUsers(users).size();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 점수가 16이하일 때 딜러가 카드를 받는지 확인한다.")
    void checkDealerReceiveCard() {
        GameMachine gameMachine = new GameMachine(cardDeck);
        Player dealer = new Dealer(new ArrayList<>(Arrays.asList(new Card(CardPattern.DIAMOND, CardNumber.KING),
                new Card(CardPattern.HEART, CardNumber.SIX))));
        final boolean expected = true;

        final boolean actual = gameMachine.checkDealerReceiveCard(dealer);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 점수가 16 초과일 때 카드를 받지 않는지 확인한다.")
    void checkDealerNotReceiveCard() {
        GameMachine gameMachine = new GameMachine(cardDeck);
        Player dealer = new Dealer(new ArrayList<>(Arrays.asList(new Card(CardPattern.DIAMOND, CardNumber.KING),
                new Card(CardPattern.HEART, CardNumber.SEVEN))));
        final boolean expected = false;

        final boolean actual = gameMachine.checkDealerReceiveCard(dealer);
        assertThat(actual).isEqualTo(expected);
    }
}
