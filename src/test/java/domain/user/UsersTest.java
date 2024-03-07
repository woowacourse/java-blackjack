package domain.user;

import static domain.card.Number.FIVE;
import static domain.card.Number.TEN;
import static domain.card.Shape.CLOVER;
import static domain.game.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import domain.TotalDeck;
import domain.TotalDeckGenerator;
import domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UsersTest {
    @Test
    @DisplayName("현재 플레이어를 반환한다.")
    void getCurrentPlayerTest() {
        Player player1 = new Player(new Name("a"));
        Users users = new Users(List.of(player1));

        assertThat(users.getCurrentUser()).isEqualTo(player1);
    }

    @Test
    @DisplayName("현재 플레이 중인 유저에 카드를 추가한다.")
    void addCardOfCurrentUserTest() {
        Player player1 = new Player(new Name("a"));
        Users users = new Users(List.of(player1));
        Card card = new Card(CLOVER, FIVE);
        users.addCardOfCurrentUser(card);

        assertThat(users.getCurrentUser().userDeck.getFirstCard()).isEqualTo(card);
    }

    @Test
    @DisplayName("다음 플레이어로 순서를 넘겨준다.")
    void nextUserTest() {
        Player player1 = new Player(new Name("a"));
        Player player2 = new Player(new Name("b"));
        Users users = new Users(List.of(player1, player2));

        users.nextUser();
        assertThat(users.getCurrentUser()).isEqualTo(player2);
    }

    @Test
    @DisplayName("현재 플레이 중인 유저가 플레이어인지 확인한다.")
    void isCurrentUserPlayerTest() {
        Player player1 = new Player(new Name("a"));
        Player player2 = new Player(new Name("b"));
        Users users = new Users(List.of(player1, player2));

        assertThat(users.isCurrentUserPlayer()).isTrue();
    }

    @Test
    @DisplayName("현재 플레이 중인 유저가 딜러이면 false를 반환한다.")
    void isCurrentUserNotPlayerTest() {
        Player player1 = new Player(new Name("a"));
        Users users = new Users(List.of(player1));

        users.nextUser();
        assertThat(users.isCurrentUserPlayer()).isFalse();
    }

    @Test
    @DisplayName("딜러에게 카드를 추가한다.")
    void addDealerCardTest() {
        Users users = new Users(List.of(new Player(new Name("a"))));

        Card card = new Card(CLOVER, FIVE);
        users.addDealerCard(card);

        Dealer dealer = users.getDealer();
        assertThat(dealer.userDeck.getFirstCard()).isEqualTo(card);
    }

    @Test
    @DisplayName("딜러가 카드를 추가로 받아야 하는지 판별한다.")
    void getDealerCardSumTest() {
        Users users = new Users(List.of(new Player(new Name("a"))));

        Card card = new Card(CLOVER, FIVE);
        users.addDealerCard(card);

        assertThat(users.isDealerCardAddCondition()).isTrue();
    }

    @Test
    @DisplayName("승패 결과를 반환한다.")
    void generatePlayerResultTest() {
        Player player1 = new Player(new Name("a"));
        Users users = new Users(List.of(player1));

        player1.addCard(new Card(CLOVER, FIVE));

        assertThat(users.generatePlayerResult(player1)).isEqualTo(WIN);
    }

    @Test
    @DisplayName("입력받은 전체 덱에서 각 유저에게 카드를 두장 씩 나눠준다.")
    void setStartCardsTest() {
        Users users = new Users(List.of(new Player(new Name("a"))));

        users.setStartCards(new TotalDeck(TotalDeckGenerator.generate()));

        Dealer dealer = users.getDealer();
        assertThat(dealer.userDeck.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("플레이어의 숫자가 21을 넘으면 busted된다.")
    void bustedTest() {
        Player player1 = new Player(new Name("a"));
        Users users = new Users(List.of(player1));

        player1.addCard(new Card(CLOVER, TEN));
        player1.addCard(new Card(CLOVER, TEN));
        player1.addCard(new Card(CLOVER, TEN));

        assertThat(users.currentUserBusted()).isTrue();
    }
}
