package domain;

import static org.assertj.core.api.Assertions.assertThat;

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
}
