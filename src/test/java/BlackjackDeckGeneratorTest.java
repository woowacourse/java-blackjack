import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.blackjackgame.BlackjackDeck;
import domain.blackjackgame.BlackjackDeckGenerator;
import domain.strategy.BlackjackDrawStrategy;
import exception.BlackJackException;
import org.junit.jupiter.api.Test;

public class BlackjackDeckGeneratorTest {

    @Test
    void 블랙잭_카드는_52장만_만들어진다() {
        BlackjackDeck deck = BlackjackDeckGenerator.generateDeck(new BlackjackDrawStrategy());
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }
        assertThatThrownBy(deck::drawCard).isInstanceOf(BlackJackException.class);
    }
}
