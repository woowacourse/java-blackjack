package service;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.CardDeck;
import infra.CardShuffler;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackServiceTest {

    private final BlackjackService blackjackService = new BlackjackService(new FakeCardShuffler());

    @Test
    @DisplayName("카드덱에서 지정한 수만큼의 카드를 뽑는다.")
    public void 카드_뽑기_성공() throws Exception {
        // given
        CardDeck cardDeck = CardDeck.initCardDeck();
        int originCount = cardDeck.getDeckSize();
        int drawCount = 2;

        // when
        List<Card> cards = blackjackService.drawCard(cardDeck, drawCount);

        // then
        assertThat(cards).hasSize(drawCount);
        assertThat(cardDeck.getDeckSize()).isEqualTo(originCount - drawCount);
    }

    static class FakeCardShuffler implements CardShuffler {

        @Override
        public int shuffleCardDeck(int deckSize) {
            return 0;
        }
    }
}