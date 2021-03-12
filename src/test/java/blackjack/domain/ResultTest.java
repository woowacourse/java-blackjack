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

    private Dealer dealer;
    private Players players;

    @BeforeEach
    void setUp() {
        this.dealer = new Dealer();
        this.players = new Players(Collections.singletonList("미립"));
    }

    @Test
    @DisplayName("플레이어의 블랙잭 승리시 배당율 테스트")
    void testPlayerBlackjackWinAmplification() {
        BlackjackManager blackjackManager = new BlackjackManager(
            getBlackjackWinTestCardDeck(),
            this.dealer,
            players
        );
        blackjackManager.initDrawCards();
        Player player = blackjackManager.getCurrentPlayer();

        assertThat(player.judgeByDealerState(this.dealer)).isEqualTo(Result.BLACKJACK_WIN);
        assertThat(player.judgeByDealerState(this.dealer).getAmplification()).isEqualTo(1.5);
    }

    private CardDeck getBlackjackWinTestCardDeck() {
        return CardDeck.customDeck(Arrays.asList(
            Card.valueOf(Pattern.HEART, Number.SEVEN),
            Card.valueOf(Pattern.HEART, Number.TEN),
            Card.valueOf(Pattern.HEART, Number.ACE),
            Card.valueOf(Pattern.HEART, Number.JACK)
        ));
    }

    @Test
    @DisplayName("플레이어의 일반 승리시 배당율 테스트")
    void testPlayerWinAmplification() {
        BlackjackManager blackjackManager = new BlackjackManager(
            getWinTestCardDeck(),
            this.dealer,
            players
        );
        blackjackManager.initDrawCards();
        blackjackManager.hitOrStayCurrentPlayer(true);
        blackjackManager.hitOrStayCurrentPlayer(true);
        blackjackManager.hitOrStayCurrentPlayer(false);
        Player player = blackjackManager.getCurrentPlayer();

        assertThat(player.judgeByDealerState(this.dealer)).isEqualTo(Result.WIN);
        assertThat(player.judgeByDealerState(this.dealer).getAmplification()).isEqualTo(1);
    }

    private CardDeck getWinTestCardDeck() {
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
    @DisplayName("플레이어의 무승부시 배당율 테스트")
    void testPlayerDrawAmplification() {
        BlackjackManager blackjackManager = new BlackjackManager(
            getDrawTestCardDeck(),
            this.dealer,
            players
        );
        blackjackManager.initDrawCards();
        blackjackManager.hitOrStayCurrentPlayer(true);
        blackjackManager.hitOrStayCurrentPlayer(false);
        Player player = blackjackManager.getCurrentPlayer();

        assertThat(player.judgeByDealerState(this.dealer)).isEqualTo(Result.DRAW);
        assertThat(player.judgeByDealerState(this.dealer).getAmplification()).isEqualTo(1);
    }

    private CardDeck getDrawTestCardDeck() {
        return CardDeck.customDeck(Arrays.asList(
            Card.valueOf(Pattern.HEART, Number.SEVEN),
            Card.valueOf(Pattern.HEART, Number.TEN),
            Card.valueOf(Pattern.HEART, Number.FOUR),
            Card.valueOf(Pattern.HEART, Number.FIVE),
            Card.valueOf(Pattern.DIAMOND, Number.EIGHT)
        ));
    }

    @Test
    @DisplayName("플레이어의 패배시 배당율 테스트")
    void testPlayerLoseAmplification() {
        BlackjackManager blackjackManager = new BlackjackManager(
            getLoseTestCardDeck(),
            this.dealer,
            players
        );
        blackjackManager.initDrawCards();
        blackjackManager.hitOrStayCurrentPlayer(false);
        Player player = blackjackManager.getCurrentPlayer();

        assertThat(player.judgeByDealerState(this.dealer)).isEqualTo(Result.LOSE);
        assertThat(player.judgeByDealerState(this.dealer).getAmplification()).isEqualTo(-1);
    }

    private CardDeck getLoseTestCardDeck() {
        return CardDeck.customDeck(Arrays.asList(
            Card.valueOf(Pattern.HEART, Number.SEVEN),
            Card.valueOf(Pattern.HEART, Number.TEN),
            Card.valueOf(Pattern.HEART, Number.FOUR),
            Card.valueOf(Pattern.HEART, Number.FIVE)
        ));
    }
}
