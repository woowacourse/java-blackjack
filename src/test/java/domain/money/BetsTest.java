package domain.money;

import static domain.Fixtures.ACE_CLOVER;
import static domain.Fixtures.ACE_HEART;
import static domain.Fixtures.FOUR_HEART;
import static domain.Fixtures.KING_CLOVER;
import static domain.Fixtures.KING_SPADE;
import static domain.Fixtures.NINE_DIAMOND;
import static domain.Fixtures.QUEEN_SPADE;
import static domain.Fixtures.SEVEN_HEART;
import static domain.Fixtures.TEN_CLOVER;
import static domain.Fixtures.THREE_SPADE;
import static domain.Fixtures.TWO_SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import domain.result.WinningResult;
import domain.shuffler.FixedCardsShuffler;
import java.util.List;
import java.util.Map;
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
    void standBustTest() {
        Participants participants = new Participants(List.of("깃짱", "이리내"));
        participants.distributeInitialCards(new CardDeck(new FixedCardsShuffler()));

        Player gitJjang = participants.getPlayers().get(0); // score: 20
        Player irene = participants.getPlayers().get(1); // score: 20
        Dealer dealer = participants.getDealer(); // score: 20

        Bets bets = new Bets();
        bets.addBet(gitJjang, new Money(1000));
        bets.addBet(irene, new Money(10000));

        gitJjang.receiveCard(ACE_HEART); // score: 21 (stand)
        irene.receiveCard(THREE_SPADE); // score: 23 (bust)

        WinningResult winningResult = new WinningResult(participants);
        Map<Player, Money> weightedBets = bets.calculatePlayersProfit(winningResult);

        assertAll(
                () -> assertThat(weightedBets.get(gitJjang)).isEqualTo(new Money(1000)),
                () -> assertThat(weightedBets.get(irene)).isEqualTo(new Money(-10000)),
                () -> assertThat(bets.calculateDealerProfit(winningResult)).isEqualTo(new Money(9000))
        );
    }

    @Test
    void blackJackDrawTest() {
        Participants participants = new Participants(List.of("깃짱", "이리내"));
        participants.distributeInitialCards(
                new CardDeck(List.of(KING_CLOVER, QUEEN_SPADE, SEVEN_HEART, NINE_DIAMOND, ACE_HEART, KING_SPADE),
                        new FixedCardsShuffler()));

        Player gitJjang = participants.getPlayers().get(0); // score: 21 (BlackJack)
        Player irene = participants.getPlayers().get(1); // score: 16
        Dealer dealer = participants.getDealer(); // score: 20

        Bets bets = new Bets();
        bets.addBet(gitJjang, new Money(1000));
        bets.addBet(irene, new Money(10000));

        irene.receiveCard(FOUR_HEART); // score: 20 (hit) => draw
        irene.selectStand();

        WinningResult winningResult = new WinningResult(participants);
        Map<Player, Money> weightedBets = bets.calculatePlayersProfit(winningResult);

        assertAll(
                () -> assertThat(weightedBets.get(gitJjang)).isEqualTo(new Money(1500)),
                () -> assertThat(weightedBets.get(irene)).isEqualTo(new Money(0)),
                () -> assertThat(bets.calculateDealerProfit(winningResult)).isEqualTo(new Money(-1500))
        );
    }


    @Test
    void dealerBustTest() {
        Participants participants = new Participants(List.of("깃짱", "이리내"));
        participants.distributeInitialCards(
                new CardDeck(List.of(KING_CLOVER, THREE_SPADE, SEVEN_HEART, NINE_DIAMOND, ACE_HEART, KING_SPADE),
                        new FixedCardsShuffler()));

        Player gitJjang = participants.getPlayers().get(0); // score: 21 (BlackJack)
        Player irene = participants.getPlayers().get(1); // score: 16
        Dealer dealer = participants.getDealer(); // score: 13

        Bets bets = new Bets();
        bets.addBet(gitJjang, new Money(1000));
        bets.addBet(irene, new Money(10000));

        dealer.receiveCard(TEN_CLOVER);
        irene.selectStand();

        WinningResult winningResult = new WinningResult(participants);
        Map<Player, Money> weightedBets = bets.calculatePlayersProfit(winningResult);

        assertAll(
                () -> assertThat(weightedBets.get(gitJjang)).isEqualTo(new Money(1500)),
                () -> assertThat(weightedBets.get(irene)).isEqualTo(new Money(10000)),
                () -> assertThat(bets.calculateDealerProfit(winningResult)).isEqualTo(new Money(-11500))
        );
    }

    @Test
    void dealerBlackJackTest() {
        Participants participants = new Participants(List.of("깃짱", "이리내"));
        participants.distributeInitialCards(
                new CardDeck(List.of(KING_CLOVER, ACE_CLOVER, SEVEN_HEART, NINE_DIAMOND, ACE_HEART, KING_SPADE),
                        new FixedCardsShuffler()));

        Player gitJjang = participants.getPlayers().get(0); // score: 21 (BlackJack)
        Player irene = participants.getPlayers().get(1); // score: 16
        Dealer dealer = participants.getDealer(); // score: 21 (BlackJack)

        Bets bets = new Bets();
        bets.addBet(gitJjang, new Money(1000));
        bets.addBet(irene, new Money(10000));

        irene.selectStand();

        WinningResult winningResult = new WinningResult(participants);
        Map<Player, Money> weightedBets = bets.calculatePlayersProfit(winningResult);

        assertAll(
                () -> assertThat(weightedBets.get(gitJjang)).isEqualTo(new Money(0)),
                () -> assertThat(weightedBets.get(irene)).isEqualTo(new Money(-10000)),
                () -> assertThat(bets.calculateDealerProfit(winningResult)).isEqualTo(new Money(10000))
        );
    }

    @Test
    void winLoseTest() {
        Participants participants = new Participants(List.of("깃짱", "이리내"));
        participants.distributeInitialCards(
                new CardDeck(List.of(KING_CLOVER, TWO_SPADE, SEVEN_HEART, NINE_DIAMOND, FOUR_HEART, THREE_SPADE),
                        new FixedCardsShuffler()));

        Player gitJjang = participants.getPlayers().get(0); // score: 7
        Player irene = participants.getPlayers().get(1); // score: 16
        Dealer dealer = participants.getDealer(); // score: 12

        Bets bets = new Bets();
        bets.addBet(gitJjang, new Money(1000));
        bets.addBet(irene, new Money(10000));

        dealer.receiveCard(FOUR_HEART);
        gitJjang.selectStand();
        irene.receiveCard(ACE_HEART);
        irene.selectStand();

        WinningResult winningResult = new WinningResult(participants);
        Map<Player, Money> weightedBets = bets.calculatePlayersProfit(winningResult);

        assertAll(
                () -> assertThat(weightedBets.get(gitJjang)).isEqualTo(new Money(-1000)),
                () -> assertThat(weightedBets.get(irene)).isEqualTo(new Money(10000)),
                () -> assertThat(bets.calculateDealerProfit(winningResult)).isEqualTo(new Money(-9000))
        );
    }
}
