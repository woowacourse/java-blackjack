package participant.player;

import card.Card;
import card.CardDeck;
import card.CardNumber;
import card.CardPattern;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        String name = "pola";

        player = new Player(new CardDeck().firstCards(), new Name(name), new BetMoney(3000));
    }

    @DisplayName("Player가 들고 있는 카드가 최고점을 넘을경우 true를 return한다.")
    @Test
    void isOverMaxGameScore() {
        Card kingCard = new Card(CardNumber.KING, CardPattern.CLOVER_PATTERN);
        Card queenCard = new Card(CardNumber.QUEEN, CardPattern.CLOVER_PATTERN);
        Card jackCard = new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN);

        player.hit(kingCard);
        player.hit(queenCard);
        player.hit(jackCard);

        Assertions.assertThat(player.isBust()).isTrue();
    }

    @DisplayName("Player가 들고 있는 카드가 최고점을 넘지 않을경우 false를 return한다.")
    @Test
    void isNotOverMaxGameScore() {
        Assertions.assertThat(player.isBust()).isFalse();
    }

    @DisplayName("게임에 참가하는 경우, 카드 두장을 받고 시작한다.")
    @Test
    void getCardWhenPlay() {
        Assertions.assertThat(player.getCards().countCard()).isEqualTo(2);
    }
}
