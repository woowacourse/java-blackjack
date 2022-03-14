package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardGeneratorTest {

    @ParameterizedTest
    @EnumSource(value = CardType.class, names = {"DIAMOND", "SPADE", "HEART", "CLOVER"})
    @DisplayName("모양별로 13개씩 카드가 만들어지는지 확인한다.")
    public void generateThirteenCardForCardType(CardType cardType) {
        CardGenerator cardGenerator = new CardGenerator();
        List<Card> cards = cardGenerator.generate();
        assertThat(cards.stream().filter(card -> card.getCardText().contains(cardType.getName()))
                .count()).isEqualTo(13);
    }

}
