package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.CardShape;
import blackjack.model.card.CardType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ParticipantTest {

    @ValueSource(strings = {"", "     "})
    @ParameterizedTest
    void 참가자의_이름이_공백인_경우_예외가_발생한다(String name) {
        // given

        // when & then
        assertThatThrownBy(() -> new Participant(name));
    }

    @Test
    void 참여자의_이름이_2글자_이상_5글자가_아니면_예외가_발생한다() {
        // given

        // when & then
        assertThatThrownBy(() -> new Participant("한스한스한스"));
    }

    @Test
    void 참여자에게_카드를_한장_준다() {
        Participant participant = new Participant("프리");
        participant.putCard(new Card(CardShape.HEART, CardType.NORMAL_2));
        assertThat(participant.getReceivedCards().size()).isEqualTo(1);
    }
}
