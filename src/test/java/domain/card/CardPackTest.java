package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class CardPackTest {

    @Test
    void 카드팩_상단의_한장을_꺼낸다() {
        //given
        CardPack pack = new CardPack();
        int beforeSize = pack.size();

        //when
        Card card = pack.poll();
        int afterSize = pack.size();

        assertAll(
            () -> assertThat(card).isNotNull(),
            () -> assertThat(afterSize).isEqualTo(beforeSize - 1)
        );
    }
}
