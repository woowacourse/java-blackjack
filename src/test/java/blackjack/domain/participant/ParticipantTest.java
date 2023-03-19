package blackjack.domain.participant;

import blackjack.domain.card.*;
import blackjack.domain.game.Hand;
import blackjack.fixture.HandFixture;
import blackjack.fixture.ParticipantFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipantTest {
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "       "})
    @DisplayName("이름이 공백이거나 비어있을 경우 예외가 발생한다.")
    void throwExceptionWhenNameIsBlank(final String nameValue) {
        final Deck deck = new CardDeck();
        final Hand participantsCards
                = HandFixture.create(deck.draw(), deck.draw(), List.of());
        assertThatThrownBy(() -> new Participant(participantsCards, nameValue) {
            @Override
            public boolean isHittable() {
                throw new UnsupportedOperationException();
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("자신의 점수를 계산한다.")
    void getTotalPoint() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.SIX);
        Card cardTwo = new Card(CardShape.HEART, CardNumber.ACE);
        Participant participant = ParticipantFixture.create(cardOne, cardTwo, List.of());
        int totalPoint = participant.getTotalPoint();

        assertThat(totalPoint).isEqualTo(17);
    }

    @Test
    @DisplayName("참가자가 카드를 뽑는다.")
    void hit() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.THREE);
        Card cardTwo = new Card(CardShape.DIAMOND, CardNumber.TWO);
        Participant participant = ParticipantFixture.create(cardOne, cardTwo, List.of());
        int beforeHitPoint = participant.getTotalPoint();
        participant.hit(new Card(CardShape.SPADE, CardNumber.ACE));
        int afterHitPoint = participant.getTotalPoint();

        assertThat(afterHitPoint).isGreaterThan(beforeHitPoint);
    }

    @Test
    @DisplayName("참가자가 카드를 보여준다.")
    void open() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.THREE);
        Card cardTwo = new Card(CardShape.DIAMOND, CardNumber.TWO);
        Participant participant = ParticipantFixture.create(cardOne, cardTwo, List.of());

        assertThat(participant.open(2)).containsAll(List.of(cardOne, cardTwo));
    }

    @ParameterizedTest
    @ValueSource(strings = {"헤나01", "헤나02", "헤나03"})
    @DisplayName("참가자 이름을 가져온다.")
    void getName(final String nameValue) {
        final Deck deck = new CardDeck();
        final Hand cards = HandFixture.create(deck.draw(), deck.draw(), List.of());
        final Participant participant = new Participant(cards, nameValue) {
            @Override
            public boolean isHittable() {
                throw new UnsupportedOperationException();
            }
        };
        final Name name = participant.getName();

        assertThat(name.getValue()).isEqualTo(nameValue);
    }
}
