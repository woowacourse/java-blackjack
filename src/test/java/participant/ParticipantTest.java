package participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Pattern;
import blackjack.domain.participant.Hand;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게임 참여자는")
class ParticipantTest {
    @DisplayName("카드를 여러 장 받을 수 있다.")
    @Test
    void hitCards() {
        Participant participant = new Participant(new Name("소니"), new Hand()) {
            @Override
            public void win() {
            }

            @Override
            public void lose() {
            }

            @Override
            public void tie() {
            }
        };

        List<Card> cards =  List.of(new Card(CardNumber.ACE, Pattern.SPADE), new Card(CardNumber.EIGHT, Pattern.CLOVER));
        participant.hit(cards);

        assertThat(participant.showCards()).containsExactlyElementsOf(cards);
    }

}