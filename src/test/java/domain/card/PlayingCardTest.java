package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayingCardTest {
    @Test
    @DisplayName("카드 생성 테스트")
    void createPlayingCard() {
        // given
        Suit expectedSuit = Suit.SPADES;
        Denomination expectedDenomination = Denomination.ACE;
        PlayingCard spadesAce = PlayingCard.of(expectedSuit, expectedDenomination);

        // when
        String cardName = spadesAce.getCardName();

        // then
        assertThat(cardName).isEqualTo("(1,11)스페이드");
    }
}
