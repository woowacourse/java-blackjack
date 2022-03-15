package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.TrumpNumber;
import blackjack.model.card.TrumpSymbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("이름이 공백이면 예외가 발생한다")
    @Test
    void build_exception_blank() {
        assertThatThrownBy(() -> new Player(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 공백이거나 없을 수 없습니다.");
    }

    @DisplayName("이름이 null값 이면 예외가 발생한다")
    @Test
    void build_exception_null() {
        assertThatThrownBy(() -> new Player(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 공백이거나 없을 수 없습니다.");
    }

    @DisplayName("Player가 정상적으로 생성되는지 확인한다.")
    @Test
    void construct_Gamer() {
        Participant liver = new Player("아차산메이웨더미래의챔피언리버");

        assertThat(liver).isInstanceOf(Player.class);
    }

    @DisplayName("카드 점수의 합이 21점 이상이면 true를 반환한다.")
    @Test
    void isImpossibleHit_true() {
        Player player = new Player("리버");
        player.receive(new Card(TrumpNumber.NINE, TrumpSymbol.CLOVER));
        player.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.receive(new Card(TrumpNumber.TWO, TrumpSymbol.CLOVER));

        assertThat(player.isImpossibleHit()).isTrue();
    }
}
