package blackjack.gamer;

import static blackjack.fixture.TestFixture.provideFourSizeCards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.cardMachine.CardRandomMachine;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("엠제이");
    }

    @DisplayName("플레이어가 초기에 받은 카드 2장을 공개한다.")
    @Test
    void showInitialCards() {
        // given
        final Dealer dealer = Dealer.getDealer(new CardRandomMachine());

        // when
        dealer.initCardMachine();
        final List<Card> cards = dealer.spreadTwoCards();
        player.receiveCards(cards);

        // then
        assertThat(player.showInitialCards()).hasSize(2);
    }

    @DisplayName("플레이어가 가진 모든 카드를 공개한다.")
    @Test
    void showAllCards() {
        // given

        // when
        player.receiveCards(provideFourSizeCards());

        // then
        assertThat(player.showAllCards()).hasSize(4);
    }

    @DisplayName("플레이어의 닉네임을 확인한다.")
    @Test
    void getPlayerNickName() {
        // given

        // when & then
        assertThat(player.getNickName()).isEqualTo("엠제이");
    }

    @DisplayName("플레이어가 승리한다.")
    @Test
    void win() {
        // given

        // when
        player.betMoney("10000");
        player.winGame();

        // then
        assertThat(player.getProfit()).isEqualTo(10_000);
    }

    @DisplayName("플레이어가 승리한다.")
    @Test
    void lose() {
        // given

        // when
        player.betMoney("10000");
        player.loseGame();

        // then
        assertThat(player.getProfit()).isEqualTo(-10_000);
    }

    @DisplayName("플레이어가 승리한다.")
    @Test
    void draw() {
        // given

        // when
        player.betMoney("10000");
        player.drawGame();

        // then
        assertThat(player.getProfit()).isEqualTo(0);
    }

    @DisplayName("플레이어가 블랙잭한다.")
    @Test
    void blackajack() {
        // given

        // when
        player.betMoney("10000");
        player.blackjackGame();

        // then
        assertThat(player.getProfit()).isEqualTo(5_000);
    }

    @DisplayName("플레이어가 푸시한다.")
    @Test
    void push() {
        // given

        // when
        player.betMoney("10000");
        player.pushGame();

        // then
        assertThat(player.getProfit()).isEqualTo(0);
    }
}
