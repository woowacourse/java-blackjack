package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.enums.CardRank;
import domain.enums.CardShape;
import testutil.PlayerTestUtil;
import utils.generator.CardsGenerator;
import utils.generator.ShuffledCardsGenerator;

class DeckTest {
//    private Deck deck;
//
//    @BeforeEach
//    void setUp() {
//        CardsGenerator cardsGenerator = new ShuffledCardsGenerator();
//        this.deck = new Deck(cardsGenerator);
//    }

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
    }


//    @DisplayName("처음에 카드 2장씩 베부된다.")
//    @Test
//    void 초기_카드_2장_배부_테스트() {
//        Cards cards = blackjackService.generateCards(new ShuffledCardsGenerator());
//        Players playerList = blackjackService.createPlayers(List.of("요크", "아티"), cards);
//        assertThat(playerList.getPlayer(0).getCards().size()).isEqualTo(2);
//    }
//
//    @DisplayName("셔플된 카드 덱에서 플레이어에게 초기 카드 2장이 순서대로 배부된다.")
//    @Test
//    void 셔플_카드_순서대로_배부_정상_테스트() {
//        Cards cards = blackjackService.generateCards();
//        Players playerList = blackjackService.createPlayers(List.of("요크", "아티"), cards);
//
//        assertThat(playerList.getPlayer(0).getCards().getFirst()).isEqualTo(new Card(CardShape.SPADE, CardRank.ACE));
//        assertThat(playerList.getPlayer(0).getCards().get(1)).isEqualTo(new Card(CardShape.SPADE, CardRank.TWO));
//
//        assertThat(playerList.getPlayer(1).getCards().get(0)).isEqualTo(new Card(CardShape.SPADE, CardRank.THREE));
//        assertThat(playerList.getPlayer(1).getCards().get(1)).isEqualTo(new Card(CardShape.SPADE, CardRank.FOUR));
//    }


}