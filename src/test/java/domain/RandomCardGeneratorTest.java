package domain;

import static domain.card.CardScore.*;
import static domain.card.CardType.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.*;
import domain.fake.AceCardGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandomCardGeneratorTest {
    @Test
    void 랜덤_카드를_생성한다() {
        //given
        //when
        final RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
        final Card card = randomCardGenerator.peekRandomCard();

        //then
        assertThat(card).isInstanceOf(Card.class);
    }

    @Test
    void 다이아_에이스를_생성한다() {
        AceCardGenerator aceCardGenerator = new AceCardGenerator();
        final Card card = aceCardGenerator.peekRandomCard();

        assertThat(card.getScore()).isEqualTo(ACE);
    }

    @Test
    void 카드를_모두_소진한_경우_에러가_발생한다(){
        CardGenerator cardGenerator = new RandomCardGenerator();
        for(int i=0; i<52; i++){
            cardGenerator.peekRandomCard();
        }

        assertThatThrownBy(cardGenerator::peekRandomCard).isInstanceOf(IllegalStateException.class);
    }
}
