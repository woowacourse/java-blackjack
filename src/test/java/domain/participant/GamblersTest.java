package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.card.CardHand;
import domain.card.CardPack;
import domain.game.GamblingMoney;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class GamblersTest {

    private Dealer dealer;
    private Player player1;
    private Player player2;
    private Player player3;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(new CardHand());
        player1 = new Player("이름1", new CardHand(), GamblingMoney.bet(10000));
        player2 = new Player("이름2", new CardHand(), GamblingMoney.bet(10000));
        player3 = new Player("이름3", new CardHand(), GamblingMoney.bet(10000));
    }

    @Test
    void 딜러와_플레이어들로_Gamblers를_생성한다() {
        assertThatCode(() -> new Gamblers(dealer, List.of(player1, player2, player3)))
            .doesNotThrowAnyException();
    }

    @Test
    void 플레이어가_없으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Gamblers(dealer, List.of()))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어가_8명보다_많으면_예외가_발생한다() {
        final var money = GamblingMoney.bet(10000);
        List<Player> players = List.of(
            new Player("이름1", new CardHand(), money),
            new Player("이름2", new CardHand(), money),
            new Player("이름3", new CardHand(), money),
            new Player("이름4", new CardHand(), money),
            new Player("이름5", new CardHand(), money),
            new Player("이름6", new CardHand(), money),
            new Player("이름7", new CardHand(), money),
            new Player("이름8", new CardHand(), money),
            new Player("이름9", new CardHand(), money)
        );

        assertThatThrownBy(() -> new Gamblers(dealer, players))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 딜러와_플레이어들에게_초기카드를_나눠준다() {
        Gamblers gamblers = new Gamblers(dealer, List.of(player1, player2));

        gamblers.distributeSetUpCards(CardPack.of(Card.allCards()));

        assertAll(
            () -> assertThat(dealer.getCards()).hasSize(2),
            () -> assertThat(player1.getCards()).hasSize(2),
            () -> assertThat(player2.getCards()).hasSize(2)
        );
    }

    @Test
    void 초기카드는_딜러먼저_2장_이후_플레이어들에게_순서대로_2장씩_나누어준다() {
        Gamblers gamblers = new Gamblers(dealer, List.of(player1, player2, player3));

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
        Gamblers gamblers = new Gamblers(dealer, List.of(player1, player2, player3));
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
        Gamblers gamblers = new Gamblers(dealer, List.of(player1, player2, player3));
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
        Gamblers gamblers = new Gamblers(dealer, List.of(player1));
        dealer.takeCards(Card.SPADE_J, Card.SPADE_7); // 16이하가 아니라서 못받음.
        player1.takeCards(Card.CLOVER_10, Card.HEART_10, Card.CLOVER_2); // 21을 넘어 버스트됨.

        CardPack cardPack = CardPack.of(List.of(Card.SPADE_A, Card.SPADE_2));

        //when
        gamblers.distributeExtraCardsToPlayers(cardPack, GamblerAnswersForTest.neverCalled());

        //then
        assertAll(
            () -> assertThat(dealer.getCards()).containsOnly(Card.SPADE_J, Card.SPADE_7),
            () -> assertThat(player1.getCards()).containsOnly(
                Card.CLOVER_10, Card.HEART_10, Card.CLOVER_2)
        );
    }

    @Test
    void 플레이어가_카드를_더_받을수_있지만_승낙하지_않으면_카드를_더_받지_않는다() {
        Gamblers gamblers = new Gamblers(dealer, List.of(player1));
        player1.takeCards(Card.CLOVER_2, Card.CLOVER_3);

        CardPack cardPack = CardPack.of(List.of(Card.DIAMOND_J, Card.SPADE_J));
        gamblers.distributeExtraCardsToPlayers(cardPack, GamblerAnswersForTest.neverOK());

        assertThat(player1.getCards()).containsOnly(Card.CLOVER_2, Card.CLOVER_3);
    }

    @Test
    void 플레이어들의_최종_수익을_계산할_수_있다() {
        //given
        final var money = GamblingMoney.bet(10000);
        Player winner1 = new Player("winner", new CardHand(), money);
        Player drawer1 = new Player("drawer1", new CardHand(), money);
        Player drawer2 = new Player("drawer2", new CardHand(), money);
        Player loser1 = new Player("loser1", new CardHand(), money);
        Player loser2 = new Player("loser2", new CardHand(), money);
        Player loser3 = new Player("loser3", new CardHand(), money);

        Gamblers gamblers = new Gamblers(dealer,
            List.of(winner1, drawer1, drawer2, loser1, loser2, loser3));

        dealer.takeCards(Card.SPADE_10, Card.DIAMOND_10);
        winner1.takeCards(Card.SPADE_10, Card.HEART_9, Card.HEART_2); // 21
        drawer1.takeCards(Card.CLOVER_10, Card.SPADE_J); // 20
        drawer2.takeCards(Card.DIAMOND_J, Card.HEART_J); // 20
        loser1.takeCards(Card.SPADE_K, Card.SPADE_9); // 19
        loser2.takeCards(Card.DIAMOND_K, Card.DIAMOND_9); // 19
        loser3.takeCards(Card.HEART_K, Card.HEART_9); // 19

        //when
        Map<Player, GamblingMoney> playerProfits = gamblers.evaluatePlayerProfits();

        //then
        assertAll(
            () -> assertThat(playerProfits.get(winner1)).isEqualTo(new GamblingMoney(10000)),
            () -> assertThat(playerProfits.get(drawer1)).isEqualTo(new GamblingMoney(0)),
            () -> assertThat(playerProfits.get(drawer2)).isEqualTo(new GamblingMoney(0)),
            () -> assertThat(playerProfits.get(loser1)).isEqualTo(new GamblingMoney(-10000)),
            () -> assertThat(playerProfits.get(loser2)).isEqualTo(new GamblingMoney(-10000)),
            () -> assertThat(playerProfits.get(loser3)).isEqualTo(new GamblingMoney(-10000))
        );
    }

    @Test
    void 딜러의_최종_수익을_계산할_수_있다() {
        //given
        final var money = GamblingMoney.bet(10000);
        Player winner1 = new Player("winner", new CardHand(), money);
        Player drawer1 = new Player("drawer1", new CardHand(), money);
        Player drawer2 = new Player("drawer2", new CardHand(), money);
        Player loser1 = new Player("loser1", new CardHand(), money);
        Player loser2 = new Player("loser2", new CardHand(), money);
        Player loser3 = new Player("loser3", new CardHand(), money);

        Gamblers gamblers = new Gamblers(dealer,
            List.of(winner1, drawer1, drawer2, loser1, loser2, loser3));

        dealer.takeCards(Card.SPADE_10, Card.DIAMOND_10);
        winner1.takeCards(Card.SPADE_10, Card.HEART_9, Card.HEART_2); // 21
        drawer1.takeCards(Card.CLOVER_10, Card.SPADE_J); // 20
        drawer2.takeCards(Card.DIAMOND_J, Card.HEART_J); // 20
        loser1.takeCards(Card.SPADE_K, Card.SPADE_9); // 19
        loser2.takeCards(Card.DIAMOND_K, Card.DIAMOND_9); // 19
        loser3.takeCards(Card.HEART_K, Card.HEART_9); // 19

        //when
        GamblingMoney dealerProfit = gamblers.evaluateDealerProfit();

        //then
        assertThat(dealerProfit).isEqualTo(new GamblingMoney(20000));
    }

    @DisplayName("오직 플레이어만 블랙잭인 경우 딜러는 플레이어 배팅금의 1.5배를 손해본다.")
    @Test
    void dealerProfitWhenOnlyPlayerBlackjack() {
        //given
        Player player = new Player("winner", new CardHand(), GamblingMoney.bet(10000));
        Gamblers gamblers = new Gamblers(dealer, List.of(player));

        dealer.takeCards(Card.SPADE_10, Card.DIAMOND_10);
        player.takeCards(Card.SPADE_10, Card.HEART_A);

        //when
        GamblingMoney dealerProfit = gamblers.evaluateDealerProfit();

        //then
        assertThat(dealerProfit).isEqualTo(new GamblingMoney(-15000));
    }
}
