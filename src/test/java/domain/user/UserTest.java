package domain.user;

import domain.CardDeck;
import domain.BlackjackGame;
import domain.Card;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class UserTest {
    @Nested
    @DisplayName("카드 공개")
    public class OpenCard {
        @DisplayName("일반 유저는 자신이 가진 모든 카드를 공개해야 한다")
        @Test
        void test() {
            // given
            List<String> names = List.of("수양");
            List<Player> users = names.stream().map(Player::new).toList();
            BlackjackGame gameManger = new BlackjackGame(users, new Dealer(), new CardDeck());
            User user = gameManger.findUserByUsername("수양");
            user.drawCard(gameManger.handOutCard());
            user.drawCard(gameManger.handOutCard());

            // when
            List<Card> cards = user.openInitialCard();

            // then
            Assertions.assertThat(cards).hasSize(2);
        }

        @DisplayName("딜러는 자신이 가진 하나의 카드만 공개해야 한다")
        @Test
        void test2() {
            // given
            List<String> names = List.of("수양");
            List<Player> users = names.stream().map(Player::new).toList();
            BlackjackGame gameManger = new BlackjackGame(users, new Dealer(), new CardDeck());
            User user = gameManger.getDealer();
            user.drawCard(gameManger.handOutCard());
            user.drawCard(gameManger.handOutCard());

            // when
            List<Card> cards = user.openInitialCard();

            // then
            Assertions.assertThat(cards).hasSize(1);
        }
    }

    @DisplayName("카드를 뽑았을 때 21을 넘기면 버스트된다")
    @Test
    void test3() {
        // given
        List<String> names = List.of("수양");
        List<Player> users = names.stream().map(Player::new).toList();
        BlackjackGame gameManger = new BlackjackGame(users, new Dealer(), new CardDeck());
        User user = gameManger.findUserByUsername("수양");
        user.drawCard(gameManger.handOutCard());
        user.drawCard(gameManger.handOutCard());
        for (int i = 0; i < 12; i++) {
            user.drawCard(gameManger.handOutCard());
        }

        // when && then
        Assertions.assertThat(user.isBust()).isEqualTo(true);
    }
}