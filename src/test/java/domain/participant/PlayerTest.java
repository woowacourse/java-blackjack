package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

public class PlayerTest {
    @Test
    @DisplayName("플레이어의 이름이 비어있는 경우, 예외가 발생한다.")
    void playerNameIsEmptyTest() {
        List<Card> cardsList = List.of(
                new Card(CardScore.EIGHT, CardSuit.CLUB),
                new Card(CardScore.FOUR, CardSuit.CLUB)
        );
        Cards cards = new Cards(cardsList);

        assertThatThrownBy(() -> new Player(cards, "", new Money(10000)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PLAYER_NAME_IS_EMPTY.getMessage());
    }

    @Test
    @DisplayName("플레이어가 버스트도 아니고, 블랙잭도 아니면 카드를 한 장 더 받을 수 있다.")
    void canHitTest() {
        List<Card> cardsList = List.of(
                new Card(CardScore.EIGHT, CardSuit.CLUB),
                new Card(CardScore.FOUR, CardSuit.CLUB)
        );
        Cards cards = new Cards(cardsList);
        Player player = new Player(cards, "pobi", new Money(10000));

        assertThat(player.canHit()).isTrue();
    }
}
