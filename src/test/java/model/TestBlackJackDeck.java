package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class TestBlackJackDeck {
    @Test
    public void 카드_드로우_정상_작동() {
        BlackJackDeck blackJackDeck = new BlackJackDeck();

        assertThat(blackJackDeck.draw().cardNumber()).isEqualTo(CardNumber.ACE);
        assertThat(blackJackDeck.draw().shape()).isEqualTo(Shape.HEART);
    }
}
