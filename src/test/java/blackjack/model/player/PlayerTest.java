package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;

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

    @DisplayName("Card를 받으면 새로운 Player 인스턴스를 반환한다.")
    @Test
    void drawSeveral_new_Participant() {
        Participant player = new Player("리버");
        Participant otherDealer = player.receive(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));

        assertThat(player).isNotEqualTo(otherDealer);
    }

    @DisplayName("Card를 hit하면 새로운 Player 인스턴스를 반환한다.")
    @Test
    void draw_new_Participant() {
        CardDeck deck = new CardDeck();
        Participant player = new Player("리버");
        Participant otherDealer = player.hit(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));

        assertThat(player).isNotEqualTo(otherDealer);
    }
}
