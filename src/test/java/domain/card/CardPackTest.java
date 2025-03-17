package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class CardPackTest {

    @Test
    void 카드팩_상단의_한장을_꺼낸다() {
        //given
        CardPack pack = CardPack.of(List.of(Card.DIAMOND_J, Card.SPADE_2, Card.SPADE_3));

        //when
        Card card = pack.poll();

        //then
        assertThat(card).isEqualTo(Card.DIAMOND_J);
    }

    @Test
    void 카드팩의_카드가_소진되면_예외가_발생한다() {
        //given
        CardPack pack = CardPack.of(List.of(Card.DIAMOND_J));
        pack.poll();

        //when
        //then
        assertThatThrownBy(pack::poll).isInstanceOf(IllegalStateException.class);
    }
}
