package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardGeneratorTest {

    @ParameterizedTest(name = "{0} {displayName}")
    @EnumSource(value = CardType.class, names = {"DIAMOND", "SPADE", "HEART", "CLOVER"})
    @DisplayName("일 때 13개의 카드가 만들어지는지 확인한다.")
    public void generateThirteenCardForCardType(CardType cardType) {
        CardGenerator cardGenerator = new CardGenerator();
        List<Card> cards = cardGenerator.generate();
        long cardCount = cards.stream()
                .filter(card -> card.getCardText().contains(cardType.getName()))
                .count();
        assertThat(cardCount).isEqualTo(13);
    }

    @ParameterizedTest(name = "{0} {displayName}")
    @EnumSource(value = CardNumber.class, names = {"ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT",
    "NINE", "TEN", "JACK", "QUEEN", "KING"})
    @DisplayName("일 때 4개의 카드가 만들어지는지 확인한다.")
    public void generateThirteenCardForCardType(CardNumber cardNumber) {
        CardGenerator cardGenerator = new CardGenerator();
        List<Card> cards = cardGenerator.generate();
        long cardCount = cards.stream()
                .filter(card -> card.getCardText().contains(cardNumber.getOriginalName()))
                .count();
        assertThat(cardCount).isEqualTo(4);
    }

}
