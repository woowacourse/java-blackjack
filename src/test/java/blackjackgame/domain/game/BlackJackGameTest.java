package blackjackgame.domain.game;

import static org.assertj.core.api.Assertions.*;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Cards;
import blackjackgame.domain.card.CardsGenerator;
import blackjackgame.domain.card.CloverCard;
import blackjackgame.domain.card.DiamondCard;
import blackjackgame.domain.card.HeartCard;
import blackjackgame.domain.card.SpadeCard;
import blackjackgame.domain.user.Dealer;
import blackjackgame.domain.user.DealerStatus;
import blackjackgame.domain.user.Player;
import blackjackgame.domain.user.PlayerStatus;
import blackjackgame.domain.user.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {
    private BlackJackGame blackJackGame;
    private Player player;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        Players players = new Players(List.of("민트"));
        player = players.getPlayers().get(0);
        dealer = new Dealer();
        Cards cards = new Cards(new FixedCardsGenerator());

        blackJackGame = new BlackJackGame(players , dealer, cards);
    }

    @DisplayName("블랙잭 게임 생성 당시 플레이어와 딜러는 카드를 가지고 있지 않다.")
    @Test
    void defaultCardTest() {
        assertThat(player.getCards().size()).isEqualTo(0);
        assertThat(dealer.getCards().size()).isEqualTo(0);
    }

    @DisplayName("플레이어와 딜러의 기본 카드 2장을 뽑는다.")
    @Test
    void drawDefaultCardTest() {
        int dealerInitialCardsSize = dealer.getCards().size();
        int playerInitialCardsSize = player.getCards().size();

        blackJackGame.drawDefaultCard();

        assertThat(dealer.getCards().size()).isEqualTo(dealerInitialCardsSize + 2);
        assertThat(player.getCards().size()).isEqualTo(playerInitialCardsSize + 2);
        assertThat(dealer.getCards()).isEqualTo(List.of(CloverCard.CLOVER_FIVE, HeartCard.HEART_JACK));
        assertThat(player.getCards()).isEqualTo(List.of(SpadeCard.SPADE_FIVE, DiamondCard.DIAMOND_SEVEN));
    }

    @DisplayName("딜러는 합산 점수가 17 이상이 될 때까지 카드를 뽑는다")
    @Test
    void drawDealerCardUntilSatisfyingConditionTest() {
        List<Card> expectedCards = List.of(CloverCard.CLOVER_FIVE, HeartCard.HEART_JACK, SpadeCard.SPADE_FIVE);

        blackJackGame.drawDealerCardUntilSatisfyingMinimumScore();

        assertThat(dealer.getCards()).isEqualTo(expectedCards);
    }

    @DisplayName("플레이어만 버스트일 경우, 딜러가 승리한다.")
    @Test
    void judgeWinnerTest_playerBust() {
        dealer.receiveCards(FixedCardsGenerator.generateScore17Case());
        player.receiveCards(FixedCardsGenerator.generateScore29Case());

        blackJackGame.judgeWinner();
        Map<Result, Integer> winningRecord = blackJackGame.getDealerFinalResult();

        assertThat(player.getStatus()).isSameAs(PlayerStatus.BUST);
        assertThat(dealer.getStatus()).isNotSameAs(DealerStatus.BUST);
        assertThat(winningRecord.get(Result.WIN)).isEqualTo(1);
        assertThat(winningRecord.get(Result.DRAW)).isEqualTo(0);
        assertThat(winningRecord.get(Result.LOSE)).isEqualTo(0);
    }

    @DisplayName("딜러만 버스트일 경우 플레이어가 승리한다.")
    @Test
    void judgeWinnerTest_playerNormalAndDealerBust() {
        dealer.receiveCards(FixedCardsGenerator.generateScore29Case());
        player.receiveCards(FixedCardsGenerator.generateScore17Case());

        blackJackGame.judgeWinner();
        Map<Result, Integer> winningRecord = blackJackGame.getDealerFinalResult();

        assertThat(dealer.getStatus()).isSameAs(DealerStatus.BUST);
        assertThat(player.getStatus()).isNotSameAs(PlayerStatus.BUST);
        assertThat(winningRecord.get(Result.WIN)).isEqualTo(0);
        assertThat(winningRecord.get(Result.DRAW)).isEqualTo(0);
        assertThat(winningRecord.get(Result.LOSE)).isEqualTo(1);
    }

    @DisplayName("딜러와 플레이어 모두 버스트가 아니고, 딜러의 점수가 플레이어의 점수보다 높으면 딜러가 승리한다.")
    @Test
    void judgeWinnerTest_dealerWin() {
        dealer.receiveCards(FixedCardsGenerator.generateScore17Case());
        player.receiveCards(FixedCardsGenerator.generateScore10case());

        blackJackGame.judgeWinner();
        Map<Result, Integer> winningRecord = blackJackGame.getDealerFinalResult();

        assertThat(dealer.getStatus()).isNotSameAs(DealerStatus.BUST);
        assertThat(player.getStatus()).isNotSameAs(PlayerStatus.BUST);
        assertThat(winningRecord.get(Result.WIN)).isEqualTo(1);
        assertThat(winningRecord.get(Result.DRAW)).isEqualTo(0);
        assertThat(winningRecord.get(Result.LOSE)).isEqualTo(0);
    }

    @DisplayName("딜러와 플레이어 모두 버스트가 아니고, 플레이어의 점수가 딜러의 점수보다 높으면 플레이어가 승리한다.")
    @Test
    void judgeWinnerTest_playerWin() {
        dealer.receiveCards(FixedCardsGenerator.generateScore10case());
        player.receiveCards(FixedCardsGenerator.generateScore17Case());

        blackJackGame.judgeWinner();
        Map<Result, Integer> winningRecord = blackJackGame.getDealerFinalResult();

        assertThat(dealer.getStatus()).isNotSameAs(DealerStatus.BUST);
        assertThat(player.getStatus()).isNotSameAs(PlayerStatus.BUST);
        assertThat(winningRecord.get(Result.WIN)).isEqualTo(0);
        assertThat(winningRecord.get(Result.DRAW)).isEqualTo(0);
        assertThat(winningRecord.get(Result.LOSE)).isEqualTo(1);
    }

    @DisplayName("딜러와 플레이어 모두 버스트면 무승부다.")
    @Test
    void judgeWinnerTest_bothBust() {
        dealer.receiveCards(FixedCardsGenerator.generateScore29Case());
        player.receiveCards(FixedCardsGenerator.generateScore29Case());

        blackJackGame.judgeWinner();
        Map<Result, Integer> winningRecord = blackJackGame.getDealerFinalResult();

        assertThat(dealer.getStatus()).isSameAs(DealerStatus.BUST);
        assertThat(player.getStatus()).isSameAs(PlayerStatus.BUST);
        assertThat(winningRecord.get(Result.WIN)).isEqualTo(0);
        assertThat(winningRecord.get(Result.DRAW)).isEqualTo(1);
        assertThat(winningRecord.get(Result.LOSE)).isEqualTo(0);
    }

    @DisplayName("딜러와 플레이어의 점수가 같으면 무승부다.")
    @Test
    void judgeWinnerTest_bothNormalSameScore() {
        player.receiveCards(FixedCardsGenerator.generateScore17Case()); // 플레이어 : 10클로버
        dealer.receiveCards(FixedCardsGenerator.generateScore17Case()); // 딜러 : 10다이아몬드

        blackJackGame.judgeWinner();
        Map<Result, Integer> winningRecord = blackJackGame.getDealerFinalResult();

        assertThat(player.getScore()).isEqualTo(dealer.getScore());
        assertThat(winningRecord.get(Result.WIN)).isEqualTo(0);
        assertThat(winningRecord.get(Result.DRAW)).isEqualTo(1);
        assertThat(winningRecord.get(Result.LOSE)).isEqualTo(0);
    }

    private static class FixedCardsGenerator implements CardsGenerator {
        @Override
        public List<Card> generate() {
            return new ArrayList<>(
                    List.of(CloverCard.CLOVER_FIVE, HeartCard.HEART_JACK, SpadeCard.SPADE_FIVE,
                            DiamondCard.DIAMOND_SEVEN, HeartCard.HEART_ACE, CloverCard.CLOVER_SIX, CloverCard.CLOVER_EIGHT,
                            DiamondCard.DIAMOND_NINE, HeartCard.HEART_SEVEN)
            );
        }

        public static List<Card> generateScore29Case() {
            return new ArrayList<>(List.of(CloverCard.CLOVER_JACK, DiamondCard.DIAMOND_KING, SpadeCard.SPADE_NINE));
        }

        public static List<Card> generateScore17Case() {
            return new ArrayList<>(List.of(CloverCard.CLOVER_EIGHT, SpadeCard.SPADE_NINE));
        }

        public static List<Card> generateScore10case() {
            return new ArrayList<>(List.of(CloverCard.CLOVER_SIX, HeartCard.HEART_FOUR));
        }
    }
}
