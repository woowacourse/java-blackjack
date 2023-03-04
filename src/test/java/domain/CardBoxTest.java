package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/04
 */
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardBoxTest {

    @Test
    void 원하는_인덱스의_카드를_반환하고_리스트에서_제거한다() {
        CardBox cardBox = new CardBox();

        assertThat(cardBox.get(0)).isEqualTo(new Card("A스페이드", 11));
        assertThat(cardBox.cardBox.size()).isEqualTo(51);
    }
}
