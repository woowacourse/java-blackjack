package team.blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {
    private List<String> playerNames;
    private BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        playerNames = List.of("pobi", "woni");
        blackjackGame = new BlackjackGame(playerNames);
    }

    @Test
    void 플레이어는_처음_게임_시작시에_서로_다른_2장의_카드를_받는다() {
        Player newPlayer = new Player("pobi");

        blackjackGame.dealInitialCardsTo(newPlayer);

        assertThat(newPlayer.getHands().getFirst().getCards().size()).isEqualTo(2);
    }

    @Test
    void 딜러는_처음_게임_시작시에_서로_다른_2장의_카드를_받는다() {
        Dealer dealer = new Dealer();

        blackjackGame.dealInitialCardsTo(dealer);

        assertThat(dealer.getHand().getCards().size()).isEqualTo(2);
    }

    @Test
    void 플레이어에게_카드_한장을_발급한다() {
        Player player = new Player("pobi");

        blackjackGame.dealCardTo(player);

        assertThat(player.getHands().getFirst().getCards().size()).isEqualTo(1);
    }

    @Test
    void 딜러에게_카드_한장을_발급한다() {
        Dealer dealer = new Dealer();

        blackjackGame.dealCardTo(dealer);

        assertThat(dealer.getHand().getCards().size()).isEqualTo(1);
    }
}
