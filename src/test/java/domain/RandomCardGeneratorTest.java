package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardScore;
import domain.card.RandomCardGenerator;
import domain.fake.AceCardGenerator;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RandomCardGeneratorTest {


    @Test
    void 랜덤_카드를_생성한다() {
        //given
        //when
        final RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
        final List<Card> cards = randomCardGenerator.generate();

        //then
        assertThat(cards).isNotEmpty();

    }

    @Test
    void 다이아_에이스를_생성한다() {
        AceCardGenerator aceCardGenerator = new AceCardGenerator();
        final List<Card> cards = aceCardGenerator.generate();

        assertThat(cards.getFirst().score()).isEqualTo(CardScore.ACE);
    }

}
