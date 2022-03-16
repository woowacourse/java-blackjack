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

    @DisplayName("카드 숫자의 합이 21이상 이면 true를 반환한다.")
    @Test
    void finish_true() {
        Player player = new Player("리버");
        player.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.receive(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));
        player.receive(new Card(TrumpNumber.THREE, TrumpSymbol.HEART));

        assertThat(player.isFinish()).isTrue();
    }

    @DisplayName("카드 숫자의 합이 21미만 이면 false를 반환한다.")
    @Test
    void finish_false() {
        Player player = new Player("리버");
        player.receive(new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER));
        player.receive(new Card(TrumpNumber.FIVE, TrumpSymbol.HEART));

        assertThat(player.isFinish()).isFalse();
    }

    @DisplayName("카드 숫자의 합이 21을 초과하면 true를 반환한다.")
    @Test
    void bust_false() {
        Participant player = new Player("리버");
        player.receive(new Card(TrumpNumber.SEVEN, TrumpSymbol.CLOVER));
        player.receive(new Card(TrumpNumber.FIVE, TrumpSymbol.HEART));
        player.receive(new Card(TrumpNumber.JACK, TrumpSymbol.HEART));

        assertThat(player.isBust()).isTrue();
    }

    @DisplayName("카드 숫자의 합이 21이하 이면 false를 반환한다.")
    @Test
    void bust_true() {
        Participant player = new Player("리버");
        player.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.receive(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));
        player.receive(new Card(TrumpNumber.THREE, TrumpSymbol.HEART));

        assertThat(player.isBust()).isFalse();
    }

    @DisplayName("카드 숫자의 합이 21이고 카드가 두장이면 true를 반환한다.")
    @Test
    void blackjack_true() {
        Participant player = new Player("리버");
        player.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.receive(new Card(TrumpNumber.ACE, TrumpSymbol.HEART));

        assertThat(player.isBlackjack()).isTrue();
    }

    @DisplayName("카드 숫자의 합이 21이고 카드가 두장이 아니면 false를 반환한다.")
    @Test
    void blackjack_false() {
        Participant player = new Player("리버");
        player.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.receive(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));
        player.receive(new Card(TrumpNumber.THREE, TrumpSymbol.HEART));

        assertThat(player.isBlackjack()).isFalse();
    }

    @DisplayName("카드 숫자의 합이 21이 아니고 카드가 두장이면 false를 반환한다.")
    @Test
    void blackjack_false_2() {
        Participant player = new Player("리버");
        player.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.receive(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));

        assertThat(player.isBust()).isFalse();
    }

    @DisplayName("다른 참가자보다 카드숫자 합이 높으면 true를 반환한다.")
    @Test
    void win_true(){
        Participant player = new Player("리버");
        player.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.receive(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));

        Participant otherPlayer = new Player("포키");
        otherPlayer.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        otherPlayer.receive(new Card(TrumpNumber.SEVEN, TrumpSymbol.HEART));

        assertThat(player.isWinBy(otherPlayer)).isTrue();
    }

    @DisplayName("다른 참가자보다 카드숫자 합이 낮으면 false를 반환한다.")
    @Test
    void win_false(){
        Participant player = new Player("리버");
        player.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.receive(new Card(TrumpNumber.SEVEN, TrumpSymbol.HEART));

        Participant otherPlayer = new Player("포키");
        otherPlayer.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        otherPlayer.receive(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));

        assertThat(player.isWinBy(otherPlayer)).isFalse();
    }
}
