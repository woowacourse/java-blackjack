package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class CardDeckTest {

    @Test
    public void 카드덱_생성_테스트(){
        assertThat(CardDeck.get().size())
                .isEqualTo(52);
    }
}