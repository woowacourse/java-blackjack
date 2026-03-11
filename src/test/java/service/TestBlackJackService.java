package service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import model.BlackJackDeck;
import model.CardNumber;
import model.Dealer;
import model.Player;
import model.PlayerName;
import model.Players;
import model.Shape;
import model.dto.Card;
import model.dto.ParticipantWinning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBlackJackService {
    private BlackJackService blackJackService;

    private Player player;
    private Dealer dealer;


    @BeforeEach
    public void setUp() {
        BlackJackDeck cards = new BlackJackDeck();
        blackJackService = new BlackJackService(cards);

        player = new Player(new PlayerName("player"));
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
    public void 둘_다_버스트가_아니면서_점수가_높은_경우_정상_작동() {
        dealer.addCard(new Card(Shape.CLOVER, CardNumber.KING));
        dealer.addCard(new Card(Shape.CLOVER, CardNumber.EIGHT));

        player.addCard(new Card(Shape.CLOVER, CardNumber.KING));

        ParticipantWinning winning = blackJackService.getGameResult(new Players(List.of(player)), dealer);

        List<String> dealerWinning = winning.dealerWinning().getFormattedDealerWinning();

        assertThat(dealerWinning.getFirst()).isEqualTo("1승");
        assertThat(dealerWinning.size()).isEqualTo(1);
    }

    @Test
    public void 둘_다_버스트가_아니면서_점수가_같은_경우_정상_작동() {
        dealer.addCard(new Card(Shape.CLOVER, CardNumber.KING));
        dealer.addCard(new Card(Shape.DIAMOND, CardNumber.KING));

        player.addCard(new Card(Shape.CLOVER, CardNumber.KING));
        player.addCard(new Card(Shape.DIAMOND, CardNumber.KING));

        ParticipantWinning winning = blackJackService.getGameResult(new Players(List.of(player)), dealer);

        List<String> dealerWinning = winning.dealerWinning().getFormattedDealerWinning();

        assertThat(dealerWinning.getFirst()).isEqualTo("1무");
        assertThat(dealerWinning.size()).isEqualTo(1);
    }

    @Test
    public void 둘_다_버스트가_아니면서_점수가_낮은_경우_정상_작동() {
        dealer.addCard(new Card(Shape.CLOVER, CardNumber.KING));
        dealer.addCard(new Card(Shape.CLOVER, CardNumber.EIGHT));

        player.addCard(new Card(Shape.CLOVER, CardNumber.KING));
        player.addCard(new Card(Shape.DIAMOND, CardNumber.KING));

        ParticipantWinning winning = blackJackService.getGameResult(new Players(List.of(player)), dealer);

        List<String> dealerWinning = winning.dealerWinning().getFormattedDealerWinning();

        assertThat(dealerWinning.getFirst()).isEqualTo("1패");
        assertThat(dealerWinning.size()).isEqualTo(1);
    }

    @Test
    public void 플레이어만_버스트인_경우_정상_작동() {
        dealer.addCard(new Card(Shape.CLOVER, CardNumber.KING));
        dealer.addCard(new Card(Shape.CLOVER, CardNumber.EIGHT));

        player.addCard(new Card(Shape.CLOVER, CardNumber.KING));
        player.addCard(new Card(Shape.DIAMOND, CardNumber.KING));
        player.addCard(new Card(Shape.HEART, CardNumber.KING));

        ParticipantWinning winning = blackJackService.getGameResult(new Players(List.of(player)), dealer);

        List<String> dealerWinning = winning.dealerWinning().getFormattedDealerWinning();

        assertThat(dealerWinning.getFirst()).isEqualTo("1승");
        assertThat(dealerWinning.size()).isEqualTo(1);
    }

    @Test
    public void 딜러만_버스트인_경우_정상_작동() {
        dealer.addCard(new Card(Shape.CLOVER, CardNumber.KING));
        dealer.addCard(new Card(Shape.DIAMOND, CardNumber.KING));
        dealer.addCard(new Card(Shape.HEART, CardNumber.KING));

        player.addCard(new Card(Shape.CLOVER, CardNumber.KING));
        player.addCard(new Card(Shape.CLOVER, CardNumber.EIGHT));

        ParticipantWinning winning = blackJackService.getGameResult(new Players(List.of(player)), dealer);

        List<String> dealerWinning = winning.dealerWinning().getFormattedDealerWinning();

        assertThat(dealerWinning.getFirst()).isEqualTo("1패");
        assertThat(dealerWinning.size()).isEqualTo(1);
    }

    @Test
    public void 둘_다_버스트인_경우_정상_작동() {
        dealer.addCard(new Card(Shape.CLOVER, CardNumber.KING));
        dealer.addCard(new Card(Shape.DIAMOND, CardNumber.KING));
        dealer.addCard(new Card(Shape.HEART, CardNumber.KING));

        player.addCard(new Card(Shape.CLOVER, CardNumber.KING));
        player.addCard(new Card(Shape.DIAMOND, CardNumber.KING));
        player.addCard(new Card(Shape.HEART, CardNumber.KING));

        ParticipantWinning winning = blackJackService.getGameResult(new Players(List.of(player)), dealer);

        List<String> dealerWinning = winning.dealerWinning().getFormattedDealerWinning();

        assertThat(dealerWinning.getFirst()).isEqualTo("1승");
        assertThat(dealerWinning.size()).isEqualTo(1);
    }
}
