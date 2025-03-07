package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardType;
import domain.card.RandomCardGenerator;
import domain.fake.AceCardGenerator;
import org.junit.jupiter.api.Test;

public class RandomCardGeneratorTest {


    @Test
    void 랜덤_카드를_생성한다() {
        //given
        //when
        final RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
        final Card card = randomCardGenerator.generate();

        //then
        assertThat(card).isInstanceOf(Card.class);

    }

    @Test
    void 다이아_에이스를_생성한다() {
        AceCardGenerator aceCardGenerator = new AceCardGenerator();
        final Card card = aceCardGenerator.generate();

        assertThat(card.getType()).isEqualTo(CardType.DIAMOND_ACE);
    }

}
