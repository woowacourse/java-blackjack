package domain.player;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ParticipantTest {

    @Test
    @DisplayName("카드를 출력한다")
    void createParticipant_thenDisplayCards() {
        //given
        Participant participant = Participant.from("power");
        participant.takeCard(Card.of(Suit.DIAMOND, Rank.FIVE));
        participant.takeCard(Card.of(Suit.SPADE, Rank.TWO));
        participant.takeCard(Card.of(Suit.CLUBS, Rank.SIX));

        //when
        List<Card> cards = participant.getCards();

        //then
        assertThat(cards)
                .isEqualTo(List.of(Card.of(Suit.DIAMOND, Rank.FIVE)
                        , Card.of(Suit.SPADE, Rank.TWO)
                        , Card.of(Suit.CLUBS, Rank.SIX)));
    }
}
