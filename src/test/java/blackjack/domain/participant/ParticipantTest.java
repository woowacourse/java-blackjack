package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.result.BlackjackStatus;
import blackjack.domain.result.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ParticipantTest {
    @DisplayName("게임에 유리한 방향으로 ACE를 1, 11 중 선택하여 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {"ACE,NINE,TWO,TWO,14",
            "ACE,TWO,TWO,TWO,17",
            "ACE,ACE,NINE,NINE,20",
            "ACE,ACE,TWO,TWO,16",
            "ACE,ACE,ACE,TEN,13",
            "ACE,ACE,ACE,ACE,14",})
    void calculateWith2Ace(CardNumber cardNumber1,
                           CardNumber cardNumber2,
                           CardNumber cardNumber3,
                           CardNumber cardNumber4,
                           int expected) {
        // given
        Card card1 = new Card(cardNumber1, CardShape.DIA);
        Card card2 = new Card(cardNumber2, CardShape.HEART);
        Card card3 = new Card(cardNumber3, CardShape.CLOVER);
        Card card4 = new Card(cardNumber4, CardShape.SPADE);
        List<Card> cards = List.of(card1, card2, card3, card4);
        Participant participant = Participant.from("kirby");
        cards.forEach(participant::addCard);

        // when
        Score sum = participant.calculate();

        // then
        assertThat(sum).isEqualTo(new Score(expected));
    }

}
