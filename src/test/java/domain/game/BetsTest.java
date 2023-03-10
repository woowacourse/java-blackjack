package domain.game;

import static domain.Fixtures.ACE_HEART;
import static domain.Fixtures.THREE_SPADE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import domain.result.WinningResult;
import domain.shuffler.FixedCardsShuffler;
import java.util.List;
import org.junit.jupiter.api.Test;

class BetsTest {

    @Test
    void addBet() {
        Player player = new Player("깃짱");
        Money money = new Money(1000);

        Bets bets = new Bets();
        assertDoesNotThrow(() -> bets.addBet(player, money));
    }

    @Test
    void calculateBets() {
        Participants participants = new Participants(List.of("깃짱", "이리내"));
        participants.distributeInitialCards(new CardDeck(new FixedCardsShuffler()));

        Dealer dealer = participants.getDealer(); // score: 20
        Player gitJjang = participants.getPlayers().get(0); // score: 20
        Player irene = participants.getPlayers().get(1); // score: 20

        Bets bets = new Bets();
        bets.addBet(gitJjang, new Money(1000));
        bets.addBet(irene, new Money(10000));

        gitJjang.receiveCard(ACE_HEART); // score: 21 (stand)
        irene.receiveCard(THREE_SPADE); // score: 23 (bust)

        WinningResult winningResult = new WinningResult(participants);
        System.out.println(bets.calculateBets(winningResult));
    }
}
