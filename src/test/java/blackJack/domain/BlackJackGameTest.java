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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @Test
    @DisplayName("BlackJackGame 생성 테스트")
    void createBlackJackGame() {
        Participants participants = new Participants(List.of("kei", "rookie", "parang"));

        assertThat(new BlackJackGame(participants)).isNotNull();
    }

    @Test
    @DisplayName("게임 시작시 최초 카드 분배 기능 테스트")
    void firstCardDispensing() {
        Participants participants = new Participants(List.of("kei", "rookie", "parang"));
        BlackJackGame blackJackGame = new BlackJackGame(participants);
        Player player = participants.getPlayers().get(0);

        blackJackGame.firstCardDispensing();

        assertThat(player.getCards().size()).isEqualTo(2);
    }

    @Test
    void calculateDealerProfit() {
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
    void calculatePlayersProfit() {
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
