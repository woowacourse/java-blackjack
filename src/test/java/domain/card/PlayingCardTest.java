package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayingCardTest {
    @Test
    @DisplayName("카드 생성 테스트")
    void playingCard_create() {
        // given
        final Suit expectedSuit = Suit.SPADES;
        final Denomination expectedDenomination = Denomination.ACE;
        PlayingCard spadesAce = PlayingCard.of(expectedSuit, expectedDenomination);

        // when
        final Suit suit = spadesAce.getSuit();
        final Denomination denomination = spadesAce.getDenomination();

        // then
        assertAll(
                () -> assertThat(suit).isEqualTo(expectedSuit),
                () -> assertThat(denomination).isEqualTo(expectedDenomination)
        );
    }
}
