package blackJack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Symbol;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @Test
    void 처음_게임_시작시_참가자들에게_카드를_두장씩_분배한다() {
        Participants participants = new Participants(List.of("kei", "rookie", "parang"));
        BlackJackGame blackJackGame = new BlackJackGame(participants);
        Player player = participants.getPlayers().get(0);

        blackJackGame.firstCardDispensing();

        assertThat(player.getCards().size()).isEqualTo(2);
    }

    @Test
    void 딜러의_수익을_계산한다() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.of(Symbol.CLOVER, Denomination.JACK));
        dealer.hit(Card.of(Symbol.CLOVER, Denomination.NINE));

        Player player1 = new Player("rookie");
        player1.hit(Card.of(Symbol.CLOVER, Denomination.ACE));
        player1.hit(Card.of(Symbol.CLOVER, Denomination.QUEEN));
        player1.betting(10000);

        Player player2 = new Player("parang");
        player2.hit(Card.of(Symbol.CLOVER, Denomination.SEVEN));
        player2.hit(Card.of(Symbol.CLOVER, Denomination.KING));
        player2.betting(20000);

        Participants participants = new Participants(dealer, List.of(player1, player2));
        BlackJackGame blackJackGame = new BlackJackGame(participants);

        assertThat(blackJackGame.calculateDealerProfit()).contains(
                Map.entry(dealer, 5000)
        );
    }

    @Test
    void 플레이어의_수익을_계산한다() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.of(Symbol.CLOVER, Denomination.JACK));
        dealer.hit(Card.of(Symbol.CLOVER, Denomination.NINE));

        Player player1 = new Player("rookie");
        player1.hit(Card.of(Symbol.CLOVER, Denomination.ACE));
        player1.hit(Card.of(Symbol.CLOVER, Denomination.QUEEN));
        player1.betting(10000);

        Player player2 = new Player("parang");
        player2.hit(Card.of(Symbol.CLOVER, Denomination.SEVEN));
        player2.hit(Card.of(Symbol.CLOVER, Denomination.KING));
        player2.betting(20000);

        Participants participants = new Participants(dealer, List.of(player1, player2));
        BlackJackGame blackJackGame = new BlackJackGame(participants);

        assertThat(blackJackGame.calculatePlayersProfit()).contains(
                Map.entry(player1, 15000),
                Map.entry(player2, -20000)
        );
    }
}
