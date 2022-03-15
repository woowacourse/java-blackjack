package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayingCardTest {
    @ParameterizedTest(name = "{0}+{1} - {2}")
    @CsvSource(value = {"SPADES;ACE;1스페이드", "HEARTS;SEVEN;7하트"}, delimiter = ';')
    @DisplayName("카드 생성 테스트")
    void createCard(Suit suit, Denomination denomination, String expected) {
        // given
        PlayingCard playingCard = PlayingCard.of(suit, denomination);

        // when
        Suit suitFromCard = playingCard.getSuit();
        Denomination denominationFromCard = playingCard.getDenomination();

        // then
        assertThat(PlayingCard.of(suitFromCard, denominationFromCard)).isEqualTo(playingCard);
    }

    @ParameterizedTest(name = "{0}-{1}")
    @CsvSource(value = {"SPADES,ACE", "HEARTS,SEVEN"})
    @DisplayName("카드 동등성 테스트")
    void cardEqualityTest(Suit suit, Denomination denomination) {
        // given
        PlayingCard playingCard = PlayingCard.of(suit, denomination);
        PlayingCard anotherPlayingCard = PlayingCard.of(suit, denomination);

        // when & then
        assertThat(playingCard).isEqualTo(anotherPlayingCard);
    }
}
