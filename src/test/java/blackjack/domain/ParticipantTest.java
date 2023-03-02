package blackjack.domain;

import static blackjack.domain.Number.ACE;
import static blackjack.domain.Number.JACK;
import static blackjack.domain.Number.KING;
import static blackjack.domain.Number.QUEEN;
import static blackjack.domain.Symbol.CLUB;
import static blackjack.domain.Symbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantTest {

    @DisplayName("참가자는 카드를 받으면 카드의 수가 1 증가한다.")
    @Test
    void should_HasSize_1Increased() {
        Participant participant = new Participant();
        int previousSize = participant.getCards().size();

        Card card = new Card(SPADE, JACK);
        participant.take(card);
        int currentSize = participant.getCards().size();

        assertThat(currentSize).isEqualTo(previousSize + 1);
    }

    @DisplayName("참가자는 카드를 받으면 마지막 위치에 저장한다.")
    @Test
    void should_addCard_At_LastIndex() {
        Participant participant = new Participant();

        Card card = new Card(SPADE, JACK);
        participant.take(card);

        List<Card> cards = participant.getCards();
        Card lastCard = cards.get(cards.size() - 1);
        assertThat(lastCard).isEqualTo(card);
    }

    @DisplayName("참가자가 가지고 있는 카드의 합을 반환한다.")
    @Test
    void should_ReturnSumOfCards() {
        Participant participant = new Participant();

        participant.take(new Card(SPADE, JACK));
        participant.take(new Card(CLUB, QUEEN));

        assertThat(participant.computeSumOfCards()).isEqualTo(20);
    }

    @DisplayName("참가자 카드 중 ACE는 11을 기본값으로 한다.")
    @Test
    void should_defaultValueOfACE_Is_11() {
        Participant participant = new Participant();

        participant.take(new Card(SPADE, ACE));
        participant.take(new Card(CLUB, QUEEN));

        assertThat(participant.computeSumOfCards()).isEqualTo(21);
    }

    @DisplayName("참가자 카드 합이 21을 초과하면 ACE의 값을 1로 계산한다.")
    @Test
    void should_valueOfAce_Is_1_WhenSumOfCardsOver21() {
        Participant participant = new Participant();

        participant.take(new Card(SPADE, ACE));
        participant.take(new Card(SPADE, KING));
        participant.take(new Card(CLUB, QUEEN));

        assertThat(participant.computeSumOfCards()).isEqualTo(21);
    }
}
