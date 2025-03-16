package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.TestFixtures;
import domain.card.Card;
import domain.card.CardHand;
import domain.card.CardPack;
import domain.participant.Dealer;
import domain.participant.Gambler;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class GamblersTest {

    private Dealer dealer;
    private Player player1;
    private Player player2;
    private Player player3;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(new CardHand());
        player1 = TestFixtures.playerOfDefaultHand();
        player2 = TestFixtures.playerOfDefaultHand();
        player3 = TestFixtures.playerOfDefaultHand();
    }

    @Test
    void 딜러와_플레이어_배팅_맵으로_Gamblers를_생성한다() {
        assertThatCode(() -> new Gamblers(dealer, Map.of(player1, GamblingMoney.bet(10000))))
            .doesNotThrowAnyException();
    }

    @Test
    void 플레이어가_없으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Gamblers(dealer, Map.of()))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어가_8명보다_많으면_예외가_발생한다() {
        GamblingMoney money = GamblingMoney.bet(10000);
        Map<Player, GamblingMoney> players = Map.of(
            TestFixtures.playerOfDefaultHand(), money,
            TestFixtures.playerOfDefaultHand(), money,
            TestFixtures.playerOfDefaultHand(), money,
            TestFixtures.playerOfDefaultHand(), money,
            TestFixtures.playerOfDefaultHand(), money,
            TestFixtures.playerOfDefaultHand(), money,
            TestFixtures.playerOfDefaultHand(), money,
            TestFixtures.playerOfDefaultHand(), money,
            TestFixtures.playerOfDefaultHand(), money
        );

        assertThatThrownBy(() -> new Gamblers(dealer, players))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 딜러와_플레이어들에게_초기카드를_나눠준다() {
        Gamblers gamblers = TestFixtures.gamblers(dealer, List.of(player1, player2));

        gamblers.distributeSetUpCards(CardPack.of(Card.allCards()));

        assertAll(
            () -> assertThat(dealer.getCards()).hasSize(2),
            () -> assertThat(player1.getCards()).hasSize(2),
            () -> assertThat(player2.getCards()).hasSize(2)
        );
    }

    @Test
    void 초기카드는_딜러먼저_2장_이후_플레이어들에게_순서대로_2장씩_나누어준다() {
        Gamblers gamblers = TestFixtures.gamblers(dealer, List.of(player1, player2, player3));

        List<Card> cards = List.of(
            Card.SPADE_A, Card.SPADE_2, Card.SPADE_3, Card.SPADE_4,
            Card.SPADE_5, Card.SPADE_6, Card.SPADE_7, Card.SPADE_8);
        gamblers.distributeSetUpCards(CardPack.of(cards));

        assertAll(
            () -> assertThat(dealer.getCards()).containsSequence(Card.SPADE_A, Card.SPADE_2),
            () -> assertThat(player1.getCards()).containsSequence(Card.SPADE_3, Card.SPADE_4),
            () -> assertThat(player2.getCards()).containsSequence(Card.SPADE_5, Card.SPADE_6),
            () -> assertThat(player3.getCards()).containsSequence(Card.SPADE_7, Card.SPADE_8)
        );
    }

    @Test
    void 플레이어들에게_순서대로_추가카드를_나누어준다() {
        Gamblers gamblers = TestFixtures.gamblers(dealer, List.of(player1, player2, player3));
        player1.takeCards(Card.DIAMOND_10, Card.DIAMOND_10);
        player2.takeCards(Card.DIAMOND_10, Card.DIAMOND_10);
        player3.takeCards(Card.DIAMOND_10, Card.DIAMOND_10);
        // 모든 참가자가 20점인 상황에서

        List<Card> cards = List.of(Card.SPADE_A, Card.SPADE_2, Card.SPADE_3);
        gamblers.distributeExtraCardsToPlayers(CardPack.of(cards),
            GamblerAnswersForTest.onlyOnceOKPerGambler());

        assertAll(
            () -> assertThat(player1.getCards()).contains(Card.SPADE_A),
            () -> assertThat(player2.getCards()).contains(Card.SPADE_2),
            () -> assertThat(player3.getCards()).contains(Card.SPADE_3)
        );
    }

    @Test
    void 딜러는_16점을_초과할_때까지_계속해서_카드를_받는다() {
        Gamblers gamblers = TestFixtures.gamblers(dealer, List.of(player1, player2, player3));
        dealer.takeCards(Card.CLOVER_2, Card.DIAMOND_2);
        // 딜러의 초기 카드의 점수가 2 + 2 = 4점인 상황에서

        List<Card> cardsToDistribute = List.of(Card.SPADE_2, Card.SPADE_3, Card.SPADE_4,
            Card.SPADE_5);
        gamblers.distributeExtraCardsToDealer(CardPack.of(cardsToDistribute),
            GamblerAnswersForTest.alwaysOK());

        assertThat(dealer.getCards())
            .containsSequence(Card.CLOVER_2, Card.DIAMOND_2, Card.SPADE_2, Card.SPADE_3,
                Card.SPADE_4, Card.SPADE_5);
    }

    @Test
    void Gambler가_카드를_더_받을_수_없으면_추가카드_배분_시_아무일도_일어나지_않는다() {
        Gamblers gamblers = TestFixtures.gamblers(dealer, List.of(player1));
        dealer.takeCards(Card.SPADE_J, Card.SPADE_7); // 16이하가 아니라서 못받음.
        player1.takeCards(Card.CLOVER_10, Card.HEART_10, Card.CLOVER_2); // 21을 넘어 버스트됨.

        CardPack cardPack = CardPack.of(List.of(Card.SPADE_A, Card.SPADE_2));

        //when
        gamblers.distributeExtraCardsToPlayers(cardPack, GamblerAnswersForTest.neverCalled());

        //then
        assertAll(
            () -> assertThat(dealer.getCards()).containsOnly(Card.SPADE_J, Card.SPADE_7),
            () -> assertThat(player1.getCards()).containsOnly(Card.CLOVER_10, Card.HEART_10,
                Card.CLOVER_2)
        );
    }

    @Test
    void 플레이어가_카드를_더_받을수_있지만_승낙하지_않으면_카드를_더_받지_않는다() {
        Gamblers gamblers = TestFixtures.gamblers(dealer, List.of(player1));
        player1.takeCards(Card.CLOVER_2, Card.CLOVER_3);

        CardPack cardPack = CardPack.of(List.of(Card.DIAMOND_J, Card.SPADE_J));
        gamblers.distributeExtraCardsToPlayers(cardPack, GamblerAnswersForTest.neverOK());

        assertThat(player1.getCards()).containsOnly(Card.CLOVER_2, Card.CLOVER_3);
    }

    @ParameterizedTest
    @CsvSource({
        "SPADE_A,SPADE_10,WIN",
        "SPADE_10,DIAMOND_10,DRAW",
        "SPADE_10,SPADE_9,LOSE"
    })
    void 딜러와_비교하여_플레이어의_승무패를_가린다(Card playerCard1, Card playerCard2, Winning expectedWinning) {
        Gamblers gamblers = TestFixtures.gamblers(dealer, List.of(player1));
        dealer.takeCards(Card.CLOVER_J, Card.CLOVER_K);
        player1.takeCards(playerCard1, playerCard2);

        Map<Player, Winning> playerWinnings = gamblers.evaluatePlayerWinnings();

        assertThat(playerWinnings.get(player1)).isEqualTo(expectedWinning);
    }

    @Test
    void 딜러의_승무패_횟수를_계산한다() {
        //given
        Player winnerPlayer1 = TestFixtures.playerOfDefaultHand();
        Player drawPlayer1 = TestFixtures.playerOfDefaultHand();
        Player drawPlayer2 = TestFixtures.playerOfDefaultHand();
        Player losePlayer1 = TestFixtures.playerOfDefaultHand();
        Player losePlayer2 = TestFixtures.playerOfDefaultHand();
        Player losePlayer3 = TestFixtures.playerOfDefaultHand();

        Gamblers gamblers = TestFixtures.gamblers(dealer,
            List.of(winnerPlayer1, drawPlayer1, drawPlayer2,
                losePlayer1, losePlayer2, losePlayer3), 10000);

        dealer.takeCards(Card.SPADE_10, Card.DIAMOND_10);

        winnerPlayer1.takeCards(Card.SPADE_A, Card.HEART_10); // 21
        drawPlayer1.takeCards(Card.CLOVER_10, Card.SPADE_J); // 20
        drawPlayer2.takeCards(Card.DIAMOND_J, Card.HEART_J); // 20
        losePlayer1.takeCards(Card.SPADE_K, Card.SPADE_9); // 19
        losePlayer2.takeCards(Card.DIAMOND_K, Card.DIAMOND_9); // 19
        losePlayer3.takeCards(Card.HEART_K, Card.HEART_9); // 19

        //when
        Map<Winning, Long> dealerWinnings = gamblers.evaluateDealerWinnings();

        var dealerWinCount = dealerWinnings.get(Winning.WIN);
        var dealerDrawCount = dealerWinnings.get(Winning.DRAW);
        var dealerLoseCount = dealerWinnings.get(Winning.LOSE);

        //then
        assertAll(
            () -> assertThat(dealerWinCount).isEqualTo(3),
            () -> assertThat(dealerDrawCount).isEqualTo(2),
            () -> assertThat(dealerLoseCount).isEqualTo(1)
        );
    }

    @Test
    void 최종_수익을_계산할_수_있다() {
        //given
        Player winnerPlayer1 = TestFixtures.playerOfDefaultHand();
        Player drawPlayer1 = TestFixtures.playerOfDefaultHand();
        Player drawPlayer2 = TestFixtures.playerOfDefaultHand();
        Player losePlayer1 = TestFixtures.playerOfDefaultHand();
        Player losePlayer2 = TestFixtures.playerOfDefaultHand();
        Player losePlayer3 = TestFixtures.playerOfDefaultHand();

        Gamblers gamblers = TestFixtures.gamblers(dealer,
            List.of(winnerPlayer1, drawPlayer1, drawPlayer2,
                losePlayer1, losePlayer2, losePlayer3),
            10000); //6명 플레이어 모두 1만원씩 배팅했을 때

        dealer.takeCards(Card.SPADE_10, Card.DIAMOND_10);

        winnerPlayer1.takeCards(Card.SPADE_10, Card.HEART_9, Card.HEART_2); // 21
        drawPlayer1.takeCards(Card.CLOVER_10, Card.SPADE_J); // 20
        drawPlayer2.takeCards(Card.DIAMOND_J, Card.HEART_J); // 20
        losePlayer1.takeCards(Card.SPADE_K, Card.SPADE_9); // 19
        losePlayer2.takeCards(Card.DIAMOND_K, Card.DIAMOND_9); // 19
        losePlayer3.takeCards(Card.HEART_K, Card.HEART_9); // 19

        //when
        Map<Gambler, Integer> moneys = gamblers.evaluateProfits();

        //then
        assertAll(
            () -> assertThat(moneys.get(dealer)).isEqualTo(20000),

            () -> assertThat(moneys.get(winnerPlayer1)).isEqualTo(10000),

            () -> assertThat(moneys.get(drawPlayer1)).isEqualTo(0),
            () -> assertThat(moneys.get(drawPlayer2)).isEqualTo(0),

            () -> assertThat(moneys.get(losePlayer1)).isEqualTo(-10000),
            () -> assertThat(moneys.get(losePlayer2)).isEqualTo(-10000),
            () -> assertThat(moneys.get(losePlayer3)).isEqualTo(-10000)
        );
    }

    @DisplayName("플레이어가 블랙잭이고 딜러가 블랙잭이 아니면 플레이어의 수익은 1.5배이다.")
    @Test
    void evaluateProfitsWhenPlayerIsBlackJack() {
        //given
        Gamblers gamblers = TestFixtures.gamblers(dealer, List.of(player1), 10000);
        dealer.takeCards(Card.SPADE_10, Card.HEART_10);
        player1.takeCards(Card.SPADE_A, Card.SPADE_J);

        //when
        Map<Gambler, Integer> moneys = gamblers.evaluateProfits();

        //then
        assertAll(
            () -> assertThat(moneys.get(dealer)).isEqualTo(-15000),
            () -> assertThat(moneys.get(player1)).isEqualTo(+15000)
        );
    }

    @DisplayName("플레이어와 딜러 모두 블랙잭이면 수익은 0원이다.")
    @Test
    void evaluateProfitsWhenPlayerAndDealerAreBlackJack() {
        //given
        Gamblers gamblers = TestFixtures.gamblers(dealer, List.of(player1), 10000);
        dealer.takeCards(Card.SPADE_10, Card.SPADE_A);
        player1.takeCards(Card.HEART_10, Card.HEART_A);

        //when
        Map<Gambler, Integer> moneys = gamblers.evaluateProfits();

        //then
        assertAll(
            () -> assertThat(moneys.get(dealer)).isEqualTo(0),
            () -> assertThat(moneys.get(player1)).isEqualTo(0)
        );
    }
}
