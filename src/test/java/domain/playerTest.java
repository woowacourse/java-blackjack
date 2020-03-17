package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.card.Cards;
import domain.player.Player;
import domain.player.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class playerTest {

    @DisplayName(" 카드를 받는지 결정하는 메서드")
    @Test
    void yes_insertCard() {
        Cards cards = new Cards();
        Player player = new Player("pobi", cards.giveCard(), cards.giveCard());
        player.drawCard(cards.giveCard());

        Assertions.assertThat(player).extracting("cards").asList().size().isEqualTo(3);
    }

    @DisplayName("중복된 카드가 주어지면 에러")
    @Test
    void validateDuplicateCard() {
        Card card1 = new Card(CardNumber.FIVE, CardSuitSymbol.CLUB);
        Card card2 = new Card(CardNumber.FIVE, CardSuitSymbol.CLUB);

        Assertions.assertThatThrownBy(() -> new Player("pobi", card1, card2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복된 카드가 주어지면 에러")
    @Test
    void insertValidateDuplicateCard() {
        Card card1 = new Card(CardNumber.FIVE, CardSuitSymbol.CLUB);
        Card card2 = new Card(CardNumber.FOUR, CardSuitSymbol.CLUB);
        Card card3 = new Card(CardNumber.FOUR, CardSuitSymbol.CLUB);
        User user = new Player("pobi", card1, card2);

        Assertions.assertThatThrownBy(() -> user.drawCard(card3))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
