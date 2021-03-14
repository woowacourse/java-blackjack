package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import blackjack.domain.participant.BetAmount;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Names;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackManagerTest {

    private Dealer dealer;
    private Players players;
    private BlackjackManager blackjackManager;

    @BeforeEach
    void setUp() {
        this.dealer = new Dealer();
        this.players = initPlayers();
        this.blackjackManager = new BlackjackManager(
            getDrawTestCardDeck(),
            this.dealer,
            this.players
        );
    }

    private Players initPlayers() {
        List<Player> players = new ArrayList<>();
        Names names = new Names(Arrays.asList("pobi", "jason"));
        while(!names.isDoneAllPlayers()){
            players.add(new Player(names.getCurrentTurnName(), BetAmount.betting(3000)));
            names.passTurnToNext();
        }
        return new Players(players);
    }

    private CardDeck getDrawTestCardDeck() {
        return CardDeck.customDeck(Arrays.asList(
            Card.valueOf(Pattern.HEART, Number.SIX),
            Card.valueOf(Pattern.HEART, Number.TEN),
            Card.valueOf(Pattern.HEART, Number.JACK),
            Card.valueOf(Pattern.HEART, Number.TEN),
            Card.valueOf(Pattern.HEART, Number.QUEEN),
            Card.valueOf(Pattern.HEART, Number.KING),
            Card.valueOf(Pattern.DIAMOND, Number.KING)
        ));
    }

    @Test
    @DisplayName("각 플레이어가 초기 2장씩 소지한다.")
    void testAllPlayersGetTwoCards() {
        this.blackjackManager.initDrawCards();

        assertThat(this.players
            .toList()
            .stream()
            .filter(player -> player.getCards().size() == 2)
            .count())
            .isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어 턴 넘기기 완료")
    void testPassTurnToNextPlayer() {
        this.blackjackManager.initDrawCards();
        assertThat(this.blackjackManager.getCurrentPlayerName()).isEqualTo("pobi");
        this.blackjackManager.passTurnToNextPlayer();
        assertThat(this.blackjackManager.getCurrentPlayerName()).isEqualTo("jason");
    }

    @Test
    @DisplayName("전체 플레이어의 차례가 끝났는지 확인 테스트")
    void testAllPlayersFinished() {
        this.blackjackManager.initDrawCards();
        this.blackjackManager.hitOrStayCurrentPlayer(false);
        this.blackjackManager.passTurnToNextPlayer();
        this.blackjackManager.hitOrStayCurrentPlayer(false);
        this.blackjackManager.passTurnToNextPlayer();
        assertThat(this.blackjackManager.isFinishedAllPlayers()).isTrue();
    }

    @Test
    @DisplayName("딜러 카드 뽑기 테스트")
    void testDealerDraw() {
        this.blackjackManager.initDrawCards();
        assertThat(this.blackjackManager.getDealer().getScoreToInt()).isEqualTo(16);
        this.blackjackManager.hitDealer();
        assertThat(this.blackjackManager.getDealer().getScoreToInt()).isEqualTo(26);
    }

    @Test
    @DisplayName("딜러 Stay 상태변화 테스트")
    void testDealerToStay() {
        this.blackjackManager.initDrawCards();
        assertThat(this.blackjackManager.isFinishedDealer()).isFalse();
        this.blackjackManager.stayDealer();
        assertThat(this.blackjackManager.isFinishedDealer()).isTrue();
    }

    @Test
    @DisplayName("딜러 카드뽑기 후 17점 이상시 상태전환 테스트")
    void testDealerToStayIfScoreOverThenLimit() {
        this.blackjackManager.initDrawCards();
        assertThat(this.blackjackManager.isFinishedDealer()).isFalse();
        assertThat(this.blackjackManager.isDealerScoreOverThenLimit()).isFalse();
        this.blackjackManager.hitDealer();
        assertThat(this.blackjackManager.isFinishedDealer()).isTrue();
        assertThat(this.blackjackManager.isDealerScoreOverThenLimit()).isTrue();
    }
}
