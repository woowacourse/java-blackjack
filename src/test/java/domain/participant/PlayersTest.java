package domain.participant;

import static domain.participant.Players.MAXIMUM_PLAYER_COUNT;
import static domain.participant.Players.MINIMUM_PLAYER_COUNT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.Money;
import domain.card.Card;
import domain.card.CardScore;
import domain.card.CardSuit;
import domain.card.Cards;
import exception.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    @DisplayName("플레이어의 이름이 중복되는 경우, 예외가 발생한다.")
    void playersNameIsDuplicateTest() {
        List<Card> cardsList = List.of(
                new Card(CardScore.EIGHT, CardSuit.CLUB),
                new Card(CardScore.FOUR, CardSuit.CLUB)
        );
        Cards cards = new Cards(cardsList);
        Player player1 = new Player(cards, "pobi", new Money(10000));
        Player player2 = new Player(cards, "pobi", new Money(10000));

        assertThatThrownBy(() -> new Players(List.of(player1, player2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PLAYER_NAME_IS_DUPLICATE.getMessage());
    }

    @Test
    @DisplayName("플레이어의 수가 1명 이상 8명 이하가 아닐 경우, 예외가 발생한다.")
    void playersCountIsOutOfRangeTest() {
        List<Card> cardsList = List.of(
                new Card(CardScore.EIGHT, CardSuit.CLUB),
                new Card(CardScore.FOUR, CardSuit.CLUB)
        );
        Cards cards = new Cards(cardsList);
        Player player1 = new Player(cards, "pobi", new Money(10000));
        Player player2 = new Player(cards, "woni", new Money(10000));
        Player player3 = new Player(cards, "stark", new Money(10000));
        Player player4 = new Player(cards, "downer", new Money(10000));
        Player player5 = new Player(cards, "chorok", new Money(10000));
        Player player6 = new Player(cards, "logi", new Money(10000));
        Player player7 = new Player(cards, "neo", new Money(10000));
        Player player8 = new Player(cards, "brown", new Money(10000));
        Player player9 = new Player(cards, "lisa", new Money(10000));

        assertThatThrownBy(() -> new Players(List.of(player1, player2, player3, player4, player5, player6, player7, player8, player9)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PLAYERS_INVALID_COUNT.getMessage(MINIMUM_PLAYER_COUNT, MAXIMUM_PLAYER_COUNT));
    }
}
