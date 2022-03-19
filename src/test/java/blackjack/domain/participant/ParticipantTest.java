package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParticipantTest {

    @Test
    @DisplayName("이름이 공백이면 예외 발생")
    void Blank_Name_Exception() {
        assertThatThrownBy(() ->
            Participant.createPlayer("")
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 이름은 공백일 수 없습니다.");
    }

    @Test
    @DisplayName("이름에 특수문자가 포한되면 예외 발생")
    void Unavailable_Name_Exception() {
        assertThatThrownBy(() ->
            Participant.createPlayer("@as!")
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 이름에 특수문자가 포함될 수 없습니다.");
    }

    @Test
    @DisplayName("베팅 금액을 잘 입력받는지")
    void Bet_Money() {
        Participant player = Participant.createPlayer("panda");

        player.bet(10000);

        assertThat(player.getBetting()).isEqualTo(10000);
    }

    @Test
    @DisplayName("카드가 한장 추가되는지")
    void Receive_Card() {
        Participant player = Participant.createPlayer("yaho");
        CardDeck cardDeck = new CardDeck();

        player.receiveCard(cardDeck.pickCard());

        assertThat(player.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드의 총합이 21이상인지 판정")
    void Is_Over_21() {
        Participant player = Participant.createPlayer("yaho");

        player.receiveCard(new Card("8스페이드", 8));
        player.receiveCard(new Card("9하트", 9));
        player.receiveCard(new Card("5다이아몬드", 5));
        
        assertThat(player.isOverMaxScore()).isEqualTo(true);
    }
}
