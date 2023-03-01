package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("참여자 카드")
class ParticipantCardsTest {
    @Test
    @DisplayName("생성한다.")
    void create() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.THREE);
        Card cardTwo = new Card(CardShape.DIAMOND, CardNumber.TWO);

        assertThatNoException()
                .isThrownBy(() -> new ParticipantCards(List.of(cardOne, cardTwo)));
    }

    @Test
    @DisplayName("생성시에 카드가 두 장이 아닐 경우 예외가 발생한다.")
    void throwExceptionWhenCardsHasSizeLowerThan1() {
        assertThatThrownBy(() -> new ParticipantCards(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("생성시에 카드가 중복될 경우 예외가 발생한다.")
    void throwExceptionWhenInitialCardsDuplicated() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.THREE);
        Card cardTwo = new Card(CardShape.DIAMOND, CardNumber.THREE);

        assertThatThrownBy(() -> new ParticipantCards(List.of(cardOne, cardTwo)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복되는 카드를 가지는 경우 예외가 발생한다.")
    void throwExceptionWhenCardsDuplicated() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.THREE);
        Card cardTwo = new Card(CardShape.DIAMOND, CardNumber.FOUR);
        ParticipantCards participantCards = new ParticipantCards(List.of(cardOne, cardTwo));

        assertThatThrownBy(() -> participantCards.receive(new Card(CardShape.DIAMOND, CardNumber.THREE)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}