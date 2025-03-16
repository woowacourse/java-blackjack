package blackjack.domain.game;

import blackjack.domain.card.Deck;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    BlackjackGame blackjackGame;

    @BeforeEach
    void init() {
        Deck deck = new Deck();
        Player player = new Player("히로", new Hand());
        blackjackGame = new BlackjackGame(deck, new Players(List.of(player)));
    }

    @Test
    void 손에_카드_1장을_쥐어준다() {
        // given
        Participant participant = new Player("히로", new Hand());
        blackjackGame.giveMoreCard(participant);

        // when & then
        assertThat(participant.getCards()).hasSize(1);
    }

    @Test
    void 참가자에게_시작_카드를_2장씩_분배한다() {
        // given
        Hand hand = new Hand();
        Player player = new Player("히로", hand);
        Players players = new Players(List.of(player));
        BlackjackGame blackjackGame = new BlackjackGame(new Deck(), players);

        // when
        blackjackGame.giveStartingCards();

        // then
        assertThat(player.getCards()).hasSize(2);
    }

    @Test
    void 참가자에게_추가적으로_카드를_분배한다() {
        // given
        Hand hand = new Hand();
        Player player = new Player("히로", hand);

        // when
        blackjackGame.giveMoreCard(player);

        // then
        assertThat(player.getCards()).hasSize(1);
    }
}
