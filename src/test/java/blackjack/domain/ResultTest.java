package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    private BlackjackManager blackjackManager;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        CardDeck cardDeck = getDrawTestCardDeck();
        this.dealer = new Dealer();
        Players players = new Players(Collections.singletonList("미립"));
        this.blackjackManager = new BlackjackManager(cardDeck, this.dealer, players);
        this.blackjackManager.initDrawCards();
    }

    private CardDeck getDrawTestCardDeck() {
        return CardDeck.customDeck(Arrays.asList(
            Card.valueOf(Pattern.HEART, Number.SEVEN),
            Card.valueOf(Pattern.HEART, Number.TEN),
            Card.valueOf(Pattern.HEART, Number.FOUR),
            Card.valueOf(Pattern.HEART, Number.FIVE),
            Card.valueOf(Pattern.DIAMOND, Number.EIGHT),
            Card.valueOf(Pattern.DIAMOND, Number.TWO)
        ));
    }

    @Test
    @DisplayName("플레이어의 승 / 딜러 패 테스트")
    void testPlayerWinAndDealerLose() {
        this.blackjackManager.hitOrStayCurrentPlayer(true);
        this.blackjackManager.hitOrStayCurrentPlayer(true);
        this.blackjackManager.hitOrStayCurrentPlayer(false);
        Player player = this.blackjackManager.getCurrentPlayer();
        assertThat(player.judgeByDealerState(this.dealer)).isEqualTo(Result.WIN);
        assertThat(player.judgeByDealerState(this.dealer).reverse()).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("플레이어의 패 / 딜러 승 테스트")
    void testPlayerLoseAndDealerWin() {
        this.blackjackManager.hitOrStayCurrentPlayer(false);
        Player player = this.blackjackManager.getCurrentPlayer();
        assertThat(player.judgeByDealerState(this.dealer)).isEqualTo(Result.LOSE);
        assertThat(player.judgeByDealerState(this.dealer).reverse()).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("무승부 테스트")
    void testPlayerAndDealerDraw() {
        this.blackjackManager.hitOrStayCurrentPlayer(true);
        this.blackjackManager.hitOrStayCurrentPlayer(false);
        Player player = this.blackjackManager.getCurrentPlayer();
        assertThat(player.judgeByDealerState(this.dealer)).isEqualTo(Result.DRAW);
        assertThat(player.judgeByDealerState(this.dealer).reverse()).isEqualTo(Result.DRAW);
    }


}
