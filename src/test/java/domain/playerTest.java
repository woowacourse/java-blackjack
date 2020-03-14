package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.card.Cards;
import domain.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class playerTest {
    private Cards cards;
    private Player player;

    @BeforeEach
    private void setup() {
        Card card1 = Card.of(CardNumber.ACE, CardSuitSymbol.CLUB);
        Card card2 = Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB);
        cards = new Cards();
        player = new Player("pobi", card1, card2);
    }

    @DisplayName(" 카드를 받는지 결정하는 메서드")
    @Test
    void yes_insertCard() {
        player.insertCard(cards);

        Assertions.assertThat(player).extracting("cards").asList().size().isEqualTo(3);
    }

}
