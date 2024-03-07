package domain.user;

import domain.TotalDeck;
import domain.TotalDeckGenerator;
import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.Result.WIN;
import static domain.card.Number.FIVE;
import static domain.card.Number.TEN;
import static domain.card.Shape.CLOVER;
import static org.assertj.core.api.Assertions.assertThat;

class UsersTest {
    @Test
    @DisplayName("현재 플레이어를 반환한다.")
    void getCurrentPlayer() {
        Player player1 = new Player(new Name("a"));
        Users users = new Users(List.of(player1));

        assertThat(users.getCurrentPlayer()).isEqualTo(player1);
    }

    @Test
    @DisplayName("게임을 진행 중인 플레이어의 카드 덱을 보여준다.")
    void showCurrentUserDeck() {
        Player player1 = new Player(new Name("a"));
        Users users = new Users(List.of(player1));
        player1.addCard(new Card(CLOVER, FIVE));

        assertThat(users.showCurrentUserDeck()).isEqualTo(player1.userDeck);
    }

    @Test
    @DisplayName("현재 플레이 중인 유저에 카드를 추가한다.")
    void addCardOfCurrentUser() {
        Player player1 = new Player(new Name("a"));
        Users users = new Users(List.of(player1));
        Card card = new Card(CLOVER, FIVE);
        users.addCardOfCurrentUser(card);

        assertThat(users.showCurrentUserDeck().getFirstCard()).isEqualTo(card);
    }

    @Test
    @DisplayName("다음 플레이어로 순서를 넘겨준다.")
    void nextUser() {
        Player player1 = new Player(new Name("a"));
        Player player2 = new Player(new Name("b"));
        Users users = new Users(List.of(player1, player2));

        users.nextUser();
        assertThat(users.getCurrentPlayer()).isEqualTo(player2);
    }

    @Test
    @DisplayName("현재 플레이 중인 유저가 플레이어인지 확인한다.")
    void isCurrentUserPlayer() {
        Player player1 = new Player(new Name("a"));
        Player player2 = new Player(new Name("b"));
        Users users = new Users(List.of(player1, player2));

        assertThat(users.isCurrentUserPlayer()).isTrue();
    }

    @Test
    @DisplayName("현재 플레이 중인 유저가 딜러이면 false를 반환한다.")
    void isCurrentUserNotPlayer() {
        Player player1 = new Player(new Name("a"));
        Users users = new Users(List.of(player1));

        users.nextUser();
        assertThat(users.isCurrentUserPlayer()).isFalse();
    }

    @Test
    @DisplayName("딜러에게 카드를 추가한다.")
    void addDealerCard() {
        Users users = new Users(List.of(new Player(new Name("a"))));

        Card card = new Card(CLOVER, FIVE);
        users.addDealerCard(card);

        Dealer dealer = users.getDealer();
        assertThat(dealer.userDeck.getFirstCard()).isEqualTo(card);
    }

    @Test
    @DisplayName("딜러 카드의 합을 구한다.")
    void getDealerCardSum() {
        Users users = new Users(List.of(new Player(new Name("a"))));

        Card card = new Card(CLOVER, FIVE);
        users.addDealerCard(card);

        assertThat(users.getDealerCardSum()).isEqualTo(5);
    }

    @Test
    @DisplayName("승패 결과를 반환한다.")
    void generatePlayerResult() {
        Player player1 = new Player(new Name("a"));
        Users users = new Users(List.of(player1));

        player1.addCard(new Card(CLOVER, FIVE));

        assertThat(users.generatePlayerResult(player1)).isEqualTo(WIN);
    }

    @Test
    @DisplayName("입력받은 전체 덱에서 각 유저에게 카드를 두장 씩 나눠준다.")
    void setStartCards() {
        Users users = new Users(List.of(new Player(new Name("a"))));

        users.setStartCards(new TotalDeck(TotalDeckGenerator.generate()));

        Dealer dealer = users.getDealer();
        assertThat(dealer.userDeck.getCards()).hasSize(2);
    }


    @Test
    void busted() {
        Player player1 = new Player(new Name("a"));
        Users users = new Users(List.of(player1));

        player1.addCard(new Card(CLOVER, TEN));
        player1.addCard(new Card(CLOVER, TEN));
        player1.addCard(new Card(CLOVER, TEN));

        assertThat(users.busted()).isTrue();
    }
}
