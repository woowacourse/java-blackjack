package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardBoxTest {

    @Test
    void CARDBOX를_생성하면_총_52장의_카드가_생성된다(){
        //given
        CardBox cardBox = new CardBox();
        int cardNumberOfCardBox = cardBox.cardBox.size();
        //then
        assertThat(cardNumberOfCardBox).isEqualTo(52);
    }

    @Test
    void CardBox에서_꺼낸_Card는_CardBox에서_삭제된다(){
        //given
        CardBox cardBox = new CardBox();
        //when
        Card card = cardBox.get(5);
        //then
        assertThat(cardBox.cardBox.contains(card)).isFalse();
    }

}
