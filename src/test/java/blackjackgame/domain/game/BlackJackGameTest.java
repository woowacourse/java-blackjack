package blackjackgame.domain.game;

import static blackjackgame.domain.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import blackjackgame.domain.UserAction;
import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Cards;
import blackjackgame.domain.card.CloverCard;
import blackjackgame.domain.card.DiamondCard;
import blackjackgame.domain.card.HeartCard;
import blackjackgame.domain.card.SpadeCard;
import blackjackgame.domain.user.Bet;
import blackjackgame.domain.user.Dealer;
import blackjackgame.domain.user.Names;
import blackjackgame.domain.user.Player;
import blackjackgame.domain.user.PlayerStatus;
import blackjackgame.domain.user.Players;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {
    private BlackJackGame blackJackGame;
    private Player player;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        Players players = new Players(new Names(List.of("민트")), List.of(new Bet(5000)));
        player = players.getPlayers().get(0);
        dealer = new Dealer();
        Cards cards = new Cards((int deckCount) -> new ArrayList<>(List.of(
                CloverCard.CLOVER_FIVE, HeartCard.HEART_JACK, SpadeCard.SPADE_FIVE,
                DiamondCard.DIAMOND_SEVEN, HeartCard.HEART_ACE, CloverCard.CLOVER_SIX, CloverCard.CLOVER_EIGHT,
                DiamondCard.DIAMOND_NINE, HeartCard.HEART_SEVEN
        )));

        blackJackGame = new BlackJackGame(players, dealer, cards);
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

        blackJackGame.hitUntilSatisfyingDealerMinimumScore();

        assertThat(dealer.getCards()).isEqualTo(expectedCards);
    }

    @DisplayName("딜러가 기본 카드 2장 외에 카드를 추가로 뽑았다면, true를 반환한다.")
    @Test
    void isDealerDrawExtraCount_true() {
        blackJackGame.drawDefaultCard();

        dealer.receiveCard(SpadeCard.SPADE_ACE);

        assertThat(blackJackGame.isDealerDrawExtraCount()).isTrue();
    }

    @DisplayName("딜러가 기본 카드 2장 외에 카드를 추가로 뽑지 않았다면, false를 반환한다.")
    @Test
    void isDealerDrawExtraCount_false() {
        blackJackGame.drawDefaultCard();

        assertThat(blackJackGame.isDealerDrawExtraCount()).isFalse();
    }

    @DisplayName("플레이어가 HIT를 선택했을 경우, 한 장의 카드를 더 뽑는다.")
    @Test
    void takeUserAction_hit() {
        blackJackGame.drawDefaultCard();
        int defaultSize = player.getCards().size();

        blackJackGame.takePlayerAction(player, UserAction.HIT);

        assertThat(player.getCards().size()).isEqualTo(defaultSize + 1);
    }

    @DisplayName("플레이어가 STAY를 선택했을 경우, 카드를 뽑지 않고 플레이어의 상태를 드로우 종료 상태로 변경한다.")
    @Test
    void takeUserAction_stay() {
        blackJackGame.drawDefaultCard();
        int defaultSize = player.getCards().size();

        blackJackGame.takePlayerAction(player, UserAction.STAY);

        assertThat(player.getCards().size()).isEqualTo(defaultSize);
        assertThat(player.getStatus()).isSameAs(PlayerStatus.DRAW_FINISHED);
    }

    @DisplayName("플레이어가 수익을 잃은만큼 딜러가 수익을 얻는다.")
    @Test
    void calculateDealerProfit_dealerWin() {
        dealer.receiveCards(EIGHT_NINE_CARDS);
        player.receiveCards(SIX_FOUR_CARDS);

        int dealerProfit = blackJackGame.calculateDealerProfit();
        int playerProfit = player.getProfitAmount();

        assertThat(dealerProfit).isEqualTo(-playerProfit);
    }
}
