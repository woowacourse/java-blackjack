package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Deck;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantsTest {

    @Test
    void 중복된_플레이어_이름이_존재하면_예외를_던진다() {
        final Dealer dealer = new Dealer();
        final List<Player> players = List.of(new Player("toney"), new Player("toney"));

        assertThatThrownBy(() -> new Participants(dealer, players)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어가_5명_초과라면_예외를_던진다() {
        final Dealer dealer = new Dealer();
        final List<Player> players = List.of(
                new Player("dazzle"),
                new Player("kokodak"),
                new Player("toney"),
                new Player("pobi"),
                new Player("crong"),
                new Player("jason")
        );

        assertThatThrownBy(() -> new Participants(dealer, players)).isInstanceOf(IllegalArgumentException.class);
    }

    @Nested
    class drawCardForPlayer_메서드는 {

        @Test
        void 해당_이름의_플레이어가_존재하지_않으면_예외를_던진다() {
            final Participants participants = new Participants(
                    new Dealer(),
                    List.of(new Player("toney"), new Player("dazzle"))
            );
            final Deck deck = Deck.createUsingTrump(1);

            assertThatThrownBy(() -> participants.drawCardForPlayer(new PlayerName("kokodak"), deck))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 해당_이름의_플레이어가_존재하면_플레이어는_카드를_뽑는다() {
            final Participants participants = new Participants(
                    new Dealer(),
                    List.of(new Player("toney"), new Player("dazzle"))
            );
            final Deck deck = Deck.createUsingTrump(1);
            final PlayerName playerName = new PlayerName("toney");

            participants.drawCardForPlayer(playerName, deck);

            assertThat(participants.getPlayerHand(playerName).count()).isEqualTo(1);
        }
    }

    @Test
    void 참가한_플레이어들의_이름목록을_확인한다() {
        final Participants participants = new Participants(
                new Dealer(),
                List.of(new Player("toney"), new Player("dazzle"))
        );

        final List<PlayerName> playerNames = participants.getPlayerNames();

        assertThat(playerNames).containsExactly(new PlayerName("toney"), new PlayerName("dazzle"));
    }

    @Test
    void 딜러가_카드를_뽑는다() {
        final Participants participants = new Participants(
                new Dealer(),
                List.of(new Player("toney"), new Player("dazzle"))
        );
        final Deck deck = Deck.createUsingTrump(1);

        participants.drawCardForDealer(deck);

        assertThat(participants.getDealerHand().count()).isEqualTo(1);
    }
}
