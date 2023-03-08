package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckFactory;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantsTest {

    @Test
    void 중복되는_이름이_존재하면_예외를_던진다() {
        final List<Participant> participants = List.of(
                new Dealer(),
                new Player("dazzle"),
                new Player("dazzle"),
                new Player("kokodak"));

        assertThatThrownBy(() -> new Participants(participants))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 딜러_포함_참여자가_6명_초과라면_예외를_던진다() {
        final List<Participant> participants = List.of(
                new Dealer(),
                new Player("dazzle"),
                new Player("kokodak"),
                new Player("odo"),
                new Player("hoi"),
                new Player("gray"),
                new Player("pobi")
        );

        assertThatThrownBy(() -> new Participants(participants))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 참가자들에게_카드를_나눠준다() {
        final Deck deck = DeckFactory.createWithCount(1);
        final Participants participants = new Participants(List.of(
                new Player("dani")
        ));

        participants.drawCard(deck, 5);

        final Participant dani = participants.getParticipants().get(0);
        assertThat(dani.getCards().count()).isEqualTo(5);
    }

    @Test
    void 참가자들중_딜러를_찾는다() {
        Dealer dealer = new Dealer();
        final Participants participants = new Participants(List.of(
                dealer,
                new Player("kokodak"),
                new Player("dani")
        ));

        assertThat(participants.getDealer()).isEqualTo(dealer);
    }

    @Test
    void 참가자들중_플레이어들을_찾는다() {
        final Participants participants = new Participants(List.of(
                new Dealer(),
                new Player("kokodak"),
                new Player("dani")
        ));

        assertThat(participants.getPlayers().size()).isEqualTo(2);
    }
}
