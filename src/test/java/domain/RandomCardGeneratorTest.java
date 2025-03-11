package domain;

import static domain.card.CardScore.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.*;
import org.junit.jupiter.api.Test;

public class RandomCardGeneratorTest {
    @Test
    void 랜덤_카드를_생성한다() {
        //given
        //when
        final RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
        final Card card = randomCardGenerator.peekCard();

        //then
        assertThat(card).isInstanceOf(Card.class);
    }

    @Test
    void 카드를_모두_소진한_경우_에러가_발생한다(){
        CardGenerator cardGenerator = new RandomCardGenerator();
        for(int i=0; i<52; i++){
            cardGenerator.peekCard();
        }

        assertThatThrownBy(cardGenerator::peekCard).isInstanceOf(IllegalStateException.class);
    }
}
