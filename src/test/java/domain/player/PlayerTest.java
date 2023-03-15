package domain.player;

import domain.card.Card;
import domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.card.CardValue.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Player 은")
class PlayerTest {

    final Name name = new Name("name");

    final Player player = new Player(name) {
        @Override
        public boolean canHit() {
            return false;
        }

        @Override
        public List<Card> faceUpFirstDeal() {
            return null;
        }
    };

    @Test
    void 추상클래스다() {
        //when & then
        assertThat(Player.class).isAbstract();
    }

    @Test
    void 이름과_area_가진다() {
        // when & then
        assertDoesNotThrow(() -> new Player(name) {
            @Override
            public boolean canHit() {
                return false;
            }

            @Override
            public List<Card> faceUpFirstDeal() {
                return null;
            }
        });
    }

    @Test
    void 카드를_추가할_수_있다() {
        // when
        final int beforeSize = player.hand().cards().size();
        player.hit(new Card(CardShape.SPADE, TEN));

        // then
        assertThat(player.hand.cards()).hasSize(beforeSize + 1);
    }
}
