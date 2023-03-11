package blackjackgame.domain.game;

import static org.assertj.core.api.Assertions.*;

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
        Cards cards = new Cards(() -> new ArrayList<>(List.of(
                CloverCard.CLOVER_FIVE, HeartCard.HEART_JACK, SpadeCard.SPADE_FIVE,
                DiamondCard.DIAMOND_SEVEN, HeartCard.HEART_ACE, CloverCard.CLOVER_SIX, CloverCard.CLOVER_EIGHT,
                DiamondCard.DIAMOND_NINE, HeartCard.HEART_SEVEN
        )));

        blackJackGame = new BlackJackGame(players , dealer, cards);
    }

    @DisplayName("블랙잭 게임 생성 당시 플레이어와 딜러는 카드를 가지고 있지 않다.")
    @Test
    void defaultCardTest() {
        assertThat(player.cards().size()).isEqualTo(0);
        assertThat(dealer.cards().size()).isEqualTo(0);
    }

    @DisplayName("플레이어와 딜러의 기본 카드 2장을 뽑는다.")
    @Test
    void drawDefaultCardTest() {
        int dealerInitialCardsSize = dealer.cards().size();
        int playerInitialCardsSize = player.cards().size();

        blackJackGame.drawDefaultCard();

        assertThat(dealer.cards().size()).isEqualTo(dealerInitialCardsSize + 2);
        assertThat(player.cards().size()).isEqualTo(playerInitialCardsSize + 2);
        assertThat(dealer.cards()).isEqualTo(List.of(CloverCard.CLOVER_FIVE, HeartCard.HEART_JACK));
        assertThat(player.cards()).isEqualTo(List.of(SpadeCard.SPADE_FIVE, DiamondCard.DIAMOND_SEVEN));
    }

    @DisplayName("딜러는 합산 점수가 17 이상이 될 때까지 카드를 뽑는다")
    @Test
    void drawDealerCardUntilSatisfyingConditionTest() {
        List<Card> expectedCards = List.of(CloverCard.CLOVER_FIVE, HeartCard.HEART_JACK, SpadeCard.SPADE_FIVE);

        blackJackGame.hitUntilSatisfyingDealerMinimumScore();

        assertThat(dealer.cards()).isEqualTo(expectedCards);
    }
}
