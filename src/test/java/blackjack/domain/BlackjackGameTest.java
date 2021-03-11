package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.result.ResultBoard;
import blackjack.domain.state.State;
import blackjack.domain.state.Stay;
import blackjack.domain.user.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackjackGameTest {
    @Test
    @DisplayName("블랙잭 게임을 관리하는 객체 생성 - 플레이어 이름을 받아 세팅")
    void createBlackjackGame() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");

        BlackjackGame blackjackGame = BlackjackGame.generateByUser(names);
        assertThat(blackjackGame).isInstanceOf(BlackjackGame.class);
    }

    @Test
    @DisplayName("초기 카드를 나눠준다. - 2장인지 확인한다.")
    void handOutInitialCards() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        BlackjackGame blackjackGame = BlackjackGame.generateByUser(names);
        blackjackGame.handOutInitialCards();

        int dealerCardCount = blackjackGame.getDealer()
                .cards()
                .getCards()
                .size();
        boolean isPlayersCountTwo = blackjackGame.getPlayers()
                .getPlayers()
                .stream()
                .allMatch(player -> player.cards()
                        .getCards()
                        .size() == 2);

        assertThat(dealerCardCount).isEqualTo(2);
        assertThat(isPlayersCountTwo).isTrue();
    }

    @Test
    @DisplayName("플레이어가 게임을 진행할 수 있는지 확인한다.")
    void proceedPlayersRound() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        BlackjackGame blackjackGame = BlackjackGame.generateByUser(names);
        Player player = new Player("amazzzi");
        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.QUEEN),
                new Card(Suit.HEART, Denomination.QUEEN))));

        boolean isNotGameOver = blackjackGame.isNotGameOver(player);

        assertThat(isNotGameOver).isTrue();
    }

    @Test
    @DisplayName("플레이어 hit 확인")
    void hitPlayer() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        BlackjackGame blackjackGame = BlackjackGame.generateByUser(names);
        Player player = new Player("amazzzi");
        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.TWO)
        )));

        blackjackGame.hit(player);
        int cardCount = player.cards()
                .getCards()
                .size();

        assertThat(cardCount).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러 hit 확인")
    void hitDealer() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        BlackjackGame blackjackGame = BlackjackGame.generateByUser(names);
        Dealer dealer = new Dealer();
        dealer.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.TWO)
        )));

        blackjackGame.hit(dealer);
        int cardCount = dealer.cards().getCards().size();

        assertThat(cardCount).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러 포함 모든 유저들 가지고 오기 확인")
    void getUsers() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");

        BlackjackGame blackjackGame = BlackjackGame.generateByUser(names);
        int userCount = blackjackGame.getUsers()
                .size();

        assertThat(userCount).isEqualTo(4);
    }

    @Test
    @DisplayName("결과 보드 만들기 확인")
    void generateResultBoard() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        BlackjackGame blackjackGame = BlackjackGame.generateByUser(names);
        blackjackGame.handOutInitialCards();

        assertThat(blackjackGame.generateResultBoard()).isInstanceOf(ResultBoard.class);
    }

    @Test
    @DisplayName("n을 입력할 경우 상태를 stay로 변경한다.")
    void proceedPlayersRound1() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        BlackjackGame blackjackGame = BlackjackGame.generateByUser(names);
        Player player = new Player("amazzi");
        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.QUEEN),
                new Card(Suit.HEART, Denomination.QUEEN))));
        String answer = "n";
        blackjackGame.proceedPlayersRound(player, answer);

        State state = player.getState();
        assertThat(state).isInstanceOf(Stay.class);
    }
}
