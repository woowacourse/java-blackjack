package domain;

import static domain.card.CardScore.*;
import static domain.card.CardType.*;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardScore;
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
}
