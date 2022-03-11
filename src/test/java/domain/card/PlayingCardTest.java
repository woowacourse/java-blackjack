package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayingCardTest {
    @ParameterizedTest(name = "{0}+{1} - {2}")
    @CsvSource(value = {"SPADES;ACE;(1,11)스페이드", "HEARTS;SEVEN;7하트"}, delimiter = ';')
    @DisplayName("플레잉카드 생성 테스트")
    void createPlayingCard(Suit suit, Denomination denomination, String expected) {
        // given
        PlayingCard playingCard = PlayingCard.of(suit, denomination);

        // when
        String cardName = playingCard.getCardName();

        // then
        assertThat(cardName).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}-{1}")
    @CsvSource(value = {"SPADES,ACE", "HEARTS,SEVEN"})
    @DisplayName("플레잉카드 동등성 테스트")
    void playingCardEqualityTest(Suit suit, Denomination denomination) {
        // given
        PlayingCard playingCard = PlayingCard.of(suit, denomination);
        PlayingCard anotherCard = PlayingCard.of(suit, denomination);

        // when & then
        assertThat(playingCard).isEqualTo(anotherCard);
    }
}
