package blackjack.domain;

import static blackjack.domain.card.CardSpec.ACE;
import static blackjack.domain.card.CardSpec.FOUR;
import static blackjack.domain.card.CardSpec.JACK;
import static blackjack.domain.card.CardSpec.KING;
import static blackjack.domain.card.CardSpec.QUEEN;
import static blackjack.domain.card.CardSpec.THREE;
import static blackjack.domain.card.CardSpec.TWO;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void anyone_blackjack() {
        Participants participants = Participants.of(
            Participant.of("blackjack", 1000, ACE.card(), KING.card())
        );
        Game game = Game.of(
            Dealer.of(TWO.card(), KING.card()),
            participants,
            Cards.createNormalCards()
        );
        Assertions.assertThat(game.isAnyParticipantBlackjack()).isTrue();
    }

    @Test
    void dealer_notDrawable() {
        Dealer dealer = Dealer.of(TWO.card(), QUEEN.card());
        Participants participants = Participants.of(
            Participant.of("nabom", 1000, TWO.card(), THREE.card())
        );

        Game game = Game.of(
            dealer,
            participants,
            Cards.of(JACK.card())
        );
        Assertions.assertThat(game.drawableDealer()).isTrue();

        game.drawCardTo(dealer);
        Assertions.assertThat(game.drawableDealer()).isFalse();
    }

    @Test
    void participant_drawTest() {
        Score expectedScore = Score.of(9);
        Dealer dealer = Dealer.of(TWO.card(), QUEEN.card());
        Participant participant = Participant.of("nabom", 1000, TWO.card(), THREE.card());
        Participants participants = Participants.of(participant);

        Game game = Game.of(dealer, participants, Cards.of(FOUR.card()));
        game.drawCardTo(participant);

        Assertions.assertThat(participant.score()).isEqualTo(expectedScore);
    }
}