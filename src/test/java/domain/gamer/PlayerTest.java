package domain.gamer;

import domain.cards.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    List<Card> unShuffledDeck;

    @BeforeEach
    void setUp() {
        unShuffledDeck = Card.makeCardDeck();
    }

    @DisplayName("카드를 한 장 받는다.")
    @Test
    void hitOneCard() {
        Player player = new Player("name");
        Card newCard = unShuffledDeck.get(0);
        player.hit(newCard);

        assertThat(player.getHand()).contains(newCard);
    }

    @DisplayName("패배(Bust)하지 않는 상황인지 알려준다.")
    @Test
    void checkUnderThreshold() {
        Player player = new Player("name");
        player.hit(unShuffledDeck.get(1));
        player.hit(unShuffledDeck.get(2));
        assertThat(player.canHit()).isTrue();
    }

    @DisplayName("패배(Bust)한 상황인지 알려준다.")
    @Test
    void checkOverThreshold() {
        Player player = new Player("name");
        player.hit(unShuffledDeck.get(1));
        player.hit(unShuffledDeck.get(9));
        player.hit(unShuffledDeck.get(9));

        assertThat(player.canHit()).isFalse();
    }
}
