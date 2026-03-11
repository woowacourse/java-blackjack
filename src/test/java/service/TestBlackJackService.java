package service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.stream.Stream;
import model.BettingMoney;
import model.BlackJackDeck;
import model.CardFactory;
import model.CardNumber;
import model.Dealer;
import model.Player;
import model.PlayerName;
import model.Players;
import model.Shape;
import dto.Card;
import dto.ParticipantWinning;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestBlackJackService {

    @Test
    public void 카드_뽑기_정상_작동() {
        BlackJackDeck cards = new BlackJackDeck(CardFactory.createShuffledCards());
        BlackJackService blackJackService = new BlackJackService(cards);
        Player player = new Player(new PlayerName("player1"), new BettingMoney("10000"));
        blackJackService.draw(player);
        assertThat(player.getResult().score()).isGreaterThan(0);
        assertThat(player.getResult().hand().size()).isEqualTo(1);
    }

    static Stream<Arguments> bustCases() {
        return Stream.of(
                Arguments.of(List.of(
                        new Card(Shape.CLOVER, CardNumber.SEVEN),
                        new Card(Shape.HEART, CardNumber.SEVEN),
                        new Card(Shape.SPADE, CardNumber.SEVEN)
                ), false),
                Arguments.of(List.of(
                        new Card(Shape.CLOVER, CardNumber.TEN),
                        new Card(Shape.HEART, CardNumber.TEN),
                        new Card(Shape.SPADE, CardNumber.TWO)
                ), true)
        );
    }

    @ParameterizedTest
    @MethodSource("bustCases")
    public void 버스트_판정(List<Card> cards, boolean expected) {
        BlackJackService service = new BlackJackService(new BlackJackDeck(CardFactory.createShuffledCards()));
        Player player = new Player(new PlayerName("player"), new BettingMoney("10000"));
        cards.forEach(player::draw);
        assertThat(service.isBust(player)).isEqualTo(expected);
    }

    static Stream<Arguments> isBlackJackCases() {
        return Stream.of(
                Arguments.of(List.of(
                        new Card(Shape.CLOVER, CardNumber.ACE),
                        new Card(Shape.CLOVER, CardNumber.KING)
                ), true),
                Arguments.of(List.of(
                        new Card(Shape.CLOVER, CardNumber.SEVEN),
                        new Card(Shape.HEART, CardNumber.SEVEN),
                        new Card(Shape.SPADE, CardNumber.SEVEN)
                ), false),
                Arguments.of(List.of(
                        new Card(Shape.CLOVER, CardNumber.NINE),
                        new Card(Shape.CLOVER, CardNumber.KING)
                ), false),
                Arguments.of(List.of(
                        new Card(Shape.CLOVER, CardNumber.ACE),
                        new Card(Shape.HEART, CardNumber.FIVE),
                        new Card(Shape.SPADE, CardNumber.FIVE)
                ), false)
        );
    }

    @ParameterizedTest
    @MethodSource("isBlackJackCases")
    public void 블랙잭_판정(List<Card> cards, boolean expected) {
        BlackJackService service = new BlackJackService(new BlackJackDeck(CardFactory.createShuffledCards()));
        Player player = new Player(new PlayerName("player1"), new BettingMoney("10000"));
        cards.forEach(player::draw);
        assertThat(service.isBlackJack(player)).isEqualTo(expected);
    }

    static Stream<Arguments> scoreMatchCases() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(Shape.CLOVER, CardNumber.TEN)),
                        List.of(new Card(Shape.HEART, CardNumber.TEN)),
                        0
                ),
                Arguments.of(
                        List.of(new Card(Shape.CLOVER, CardNumber.JACK),
                                new Card(Shape.HEART, CardNumber.ACE)),
                        List.of(new Card(Shape.HEART, CardNumber.TEN)),
                        15000
                ),
                Arguments.of(
                        List.of(new Card(Shape.CLOVER, CardNumber.NINE),
                                new Card(Shape.HEART, CardNumber.FIVE)),
                        List.of(new Card(Shape.HEART, CardNumber.TEN)),
                        10000
                ),
                Arguments.of(
                        List.of(new Card(Shape.CLOVER, CardNumber.FIVE)),
                        List.of(new Card(Shape.HEART, CardNumber.TEN)),
                        -10000
                ),
                Arguments.of(
                        List.of(new Card(Shape.CLOVER, CardNumber.TEN),
                                new Card(Shape.HEART, CardNumber.TEN),
                                new Card(Shape.SPADE, CardNumber.TWO)),
                        List.of(new Card(Shape.HEART, CardNumber.ACE)),
                        -10000
                ),
                Arguments.of(
                        List.of(new Card(Shape.CLOVER, CardNumber.TWO)),
                        List.of(new Card(Shape.HEART, CardNumber.TEN),
                                new Card(Shape.SPADE, CardNumber.TEN),
                                new Card(Shape.CLOVER, CardNumber.TWO)),
                        10000
                ),
                Arguments.of(
                        List.of(new Card(Shape.CLOVER, CardNumber.TEN),
                                new Card(Shape.HEART, CardNumber.TEN),
                                new Card(Shape.SPADE, CardNumber.TWO)),
                        List.of(new Card(Shape.HEART, CardNumber.NINE),
                                new Card(Shape.SPADE, CardNumber.NINE),
                                new Card(Shape.CLOVER, CardNumber.FOUR)),
                        -10000
                )
        );
    }

    @ParameterizedTest
    @MethodSource("scoreMatchCases")
    public void 승패무_판정(List<Card> playerCards, List<Card> dealerCards, int expectedProfit) {
        BlackJackService service = new BlackJackService(new BlackJackDeck(CardFactory.createShuffledCards()));
        Player player = new Player(new PlayerName("player1"), new BettingMoney("10000"));
        playerCards.forEach(player::draw);
        Dealer dealer = new Dealer();
        dealerCards.forEach(dealer::draw);
        ParticipantWinning result = service.getGameResult(new Players(List.of(player)), dealer);
        assertThat(result.playersWinning().getFirst().profit()).isEqualTo(expectedProfit);
    }

    @Test
    public void 플레이어_블랙잭_딜러_블랙잭_무() {
        BlackJackService service = new BlackJackService(new BlackJackDeck(CardFactory.createShuffledCards()));
        Player player = new Player(new PlayerName("player1"), new BettingMoney("10000"));
        player.draw(new Card(Shape.HEART, CardNumber.ACE));
        player.draw(new Card(Shape.HEART, CardNumber.KING));
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Shape.SPADE, CardNumber.ACE));
        dealer.draw(new Card(Shape.SPADE, CardNumber.KING));
        ParticipantWinning result = service.getGameResult(new Players(List.of(player)), dealer);
        assertThat(result.playersWinning().getFirst().profit()).isEqualTo(0);
    }

    @Test
    public void 플레이어_블랙잭_딜러_일반_블랙잭승() {
        BlackJackService service = new BlackJackService(new BlackJackDeck(CardFactory.createShuffledCards()));
        Player player = new Player(new PlayerName("player1"), new BettingMoney("10000"));
        player.draw(new Card(Shape.HEART, CardNumber.ACE));
        player.draw(new Card(Shape.HEART, CardNumber.KING));
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Shape.CLOVER, CardNumber.TEN));
        dealer.draw(new Card(Shape.CLOVER, CardNumber.QUEEN));
        ParticipantWinning result = service.getGameResult(new Players(List.of(player)), dealer);
        assertThat(result.playersWinning().getFirst().profit()).isEqualTo(15000);
    }

    @Test
    public void 딜러_블랙잭_플레이어_일반_패() {
        BlackJackService service = new BlackJackService(new BlackJackDeck(CardFactory.createShuffledCards()));
        Player player = new Player(new PlayerName("player1"), new BettingMoney("10000"));
        player.draw(new Card(Shape.CLOVER, CardNumber.TEN));
        player.draw(new Card(Shape.HEART, CardNumber.QUEEN));
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Shape.SPADE, CardNumber.ACE));
        dealer.draw(new Card(Shape.SPADE, CardNumber.KING));
        ParticipantWinning result = service.getGameResult(new Players(List.of(player)), dealer);
        assertThat(result.playersWinning().getFirst().profit()).isEqualTo(-10000);
    }

    @Test
    public void 딜러_블랙잭_플레이어_버스트_패() {
        BlackJackService service = new BlackJackService(new BlackJackDeck(CardFactory.createShuffledCards()));
        Player player = new Player(new PlayerName("player1"), new BettingMoney("10000"));
        player.draw(new Card(Shape.CLOVER, CardNumber.TEN));
        player.draw(new Card(Shape.HEART, CardNumber.TEN));
        player.draw(new Card(Shape.DIAMOND, CardNumber.TWO));
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Shape.SPADE, CardNumber.ACE));
        dealer.draw(new Card(Shape.SPADE, CardNumber.KING));
        ParticipantWinning result = service.getGameResult(new Players(List.of(player)), dealer);
        assertThat(result.playersWinning().getFirst().profit()).isEqualTo(-10000);
    }

    @Test
    public void ACE_소프트_21_블랙잭_아님_일반승() {
        BlackJackService service = new BlackJackService(new BlackJackDeck(CardFactory.createShuffledCards()));
        Player player = new Player(new PlayerName("player1"), new BettingMoney("10000"));
        player.draw(new Card(Shape.HEART, CardNumber.ACE));
        player.draw(new Card(Shape.HEART, CardNumber.FIVE));
        player.draw(new Card(Shape.SPADE, CardNumber.FIVE));
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Shape.CLOVER, CardNumber.TEN));
        dealer.draw(new Card(Shape.CLOVER, CardNumber.NINE));
        ParticipantWinning result = service.getGameResult(new Players(List.of(player)), dealer);
        assertThat(result.playersWinning().getFirst().profit()).isEqualTo(10000);
    }

    @Test
    public void ACE_하드_버스트_방지() {
        BlackJackService service = new BlackJackService(new BlackJackDeck(CardFactory.createShuffledCards()));
        Player player = new Player(new PlayerName("player1"), new BettingMoney("10000"));
        player.draw(new Card(Shape.HEART, CardNumber.ACE));
        player.draw(new Card(Shape.HEART, CardNumber.TEN));
        player.draw(new Card(Shape.SPADE, CardNumber.KING));
        assertThat(service.isBust(player)).isFalse();
    }

    @Test
    public void 딜러_수익은_플레이어_수익의_합산_반대() {
        BlackJackService service = new BlackJackService(new BlackJackDeck(CardFactory.createShuffledCards()));

        Player player1 = new Player(new PlayerName("player1"), new BettingMoney("10000"));
        player1.draw(new Card(Shape.CLOVER, CardNumber.TEN));
        player1.draw(new Card(Shape.HEART, CardNumber.NINE));

        Player player2 = new Player(new PlayerName("player2"), new BettingMoney("5000"));
        player2.draw(new Card(Shape.SPADE, CardNumber.FIVE));

        Dealer dealer = new Dealer();
        dealer.draw(new Card(Shape.DIAMOND, CardNumber.TEN));
        dealer.draw(new Card(Shape.DIAMOND, CardNumber.EIGHT));

        ParticipantWinning result = service.getGameResult(new Players(List.of(player1, player2)), dealer);
        assertThat(result.dealerProfit()).isEqualTo(-5000);
    }
}
