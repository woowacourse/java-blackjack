package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CardManagerTest {

    @Test
    void 딜러와_플레이어들로_카드매니저를_생성한다() {
        //given
        Dealer dealer = new Dealer();
        Player player1 = new Player("이름1");
        Player player2 = new Player("이름2");

        //when
        //then
        assertThatCode(() -> new CardManager(dealer, List.of(player1, player2)))
            .doesNotThrowAnyException();
    }


    @Test
    void 딜러와_플레이어들에게_초기카드를_나눠준다() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("이름");

        //when
        CardManager cardManager = new CardManager(dealer, List.of(player));
        cardManager.distributeSetUpCards();

        //then
        assertAll(
            () -> assertThat(dealer.getCards()).hasSize(2),
            () -> assertThat(player.getCards()).hasSize(2)
        );
    }

}
