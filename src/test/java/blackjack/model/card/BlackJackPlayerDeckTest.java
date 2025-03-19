package blackjack.model.card;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.List;
import org.junit.jupiter.api.Test;

class BlackJackPlayerDeckTest {

    @Test
    void N_개의_카드를_반환한다() {

        CardDeck cardDeck = new CardDeck(new BlackJackCards(
                List.of(createCard(CardNumber.TWO), createCard(CardNumber.THREE), createCard(CardNumber.FOUR),
                        createCard(CardNumber.FIVE))
        ));
        int drawSize = 4;

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(cardDeck.draw(drawSize).getValues()).hasSize(drawSize);
            softAssertions.assertThat(cardDeck.blackJackCards().getValues()).hasSize(0);
        });
    }

}
