import static org.assertj.core.api.Assertions.assertThat;

import config.CardDeckFactory;
import domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckFactoryTest {
    @Test
    @DisplayName("카드 덱 생성 테스트")
    void createCardDeckTest(){
        //given
        CardDeckFactory cardDeckFactory = new CardDeckFactory();

        assertThat(cardDeckFactory.create()).isInstanceOf(CardDeck.class);
    }
}
