import domain.card.Card;
import domain.card.CardDeck;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDeckTest {

    @Test
    void 생성한_카드덱은_카드_52장을_가진다() {
        //given
        CardDeck cardDeck = new CardDeck();
        //when
        int size = cardDeck.getDeckSize();
        //then
        assertThat(size).isEqualTo(52);
    }

    @Test
    void 카드덱의_52장은_중복되지_않는다() {
        //given
        CardDeck cardDeck = new CardDeck();
        Set<Card> notDuplicatedDeck = new HashSet<>();

        //when
        notDuplicatedDeck.addAll(cardDeck.getCardDeck());

        //then
        assertThat(notDuplicatedDeck.size()).isEqualTo(52);
    }

    @Test
    void 카드덱에서_한장을_뽑으면_카드덱_사이즈가_1만큼_감소한다() {
        //given
        CardDeck cardDeck = new CardDeck();

        //when
        cardDeck.drawCard();

        //then
        assertThat(cardDeck.getDeckSize()).isEqualTo(51);
    }
}
