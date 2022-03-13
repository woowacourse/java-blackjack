package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardTest {
    @ParameterizedTest(name = "{0}+{1} - {2}")
    @CsvSource(value = {"SPADES;ACE;(1,11)스페이드", "HEARTS;SEVEN;7하트"}, delimiter = ';')
    @DisplayName("카드 생성 테스트")
    void createCard(Suit suit, Denomination denomination, String expected) {
        // given
        Card card = Card.of(suit, denomination);

        // when
        String cardName = card.getCardName();

        // then
        assertThat(cardName).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}-{1}")
    @CsvSource(value = {"SPADES,ACE", "HEARTS,SEVEN"})
    @DisplayName("카드 동등성 테스트")
    void cardEqualityTest(Suit suit, Denomination denomination) {
        // given
        Card card = Card.of(suit, denomination);
        Card anotherCard = Card.of(suit, denomination);

        // when & then
        assertThat(card).isEqualTo(anotherCard);
    }
}
