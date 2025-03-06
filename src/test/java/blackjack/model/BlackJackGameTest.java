package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {

    @Test
    void 게임이_시작하면_모든_플레이어에게_카드를_두장씩_배분한다() {
        // given
        Dealer dealer = new Dealer();
        Participant participant = new Participant("벡터");
        Participants participants = new Participants(List.of(participant));
        DeckInitializer deckInitializer = new DeckInitializer();
        // when
        BlackJackGame blackJackGame = new BlackJackGame(decdeckInitializer, dealer, participants);
        blackJackGame.initializeGame();
        // then
        assertThat(dealer.getReceivedCards().size()).isEqualTo(2);
        assertThat(participant.getReceivedCards().size()).isEqualTo(2);

    }

    @Test
    void 참여자가_카드를_받는다() {
        // given

        // when

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 참여자가_카드를_받지_않는다() {
        // given

        // when

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 딜러의_카드의_합이_16_이하라면_카드를_더_받는다() {
        // given

        // when

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 딜러의_카드의_합이_17_이상이라면_카드를_더_받지_않는다() {
        // given

        // when

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
