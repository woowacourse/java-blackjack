package blackjack.model.card;

import static blackjack.model.card.CardCreator.createCard;
import java.util.List;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    void N_개의_카드를_반환한다() {

        CardDeck cardDeck = new CardDeck(new Cards(
                List.of(createCard(CardNumber.TWO), createCard(CardNumber.THREE), createCard(CardNumber.FOUR),
                        createCard(CardNumber.FIVE))
        ));
        int drawSize = 4;

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(cardDeck.draw(drawSize).getValues()).hasSize(drawSize);
            softAssertions.assertThat(cardDeck.cards().getValues()).hasSize(0);
        });
    }

}
