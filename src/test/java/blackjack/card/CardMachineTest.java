package blackjack.card;

import blackjack.ConstantFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CardMachineTest {

    @Test
    @DisplayName("초기화된 카드 머신은 플레이어 숫자가 최소 카드 덱 개수보다 적다면 {최소 카드 덱 개수 * 카드 모양 * 카드 등급}장의 카드를 가진다")
    void initializedCardMachineShouldHaveMinDeckWhenPlayersAreLessThanMinDeck() {
        // given
        int minCardDeckCount = ConstantFixture.getMinCardDeckCount("테스트 전용");
        int playerCount = minCardDeckCount - 1;

        int expectedCardCount =
                minCardDeckCount * CardSuit.values().length * CardRank.values().length;

        // when
        CardMachine cardMachine = CardMachine.initialize(playerCount);

        // then
        assertThat(cardMachine.getCards().size()).isEqualTo(expectedCardCount);
    }

    @Test
    @DisplayName("초기화된 카드 머신은 플레이어 숫자가 최소 카드 덱 개수보다 많다면 {플레이어 숫자 * 카드 모양 * 카드 등급}장의 카드를 가진다")
    void initializedCardMachineShouldHavePlayerCountDeckWhenPlayersExceedMinDeck() {
        // given
        int minCardDeckCount = ConstantFixture.getMinCardDeckCount("테스트 전용");
        int playerCount = minCardDeckCount + 1;

        int expectedCardCount =
                playerCount * CardSuit.values().length * CardRank.values().length;

        // when
        CardMachine cardMachine = CardMachine.initialize(playerCount);

        // then
        assertThat(cardMachine.getCards().size()).isEqualTo(expectedCardCount);
    }

    @Test
    @DisplayName("카드 머신에서 카드를 한 장 뽑으면 카드 개수가 1 줄어야 한다")
    void drawingCardShouldDecreaseCardCount() {
        // given
        CardMachine cardMachine = CardMachine.initialize(1);
        int initialSize = cardMachine.getCards().size();

        // when
        Card drawnCard = cardMachine.drawCard();

        // then
        assertAll(
                () -> assertThat(drawnCard).isNotNull(),
                () -> assertThat(cardMachine.getCards().size()).isEqualTo(initialSize - 1)
        );
    }

    @Test
    @DisplayName("초기화된 카드 머신의 카드는 섞여 있어야 한다, 하지만 우연히 섞인 모습이 같을 수 있다")
    void initializedCardMachineShouldHaveShuffledCards() {
        // given
        CardMachine cardMachine1 = CardMachine.initialize(1);
        CardMachine cardMachine2 = CardMachine.initialize(1);

        // when
        List<Card> cards1 = new ArrayList<>(cardMachine1.getCards().getCards());
        List<Card> cards2 = new ArrayList<>(cardMachine2.getCards().getCards());

        // then
        assertThat(cards1).isNotEqualTo(cards2);
    }
}
