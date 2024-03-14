package domain.user;

import domain.card.Card;
import domain.deck.TotalDeckGenerator;
import domain.game.Index;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.card.Number.FIVE;
import static domain.card.Number.TEN;
import static domain.card.Shape.CLOVER;
import static domain.game.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;

class UsersTest {
    @Test
    @DisplayName("주어진 인덱스에 맞는 유저에 카드를 추가한다.")
    void addCardOfCurrentUserTest() {
        Player player1 = new Player(new Name("a"));
        Users users = new Users(List.of(player1));
        Card card = new Card(CLOVER, FIVE);
        Index index = users.makeIndex();
        users.addCardOfCurrentUser(card, index);

        assertThat(player1.userDeck.getCards().get(0)).isEqualTo(card);
    }

    @Test
    @DisplayName("딜러에게 카드를 추가한다.")
    void addDealerCardTest() {
        Users users = new Users(List.of(new Player(new Name("a"))));

        Card card = new Card(CLOVER, FIVE);
        users.addDealerCard(card);

        Dealer dealer = users.getDealer();
        assertThat(dealer.userDeck.getCards().get(0)).isEqualTo(card);
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

        users.setStartCards(TotalDeckGenerator.generate());

        Dealer dealer = users.getDealer();
        assertThat(dealer.userDeck.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("현재 플레이어의 버스트 여부를 확인한다.")
    void bustedTest() {
        Player player1 = new Player(new Name("a"));
        Users users = new Users(List.of(player1));

        player1.addCard(new Card(CLOVER, TEN));
        player1.addCard(new Card(CLOVER, TEN));
        player1.addCard(new Card(CLOVER, TEN));

        assertThat(users.currentUserBusted(users.makeIndex())).isTrue();
    }
}
