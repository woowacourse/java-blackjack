package domain.user;

import static domain.card.Number.FIVE;
import static domain.card.Shape.CLUB;
import static domain.game.GameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import domain.Deck;
import domain.DeckGenerator;
import domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UsersTest {
    @Test
    @DisplayName("입력받은 전체 덱에서 각 유저에게 카드를 두장 씩 나눠준다.")
    void setStartCardsTest() {
        Users users = new Users(new Players(List.of(new Player(new Name("a")))));

        users.putStartCards(new Deck(new DeckGenerator().generate()));

        Dealer dealer = users.getDealer();
        assertThat(dealer.getAllCards()).hasSize(2);
    }

    @Test
    @DisplayName("승패 결과를 반환한다.")
    void generatePlayerResultTest() {
        Player player1 = new Player(new Name("a"));
        Users users = new Users(new Players(List.of(player1)));

        player1.addCard(new Card(CLUB, FIVE));

        assertThat(users.generatePlayerResult(player1)).isEqualTo(WIN);
    }
}
