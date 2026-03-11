package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {

    @Test
    void 게임이_시작되면_참여자_모두_카드를_2장씩_받는다() {
        // arrange
        List<String> names = List.of("이산", "모카", "바니", "소낙눈");
        Players players = new Players(names);
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        BlackJackGame blackJackGame = new BlackJackGame(players, dealer, deck);

        // act
        blackJackGame.initDraw();

        // assert
        List<Participant> allParticipants = Stream.concat(
                Stream.of(dealer), players.getPlayers().stream()
        ).toList();
        assertThat(allParticipants).allSatisfy(participant ->
                assertThat(participant.getCardCount()).isEqualTo(2));
    }
}
