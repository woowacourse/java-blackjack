package domain.user;

import static domain.card.Number.FIVE;
import static domain.card.Shape.CLOVER;
import static domain.game.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.TotalDeck;
import domain.TotalDeckGenerator;
import domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UsersTest {
    @Test
    @DisplayName("딜러에게 카드를 추가한다.")
    void addDealerCardTest() {
        Users users = new Users(List.of(new Player(new Name("a"))));

        Card card = new Card(CLOVER, FIVE);
        users.addDealerCard(card);

        User dealer = users.getUsers().get(0);
        assertThat(dealer.userDeck.getCards().get(0)).isEqualTo(card);
    }

    @Test
    @DisplayName("딜러가 카드를 추가로 받아야 하는지 판별한다.")
    void getDealerCardSumTest() {
        Users users = new Users(List.of(new Player(new Name("a"))));

        Card card = new Card(CLOVER, FIVE);
        users.addDealerCard(card);

        assertThat(users.isDealerCardAddable()).isTrue();
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

        User dealer = users.getUsers().get(0);
        assertThat(dealer.userDeck.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("중복된 이름을 입력하면 오류를 던진다.")
    void validateDuplicatedNamesTest() {
        assertThatThrownBy(
                () -> new Users(List.of(new Player(new Name("a")), new Player(new Name("a"))))
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름은 입력할 수 없습니다: a");
    }
}
