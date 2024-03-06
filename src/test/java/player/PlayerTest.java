package player;

import card.Card;
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
        Name name = new Name("pola");

        player = new Player(name);
    }

    @DisplayName("Player가 들고 있는 카드가 최고점을 넘을경우 true를 return한다.")
    @Test
    void isOverMaxGameScore() {
        Card kingCard = new Card(CardNumber.KING, CardPattern.CLOVER_PATTERN);
        Card queenCard = new Card(CardNumber.QUEEN, CardPattern.CLOVER_PATTERN);
        Card jackCard = new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN);

        player.receiveCard(kingCard);
        player.receiveCard(queenCard);
        player.receiveCard(jackCard);

        Assertions.assertThat(player.isOverMaxCardScore()).isTrue();
    }

    @DisplayName("Player가 들고 있는 카드가 최고점을 넘지 않을경우 false를 return한다.")
    @Test
    void isNotOverMaxGameScore() {
        Card kingCard = new Card(CardNumber.KING, CardPattern.CLOVER_PATTERN);

        player.receiveCard(kingCard);

        Assertions.assertThat(player.isOverMaxCardScore()).isFalse();
    }
}
