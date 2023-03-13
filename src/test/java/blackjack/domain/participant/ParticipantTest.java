package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParticipantTest {

    @Test
    @DisplayName("카드 한장씩 잘 받는지 테스트")
    void receiveCardTest() {
        Participant participant = new Player("IO");
        Card card = new Card(CardShape.CLOVER, CardNumber.FIVE);

        participant.receiveCard(card);

        List<Card> cards = participant.getCards();
        assertThat(cards.get(cards.size() - 1)).isEqualTo(card);
    }


    @Test
    @DisplayName("블랙잭 여부 확인 테스트: 블랙잭인 경우")
    void isBlackJackTest() {
        Participant participant = new Player("IO");
        participant.receiveCard(new Card(CardShape.SPADE, CardNumber.ACE));
        participant.receiveCard(new Card(CardShape.CLOVER, CardNumber.TEN));

        assertThat(participant.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("블랙잭 여부 확인 테스트: 점수가 21점이 아닌 경우")
    void isBlackJackFailScoreTest() {
        Participant participant = new Player("IO");
        participant.receiveCard(new Card(CardShape.SPADE, CardNumber.TEN));
        participant.receiveCard(new Card(CardShape.CLOVER, CardNumber.TEN));

        assertThat(participant.isBlackJack()).isFalse();
    }

    @Test
    @DisplayName("블랙잭 여부 확인 테스트: 카드가 두 장이 아닌 경우")
    void isBlackJackFailCardCountTest() {
        Participant participant = new Player("IO");
        participant.receiveCard(new Card(CardShape.SPADE, CardNumber.ACE));
        participant.receiveCard(new Card(CardShape.CLOVER, CardNumber.EIGHT));
        participant.receiveCard(new Card(CardShape.CLOVER, CardNumber.TWO));

        assertThat(participant.getScore()).isEqualTo(21);
        assertThat(participant.isBlackJack()).isFalse();
    }
}
