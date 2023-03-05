package domain.game;

import static org.assertj.core.api.Assertions.*;

import domain.card.Cards;
import domain.user.Dealer;
import domain.user.Name;
import domain.user.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    BlackJackGame blackJackGame;

    @BeforeEach
    void setUp() {
        List<Player> players = List.of(new Player(new Name("유자")), new Player(new Name("민트")));
        Dealer dealer = new Dealer();
        Cards cards = new Cards();

        blackJackGame = new BlackJackGame(players, dealer, cards);
    }

    @DisplayName("블랙잭 게임 생성 당시 플레이어와 딜러는 카드를 가지고 있지 않다.")
    @Test
    void initialCardTest() {
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

        assertThat(player.getCards().size()).isEqualTo(2);
        assertThat(dealer.getCards().size()).isEqualTo(2);
    }

    // TODO: 2023/03/06 테스트가 가능하도록 Card 주입 방식 바꾼 뒤 getSetUpResult() 메서드 테스트
}
