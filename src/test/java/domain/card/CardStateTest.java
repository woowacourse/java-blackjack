package domain.card;

import domain.HitThreshold;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.MockCard.*;
import static domain.card.CardState.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CardStateTest {

    @ParameterizedTest
    @DisplayName("카드 상태 구하기")
    @MethodSource("provideCardAndState")
    void calculateCardState(Cards cards, CardState cardState) {
        assertThat(cards.getCardState()).isEqualTo(cardState);
    }

    public static Stream<Arguments> provideCardAndState() {
        return Stream.of(
                Arguments.of(generateCards(CLUB_ACE_CARD, HEART_TEN_CARD), BLACKJACK),
                Arguments.of(generateCards(HEART_TEN_CARD, HEART_TEN_CARD, CLUB_ACE_CARD), STAND),
                Arguments.of(generateCards(CLUB_ACE_CARD, SPADE_NINE_CARD), STAND),
                Arguments.of(generateCards(SPADE_NINE_CARD, HEART_TEN_CARD, SPADE_NINE_CARD), BUST),
                Arguments.of(generateCards(CLUB_ACE_CARD, CLUB_ACE_CARD, HEART_TEN_CARD, HEART_TEN_CARD), BUST)
        );
    }

    private static Cards generateCards(final Card... cards) {
        final Cards myCards = new Cards(HitThreshold.PLAYER_THRESHOLD);
        for (final Card card : cards) {
            myCards.add(card);
        }
        return myCards;
    }

}
