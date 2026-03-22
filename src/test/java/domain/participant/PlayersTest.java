package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.card.Card;
import domain.card.CardScore;
import domain.card.CardSuit;
import domain.card.Hand;
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
        Hand hand = new Hand(cardsList);
        Player player1 = new Player(hand, "pobi", new Money(10000));
        Player player2 = new Player(hand, "pobi", new Money(10000));

        assertThatThrownBy(() -> new Players(List.of(player1, player2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어의 이름은 중복되지 않아야 합니다.");
    }

    @Test
    @DisplayName("플레이어의 수가 1명 이상 8명 이하가 아닐 경우, 예외가 발생한다.")
    void playersCountIsOutOfRangeTest() {
        List<Card> cardsList = List.of(
                new Card(CardScore.EIGHT, CardSuit.CLUB),
                new Card(CardScore.FOUR, CardSuit.CLUB)
        );
        Hand hand = new Hand(cardsList);
        Player player1 = new Player(hand, "pobi", new Money(10000));
        Player player2 = new Player(hand, "woni", new Money(10000));
        Player player3 = new Player(hand, "stark", new Money(10000));
        Player player4 = new Player(hand, "downer", new Money(10000));
        Player player5 = new Player(hand, "chorok", new Money(10000));
        Player player6 = new Player(hand, "logi", new Money(10000));
        Player player7 = new Player(hand, "neo", new Money(10000));
        Player player8 = new Player(hand, "brown", new Money(10000));
        Player player9 = new Player(hand, "lisa", new Money(10000));

        assertThatThrownBy(() -> new Players(List.of(player1, player2, player3, player4, player5, player6, player7, player8, player9)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어의 수는 1명 이상 8명 이하여야 합니다.");
    }
}
