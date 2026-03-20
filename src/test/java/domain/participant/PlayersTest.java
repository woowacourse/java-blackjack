package domain.participant;

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
        assertThatThrownBy(() -> new Players(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PLAYERS_INVALID_COUNT.getMessage());
    }
}
