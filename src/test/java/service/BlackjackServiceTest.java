package service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Card;
import domain.CardRank;
import domain.CardShape;
import domain.Cards;
import domain.Players;
import utils.generator.CardsGenerator;
import utils.generator.ShuffledCardsGenerator;

class BlackjackServiceTest {
    private BlackjackService blackjackService;

    @BeforeEach
    void setUp() {
        BlackjackService blackjackService = new BlackjackService();
        this.blackjackService = blackjackService;
    }

    @DisplayName("처음 전쳬 카드는 52장 생성되야한다.")
    @Test
    void 처음_전쳬_카드_개수_테스트() {
        Cards cards = blackjackService.generateCards(new ShuffledCardsGenerator());

        assertThat(cards.getCards().size()).isEqualTo(52);
    }

    @DisplayName("처음에 카드 2장씩 베부된다.")
    @Test
    void 초기_카드_2장_배부_테스트() {
        Cards cards = blackjackService.generateCards(new ShuffledCardsGenerator());
        Players playerList = blackjackService.createPlayers(List.of("요크", "아티"), cards);
        assertThat(playerList.getPlayer(0).getCards().size()).isEqualTo(2);
    }

    @DisplayName("셔플된 카드 덱에서 플레이어에게 초기 카드 2장이 순서대로 배부된다.")
    @Test
    void 셔플_카드_순서대로_배부_정상_테스트() {
        Cards cards = blackjackService.generateCards(new FakeShuffledCardsGenerator());
        Players playerList = blackjackService.createPlayers(List.of("요크", "아티"), cards);

        assertThat(playerList.getPlayer(0).getCards().getFirst()).isEqualTo(new Card(CardShape.SPADE, CardRank.ACE));
        assertThat(playerList.getPlayer(0).getCards().get(1)).isEqualTo(new Card(CardShape.SPADE, CardRank.TWO));

        assertThat(playerList.getPlayer(1).getCards().get(0)).isEqualTo(new Card(CardShape.SPADE, CardRank.THREE));
        assertThat(playerList.getPlayer(1).getCards().get(1)).isEqualTo(new Card(CardShape.SPADE, CardRank.FOUR));
    }

    public static class FakeShuffledCardsGenerator implements CardsGenerator {
        @Override
        public Cards generateShuffledCards() {
            return new Cards(
                    new ArrayList<>(List.of(new Card(CardShape.SPADE, CardRank.ACE),
                            new Card(CardShape.SPADE, CardRank.TWO),
                            new Card(CardShape.SPADE, CardRank.THREE),
                            new Card(CardShape.SPADE, CardRank.FOUR),
                            new Card(CardShape.SPADE, CardRank.FIVE))
                    ));
        }
    }

}