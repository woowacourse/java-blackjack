package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import org.junit.jupiter.api.Test;

public class TestBlackJackDeck {
    @Test
    public void 카드_드로우_정상_작동() {
        BlackJackDeck blackJackDeck = new BlackJackDeck();

        assertThat(blackJackDeck.draw().cardNumber()).isEqualTo(CardNumber.ACE);
        assertThat(blackJackDeck.draw().shape()).isEqualTo(Shape.HEART);
    }

    @Test
    public void 남은_카드가_0장_일_때_드로우() {
        BlackJackDeck blackJackDeck = new BlackJackDeck();

        for(int i = 0; i < 52; i++) {
            blackJackDeck.draw();
        }

        assertThatThrownBy(blackJackDeck::draw).isExactlyInstanceOf(IllegalArgumentException.class).hasMessage(
                ErrorMessage.NO_CARD_IN_DECK.getMessage());
    }
}
