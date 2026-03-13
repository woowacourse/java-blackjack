package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.enums.CardRank;
import domain.enums.CardShape;
import testutil.PlayerTestUtil;
import utils.generator.CardsGenerator;
import utils.generator.ShuffledCardsGenerator;

class DeckTest {

    @DisplayName("처음 전쳬 카드는 52장 생성되야한다.")
    @Test
    void 처음_전쳬_카드_개수_테스트() {
        CardsGenerator cardsGenerator = new ShuffledCardsGenerator();

        Deck deck= new Deck(cardsGenerator);

        assertThat(deck.getCards().size()).isEqualTo(52);
    }

    @DisplayName("셔플된 카드 순서대로 배정된다.")
    @Test
    void 셔플_카드_배부_테스트() {
        CardsGenerator fakeCardsGenerator = new PlayerTestUtil.FakeShuffledCardsGenerator();

        Deck deck= new Deck(fakeCardsGenerator);

        assertThat(deck.pop()).isEqualTo(new Card(CardShape.SPADE, CardRank.ACE));
        assertThat(deck.pop()).isEqualTo(new Card(CardShape.SPADE, CardRank.TWO));
    }

}