package domain.user;

import domain.card.Card;
import domain.deck.TotalDeckGenerator;
import domain.money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.card.Number.FIVE;
import static domain.card.Shape.CLOVER;
import static domain.game.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;

class UsersTest {
    @Test
    @DisplayName("딜러에게 카드를 추가한다.")
    void addDealerCardTest() {
        Player player = new Player(new Name("a"), new Money("100"));
        Users users = new Users(List.of(player));

        Card card = new Card(CLOVER, FIVE);
        users.addDealerCard(card);

        Dealer dealer = users.getDealer();
        assertThat(dealer.userDeck.getCards().get(0)).isEqualTo(card);
    }

    @Test
    @DisplayName("딜러가 카드를 추가로 받아야 하는지 판별한다.")
    void getDealerCardSumTest() {
        Player player = new Player(new Name("a"), new Money("100"));
        Users users = new Users(List.of(player));

        Card card = new Card(CLOVER, FIVE);
        users.addDealerCard(card);

        assertThat(users.isDealerCardAddCondition()).isTrue();
    }

    @Test
    @DisplayName("승패 결과를 반환한다.")
    void generatePlayerResultTest() {
        Player player = new Player(new Name("a"), new Money("100"));
        Users users = new Users(List.of(player));
        Player player1 = users.getPlayers().get(0);
        player1.addCard(new Card(CLOVER, FIVE));

        assertThat(users.generatePlayerResult(player1)).isEqualTo(WIN);
    }

    @Test
    @DisplayName("입력받은 전체 덱에서 각 유저에게 카드를 두장 씩 나눠준다.")
    void setStartCardsTest() {
        Player player = new Player(new Name("a"), new Money("100"));
        Users users = new Users(List.of(player));

        users.setStartCards(TotalDeckGenerator.generate());

        Dealer dealer = users.getDealer();
        assertThat(dealer.userDeck.getCards()).hasSize(2);
    }

}
