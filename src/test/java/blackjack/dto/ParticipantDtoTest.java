package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class ParticipantDtoTest {

    @Test
    void dto로_변환할_수_있다() {
        final Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.DIAMOND, Denomination.JACK));
        final ParticipantDto participantDto = ParticipantDto.from(player);

        final String name = participantDto.name();
        final CardHandDto cardHandDto = participantDto.cardHandDto();
        final int score = participantDto.score();

        assertSoftly(softly -> {
            softly.assertThat(name).isEqualTo("pobi");
            softly.assertThat(cardHandDto.cardDtos()).hasSize(1);
            softly.assertThat(score).isEqualTo(10);
        });
    }
}
