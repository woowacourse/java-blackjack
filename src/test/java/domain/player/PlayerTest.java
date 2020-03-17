package domain.player;

import domain.BlackJackRule;
import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.card.CardDeck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerTest {
    private CardDeck cardDeck;
    private Player player;
    private BlackJackRule blackJackRule;

    @BeforeEach
    private void setup() {
        cardDeck = new CardDeck();
        Card card1 = Card.of(CardNumber.ACE, CardSuitSymbol.CLUB);
        Card card2 = Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB);
        player = new Player("pobi", new ArrayList<>(Arrays.asList(card1, card2)));
        blackJackRule = new BlackJackRule();
    }

    @DisplayName("y를 입력 받을때 카드를 받는지 결정하는 메서드")
    @Test
    void yes_insertCard() {
        if (blackJackRule.isHit(player)) {
            blackJackRule.hit(player, cardDeck.drawCard());
        }

        Assertions.assertThat(player.getCard().getCards().size()).isEqualTo(3);
    }

    @DisplayName("n를 입력 받을때 카드를 받는지 결정하는 메서드")
    @Test
    void no_insertCard() {
        if (blackJackRule.isHit(player)) {
            blackJackRule.hit(player, cardDeck.drawCard());
        }

        Assertions.assertThat(player.getCard().getCards().size()).isEqualTo(3);
    }
}
