package domain;

import static org.assertj.core.api.Assertions.assertThat;

import constant.CardNumber;
import constant.Emblem;
import java.util.ArrayDeque;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Nested
    @DisplayName("플레이어 카드 뽑기")
    class PickCard {

        @DisplayName("플레이어는 덱으로부터, 올바르게 카드를 뽑는다.")
        @Test
        public void pickCard() throws Exception {
            // given
            final Player player = new Player();
            final List<Card> cards = List.of(new Card(CardNumber.ACE, Emblem.CLUB));
            final Deck deck = new Deck(new ArrayDeque<>(cards));

            // when
            player.pickCard(deck);

            // then
            assertThat(player.cardHand().hand()).isNotEmpty();
        }
        
    }

}
