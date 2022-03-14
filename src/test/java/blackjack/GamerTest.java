package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.TrumpNumber;
import blackjack.model.card.TrumpSymbol;
import blackjack.model.player.Gamer;
import blackjack.model.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamerTest {

    @DisplayName("이름이 공백이면 예외가 발생한다")
    @Test
    void build_exception_blank() {
        assertThatThrownBy(() -> new Gamer(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 공백일 수 없습니다.");
    }

    @DisplayName("이름이 15자를 초과하면 예외가 발생한다")
    @Test
    void build_exception_max_length() {
        assertThatThrownBy(() -> new Gamer("아차산메이웨더미래의챔피언리버굿"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 15자 이하로 입력해주세요.");
    }

    @DisplayName("Gamer가 정상적으로 생성되는지 확인한다.")
    @Test
    void construct_Gamer() {
        Player liver = new Gamer("아차산메이웨더미래의챔피언리버");

        assertThat(liver).isInstanceOf(Gamer.class);
    }

    @DisplayName("카드 점수의 합이 21점 이상이면 true를 반환한다.")
    @Test
    void isImpossibleHit_true() {
        Gamer gamer = new Gamer("리버");
        gamer.receive(new Card(TrumpNumber.NINE, TrumpSymbol.CLOVER));
        gamer.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        gamer.receive(new Card(TrumpNumber.TWO, TrumpSymbol.CLOVER));

        assertThat(gamer.isImpossibleHit()).isTrue();
    }
}
