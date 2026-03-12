package service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import model.BlackJackDeck;
import model.CardNumber;
import model.Dealer;
import model.Participant;
import model.Player;
import model.PlayerName;
import model.Players;
import model.Shape;
import model.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlackJackServiceTest {
    private static final Integer INITIAL_BET = 10000;
    private BlackJackService blackJackService;

    private Player player;
    private Dealer dealer;


    @BeforeEach
    public void setUp() {
        BlackJackDeck cards = new BlackJackDeck();
        blackJackService = new BlackJackService(cards);

        player = new Player(new PlayerName("player"));
        player.setBetAmount(INITIAL_BET);
        dealer = new Dealer();
    }

    @Test
    public void 카드_뽑기_정상_작동() {
        BlackJackDeck cards = new BlackJackDeck();
        BlackJackService blackJackService = new BlackJackService(cards);

        Player player = new Player(new PlayerName("player1"));

        blackJackService.draw(player);

        assertThat(player.getResult().score()).isGreaterThan(0);
        assertThat(player.getResult().deck().size()).isEqualTo(1);
    }

    @Test
    public void 플레이어가_버스트인_경우_정상_작동() {
        participantBust(player);
        Integer profit1 = blackJackService.getGameResult(new Players(List.of(player)), dealer).dealerWinning().getTotalBet();

        assertThat(profit1).isEqualTo(0);

        participantBust(dealer);

        Integer profit2 = blackJackService.getGameResult(new Players(List.of(player)), dealer).dealerWinning().getTotalBet();
        assertThat(profit2).isEqualTo(0);
    }

    @Test
    public void 플레이어만_블랙잭인_경우_정상_작동() {
        participantBlackJack(player);
        Integer profit = blackJackService.getGameResult(new Players(List.of(player)), dealer).dealerWinning().getTotalBet();

        assertThat(profit).isEqualTo(-15000);
    }

    @Test
    public void 둘_다_블랙잭인_경우_정상_작동() {
        participantBlackJack(player);
        participantBlackJack(dealer);

        Integer profit = blackJackService.getGameResult(new Players(List.of(player)), dealer).dealerWinning().getTotalBet();

        assertThat(profit).isEqualTo(0);
    }

    @Test
    public void 둘_다_일반_점수면서_플레이어_점수가_낮은_경우_정상_작동() {
        dealer.addCard(new Card(Shape.DIAMOND, CardNumber.QUEEN));
        player.addCard(new Card(Shape.DIAMOND, CardNumber.TWO));

        Integer profit = blackJackService.getGameResult(new Players(List.of(player)), dealer).dealerWinning().getTotalBet();

        assertThat(profit).isEqualTo(INITIAL_BET);
    }

    @Test
    public void 딜러가_버스트이면서_플레이어가_일반_점수인_경우() {
        participantBust(dealer);

        Integer profit = blackJackService.getGameResult(new Players(List.of(player)), dealer).dealerWinning().getTotalBet();

        assertThat(profit).isEqualTo(-INITIAL_BET);
    }

    @Test public void 둘_다_버스트인_경우_정상_작동() {
        participantBust(player);
        participantBust(dealer);

        Integer profit = blackJackService.getGameResult(new Players(List.of(player)), dealer).dealerWinning().getTotalBet();

        assertThat(profit).isEqualTo(0);
    }

    @Test
    public void 둘_다_일반_점수면서_플레이어_점수가_높은_경우_정상_작동() {
        dealer.addCard(new Card(Shape.DIAMOND, CardNumber.QUEEN));
        player.addCard(new Card(Shape.DIAMOND, CardNumber.ACE));

        Integer profit = blackJackService.getGameResult(new Players(List.of(player)), dealer).dealerWinning().getTotalBet();

        assertThat(profit).isEqualTo(-INITIAL_BET);
    }

    @Test
    public void 둘_다_일반_점수면서_점수가_같은_경우_정상_작동() {
        dealer.addCard(new Card(Shape.DIAMOND, CardNumber.NINE));
        dealer.addCard(new Card(Shape.HEART, CardNumber.NINE));

        player.addCard(new Card(Shape.DIAMOND, CardNumber.QUEEN));
        player.addCard(new Card(Shape.DIAMOND, CardNumber.EIGHT));

        Integer profit = blackJackService.getGameResult(new Players(List.of(player)), dealer).dealerWinning().getTotalBet();

        assertThat(profit).isEqualTo(0);
    }

    private void participantBust(Participant participant) {
        participant.addCard(new Card(Shape.DIAMOND, CardNumber.KING));
        participant.addCard(new Card(Shape.DIAMOND, CardNumber.QUEEN));
        participant.addCard(new Card(Shape.DIAMOND, CardNumber.JACK));
    }

    private void participantBlackJack(Participant participant) {
        participant.addCard(new Card(Shape.DIAMOND, CardNumber.KING));
        participant.addCard(new Card(Shape.DIAMOND, CardNumber.ACE));
    }
}
