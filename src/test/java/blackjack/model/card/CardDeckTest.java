package blackjack.model.card;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.List;

import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    void 몇_장을_뽑을지_알려주면_카드를_뽑아준다() {
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

    @Test
    void 카드덱에_남아있는_카드_수가_부족하면_카드를_뽑을_수_없다() {
        CardDeck cardDeck = new CardDeck(new Cards(createCard(CardNumber.TWO)));

        assertThatThrownBy(() -> cardDeck.draw(2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("남은 카드가 부족합니다.");
    }

}
