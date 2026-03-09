package service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.stream.Stream;
import model.BlackJackDeck;
import model.CardDeckFactory;
import model.CardNumber;
import model.Dealer;
import constant.MatchStatus;
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
        BlackJackDeck cards = new BlackJackDeck(CardDeckFactory.createShuffledDeck());
        BlackJackService blackJackService = new BlackJackService(cards);
        Player player = new Player(new PlayerName("player1"));
        blackJackService.draw(player);
        assertThat(player.getResult().score()).isGreaterThan(0);
        assertThat(player.getResult().hand().size()).isEqualTo(1);
    }

    static Stream<Arguments> bustCases() {
        return Stream.of(
            Arguments.of(List.of(
                new Card(Shape.CLOVER, CardNumber.SEVEN),
                new Card(Shape.HEART, CardNumber.SEVEN),
                new Card(Shape.SPADE, CardNumber.SEVEN)   // 7+7+7=21, 버스트 아님
            ), false),
            Arguments.of(List.of(
                new Card(Shape.CLOVER, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.TEN),
                new Card(Shape.SPADE, CardNumber.TWO)     // 10+10+2=22, 버스트
            ), true)
        );
    }

    @ParameterizedTest
    @MethodSource("bustCases")
    public void 버스트_판정(List<Card> cards, boolean expected) {
        BlackJackService service = new BlackJackService(new BlackJackDeck(CardDeckFactory.createShuffledDeck()));
        Player player = new Player(new PlayerName("player"));
        cards.forEach(player::draw);
        assertThat(service.isBust(player)).isEqualTo(expected);
    }

    static Stream<Arguments> isBlackJackCases() {
        return Stream.of(
            Arguments.of(List.of(
                new Card(Shape.CLOVER, CardNumber.ACE),
                new Card(Shape.CLOVER, CardNumber.KING)   // ACE+KING=21, 2장 → 블랙잭
            ), true),
            Arguments.of(List.of(
                new Card(Shape.CLOVER, CardNumber.SEVEN),
                new Card(Shape.HEART, CardNumber.SEVEN),
                new Card(Shape.SPADE, CardNumber.SEVEN)   // 7+7+7=21, 3장 → 블랙잭 아님
            ), false),
            Arguments.of(List.of(
                new Card(Shape.CLOVER, CardNumber.NINE),
                new Card(Shape.CLOVER, CardNumber.KING)   // 19점, 2장 → 블랙잭 아님
            ), false)
        );
    }

    @ParameterizedTest
    @MethodSource("isBlackJackCases")
    public void 블랙잭_판정(List<Card> cards, boolean expected) {
        BlackJackService service = new BlackJackService(new BlackJackDeck(CardDeckFactory.createShuffledDeck()));
        Player player = new Player(new PlayerName("player1"));
        cards.forEach(player::draw);
        assertThat(service.isBlackJack(player)).isEqualTo(expected);
    }

    static Stream<Arguments> scoreMatchCases() {
        return Stream.of(
            Arguments.of(
                List.of(new Card(Shape.CLOVER, CardNumber.TEN)),
                List.of(new Card(Shape.HEART, CardNumber.TEN)),
                MatchStatus.DRAW   // 10 vs 10 → 무
            ),
            Arguments.of(
                List.of(new Card(Shape.CLOVER, CardNumber.JACK),
                        new Card(Shape.HEART, CardNumber.ACE)),
                List.of(new Card(Shape.HEART, CardNumber.TEN)),
                MatchStatus.WIN    // 21 vs 10 → 승
            ),
            Arguments.of(
                List.of(new Card(Shape.CLOVER, CardNumber.FIVE)),
                List.of(new Card(Shape.HEART, CardNumber.TEN)),
                MatchStatus.LOSE   // 5 vs 10 → 패
            ),
            Arguments.of(
                List.of(new Card(Shape.CLOVER, CardNumber.TEN),
                        new Card(Shape.HEART, CardNumber.TEN),
                        new Card(Shape.SPADE, CardNumber.TWO)),
                List.of(new Card(Shape.HEART, CardNumber.ACE)),
                MatchStatus.LOSE   // 버스트(22) vs 1 → 패
            ),
            Arguments.of(
                List.of(new Card(Shape.CLOVER, CardNumber.TWO)),
                List.of(new Card(Shape.HEART, CardNumber.TEN),
                        new Card(Shape.SPADE, CardNumber.TEN),
                        new Card(Shape.CLOVER, CardNumber.TWO)),
                MatchStatus.WIN    // 2 vs 버스트(22) → 승
            )
        );
    }

    @ParameterizedTest
    @MethodSource("scoreMatchCases")
    public void 승패무_판정(List<Card> playerCards, List<Card> dealerCards, MatchStatus expected) {
        BlackJackService service = new BlackJackService(new BlackJackDeck(CardDeckFactory.createShuffledDeck()));
        Player player = new Player(new PlayerName("player1"));
        playerCards.forEach(player::draw);
        Dealer dealer = new Dealer();
        dealerCards.forEach(dealer::draw);
        ParticipantWinning result = service.getGameResult(new Players(List.of(player)), dealer);
        assertThat(result.playersWinning().getFirst().matchStatus()).isEqualTo(expected);
    }

    @Test
    public void 플레이어_블랙잭_딜러_블랙잭_무() {
        BlackJackService service = new BlackJackService(new BlackJackDeck(CardDeckFactory.createShuffledDeck()));
        Player player = new Player(new PlayerName("player1"));
        player.draw(new Card(Shape.HEART, CardNumber.ACE));
        player.draw(new Card(Shape.HEART, CardNumber.KING));
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Shape.SPADE, CardNumber.ACE));
        dealer.draw(new Card(Shape.SPADE, CardNumber.KING));
        ParticipantWinning result = service.getGameResult(new Players(List.of(player)), dealer);
        assertThat(result.playersWinning().getFirst().matchStatus()).isEqualTo(MatchStatus.DRAW);
        assertThat(result.dealerWinning().get(MatchStatus.DRAW)).isEqualTo(1);
    }

    @Test
    public void 플레이어_블랙잭_딜러_일반_승() {
        BlackJackService service = new BlackJackService(new BlackJackDeck(CardDeckFactory.createShuffledDeck()));
        Player player = new Player(new PlayerName("player1"));
        player.draw(new Card(Shape.HEART, CardNumber.ACE));
        player.draw(new Card(Shape.HEART, CardNumber.KING));
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Shape.CLOVER, CardNumber.TEN));
        dealer.draw(new Card(Shape.CLOVER, CardNumber.QUEEN));
        ParticipantWinning result = service.getGameResult(new Players(List.of(player)), dealer);
        assertThat(result.playersWinning().getFirst().matchStatus()).isEqualTo(MatchStatus.WIN);
        assertThat(result.dealerWinning().get(MatchStatus.LOSE)).isEqualTo(1);
    }

    @Test
    public void 딜러_블랙잭_플레이어_일반_패() {
        BlackJackService service = new BlackJackService(new BlackJackDeck(CardDeckFactory.createShuffledDeck()));
        Player player = new Player(new PlayerName("player1"));
        player.draw(new Card(Shape.CLOVER, CardNumber.TEN));
        player.draw(new Card(Shape.HEART, CardNumber.QUEEN));
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Shape.SPADE, CardNumber.ACE));
        dealer.draw(new Card(Shape.SPADE, CardNumber.KING));
        ParticipantWinning result = service.getGameResult(new Players(List.of(player)), dealer);
        assertThat(result.playersWinning().getFirst().matchStatus()).isEqualTo(MatchStatus.LOSE);
        assertThat(result.dealerWinning().get(MatchStatus.WIN)).isEqualTo(1);
    }

    @Test
    public void 딜러_블랙잭_플레이어_버스트_패() {
        BlackJackService service = new BlackJackService(new BlackJackDeck(CardDeckFactory.createShuffledDeck()));
        Player player = new Player(new PlayerName("player1"));
        player.draw(new Card(Shape.CLOVER, CardNumber.TEN));
        player.draw(new Card(Shape.HEART, CardNumber.TEN));
        player.draw(new Card(Shape.DIAMOND, CardNumber.TWO));
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Shape.SPADE, CardNumber.ACE));
        dealer.draw(new Card(Shape.SPADE, CardNumber.KING));
        ParticipantWinning result = service.getGameResult(new Players(List.of(player)), dealer);
        assertThat(result.playersWinning().getFirst().matchStatus()).isEqualTo(MatchStatus.LOSE);
        assertThat(result.dealerWinning().get(MatchStatus.WIN)).isEqualTo(1);
    }
}
