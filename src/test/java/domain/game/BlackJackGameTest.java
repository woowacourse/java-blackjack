package domain.game;

import static org.assertj.core.api.Assertions.*;

import domain.card.Card;
import domain.card.Cards;
import domain.card.CardsGenerator;
import domain.card.CloverCard;
import domain.card.DiamondCard;
import domain.card.HeartCard;
import domain.card.SpadeCard;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {
    BlackJackGame blackJackGame;

    @BeforeEach
    void setUp() {
        Players players = new Players(List.of("민트"));
        Dealer dealer = new Dealer();
        Cards cards = new Cards(new FixedCardsGenerator());

        blackJackGame = new BlackJackGame(players, dealer, cards);
    }

    @DisplayName("블랙잭 게임 생성 당시 플레이어와 딜러는 카드를 가지고 있지 않다.")
    @Test
    void defaultCardTest() {
        Player player = blackJackGame.getPlayers().get(0);
        Dealer dealer = blackJackGame.getDealer();

        assertThat(player.getCards().size()).isEqualTo(0);
        assertThat(dealer.getCards().size()).isEqualTo(0);
    }

    @DisplayName("플레이어와 딜러의 기본 카드 2장을 뽑는다.")
    @Test
    void drawDefaultCardTest() {
        Player player = blackJackGame.getPlayers().get(0);
        Dealer dealer = blackJackGame.getDealer();

        blackJackGame.drawDefaultCard();

        assertThat(player.getCards()).isEqualTo(List.of(CloverCard.CLOVER_FIVE, HeartCard.HEART_JACK));
        assertThat(dealer.getCards()).isEqualTo(List.of(SpadeCard.SPADE_FIVE, DiamondCard.DIAMOND_SEVEN));
    }

    @DisplayName("딜러는 합산 점수가 17 이상이 될 때까지 카드를 뽑는다")
    @Test
    void drawDealerCardUntilSatisfyingConditionTest() {
        Dealer dealer = blackJackGame.getDealer();
        List<Card> expectedCards = List.of(CloverCard.CLOVER_FIVE, HeartCard.HEART_JACK, SpadeCard.SPADE_FIVE);

        blackJackGame.drawDealerCardUntilSatisfyingCondition();

        assertThat(dealer.getCards()).isEqualTo(expectedCards);
    }

    @DisplayName("플레이어만 버스트일 경우, 딜러가 승리한다.")
    @Test
    void judgeWinnerTest_playerBust() {
        Map<Result, Integer> winningRecord = blackJackGame.getDealer().getWinningRecord();
        Player player = blackJackGame.getPlayers().get(0);
        blackJackGame.drawDefaultCard(); // 플레이어 : 5클로버, J하트 , 딜러 : 5스페이드, 7다이아몬드
        player.receiveCard(DiamondCard.DIAMOND_JACK); // 플레이어 : 5클로버, J하트, J다이아몬드

        blackJackGame.judgeWinner();

        assertThat(player.getResult()).isEqualTo(Result.LOSE);
        assertThat(winningRecord.get(Result.WIN)).isEqualTo(1);
        assertThat(winningRecord.get(Result.DRAW)).isEqualTo(0);
        assertThat(winningRecord.get(Result.LOSE)).isEqualTo(0);
    }

    @DisplayName("딜러만 버스트일 경우 플레이어가 승리한다.")
    @Test
    void judgeWinnerTest_playerNormalAndDealerBust() {
        Map<Result, Integer> winningRecord = blackJackGame.getDealer().getWinningRecord();
        Player player = blackJackGame.getPlayers().get(0);
        Dealer dealer = blackJackGame.getDealer();
        blackJackGame.drawDefaultCard(); // 플레이어 : 5클로버, J하트 , 딜러 : 5스페이드, 7다이아몬드
        dealer.receiveCards(List.of(HeartCard.HEART_TEN, DiamondCard.DIAMOND_TEN)); // 딜러 : 5스페이드, 7다이아몬드, 10하트, 10다이아몬드

        blackJackGame.judgeWinner();

        assertThat(player.getResult()).isEqualTo(Result.WIN);
        assertThat(winningRecord.get(Result.WIN)).isEqualTo(0);
        assertThat(winningRecord.get(Result.DRAW)).isEqualTo(0);
        assertThat(winningRecord.get(Result.LOSE)).isEqualTo(1);
    }

    @DisplayName("딜러와 플레이어 모두 버스트가 아니고, 딜러의 점수가 플레이어의 점수보다 높으면 딜러가 승리한다.")
    @Test
    void judgeWinnerTest_dealerWin() {
        Map<Result, Integer> winningRecord = blackJackGame.getDealer().getWinningRecord();
        Player player = blackJackGame.getPlayers().get(0);
        blackJackGame.drawDefaultCard(); // 플레이어 : 5클로버, J하트 , 딜러 : 5스페이드, 7다이아몬드
        blackJackGame.drawDealerCardUntilSatisfyingCondition(); // 딜러 : 5스페이드, 7다이아몬드, A하트, 6클로버

        blackJackGame.judgeWinner();

        assertThat(player.getResult()).isEqualTo(Result.LOSE);
        assertThat(winningRecord.get(Result.WIN)).isEqualTo(1);
        assertThat(winningRecord.get(Result.DRAW)).isEqualTo(0);
        assertThat(winningRecord.get(Result.LOSE)).isEqualTo(0);
    }

    @DisplayName("딜러와 플레이어 모두 버스트가 아니고, 플레이어의 점수가 딜러의 점수보다 높으면 플레이어가 승리한다.")
    @Test
    void judgeWinnerTest_playerWin() {
        Map<Result, Integer> winningRecord = blackJackGame.getDealer().getWinningRecord();
        Player player = blackJackGame.getPlayers().get(0);
        blackJackGame.drawDefaultCard(); // 플레이어 : 5클로버, J하트 / 딜러 : 5스페이드, 7다이아몬드

        blackJackGame.judgeWinner();

        assertThat(player.getResult()).isEqualTo(Result.WIN);
        assertThat(winningRecord.get(Result.WIN)).isEqualTo(0);
        assertThat(winningRecord.get(Result.DRAW)).isEqualTo(0);
        assertThat(winningRecord.get(Result.LOSE)).isEqualTo(1);
    }

    @DisplayName("딜러와 플레이어 모두 버스트면 무승부다.")
    @Test
    void judgeWinnerTest_bothBust() {
        Dealer dealer = blackJackGame.getDealer();
        Map<Result, Integer> winningRecord = dealer.getWinningRecord();
        Player player = blackJackGame.getPlayers().get(0);

        player.receiveCards(List.of(CloverCard.CLOVER_JACK, CloverCard.CLOVER_QUEEN, CloverCard.CLOVER_KING));
        dealer.receiveCards(List.of(DiamondCard.DIAMOND_JACK, DiamondCard.DIAMOND_QUEEN, DiamondCard.DIAMOND_KING));
        blackJackGame.judgeWinner();

        assertThat(player.getResult()).isEqualTo(Result.DRAW);
        assertThat(winningRecord.get(Result.WIN)).isEqualTo(0);
        assertThat(winningRecord.get(Result.DRAW)).isEqualTo(1);
        assertThat(winningRecord.get(Result.LOSE)).isEqualTo(0);
    }

    @DisplayName("딜러와 플레이어의 점수가 같으면 무승부다.")
    @Test
    void judgeWinnerTest_bothNormalSameScore() {
        Dealer dealer = blackJackGame.getDealer();
        Map<Result, Integer> winningRecord = dealer.getWinningRecord();
        Player player = blackJackGame.getPlayers().get(0);

        player.receiveCard(CloverCard.CLOVER_TEN); // 플레이어 : 10클로버
        dealer.receiveCard(DiamondCard.DIAMOND_TEN); // 딜러 : 10다이아몬드
        blackJackGame.judgeWinner();

        assertThat(player.getResult()).isEqualTo(Result.DRAW);
        assertThat(winningRecord.get(Result.WIN)).isEqualTo(0);
        assertThat(winningRecord.get(Result.DRAW)).isEqualTo(1);
        assertThat(winningRecord.get(Result.LOSE)).isEqualTo(0);
    }

    private class FixedCardsGenerator implements CardsGenerator {
        /*
         5클로버, J하트, 5스페이드, 7다이아몬드, A하트, 6클로버, 8클로버, 9다이아몬드, 7하트
         위 순서대로 담겨져 있는 List<Card>를 반환
         */
        @Override
        public List<Card> generate() {
            return new ArrayList<>(
                    List.of(CloverCard.CLOVER_FIVE, HeartCard.HEART_JACK, SpadeCard.SPADE_FIVE,
                            DiamondCard.DIAMOND_SEVEN, HeartCard.HEART_ACE, CloverCard.CLOVER_SIX, CloverCard.CLOVER_EIGHT,
                            DiamondCard.DIAMOND_NINE, HeartCard.HEART_SEVEN)
            );
        }
    }
}
