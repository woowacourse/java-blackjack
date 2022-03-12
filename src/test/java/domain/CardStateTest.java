package domain;

import static domain.CardState.BLACKJACK;
import static domain.CardState.BUST;
import static domain.CardState.STAND;
import static domain.MockCard.ACE_CARD;
import static domain.MockCard.NINE_CARD;
import static domain.MockCard.TEN_CARD;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardStateTest {

    @ParameterizedTest
    @DisplayName("카드 상태 구하기")
    @MethodSource("provideCardAndState")
    void calculateCardState(Cards cards, CardState cardState) {
        assertThat(cards.getCardState()).isEqualTo(cardState);
    }

    public static Stream<Arguments> provideCardAndState() {
        return Stream.of(
                Arguments.of(generateCards(ACE_CARD, TEN_CARD), BLACKJACK),
                Arguments.of(generateCards(ACE_CARD, TEN_CARD, TEN_CARD), STAND),
                Arguments.of(generateCards(ACE_CARD, NINE_CARD), STAND),
                Arguments.of(generateCards(NINE_CARD, TEN_CARD, NINE_CARD), BUST),
                Arguments.of(generateCards(ACE_CARD, ACE_CARD, TEN_CARD, TEN_CARD), BUST)
        );
    }

    private static Cards generateCards(final Card... cards) {
        final Cards myCards = new Cards(CanAddCardThreshold.PLAYER_THRESHOLD);
        for (final Card card : cards) {
            myCards.add(card);
        }
        return myCards;
    }

}
