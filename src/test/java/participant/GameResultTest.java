package participant;

import card.Card;
import card.CardNumber;
import card.CardType;
import card.Cards;
import deck.Deck;
import deck.DeckCreateStrategy;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import state.running.Hit;

class GameResultTest {

    @Test
    @DisplayName("두 점수 사이 승패 여부 결정 테스트")
    void test() {
        // given
        Deck deck = new Deck(new DeckCreateStrategy() {
            @Override
            public List<Card> createAllCards() {
                return List.of(
                        new Card(CardType.DIAMOND, CardNumber.TEN),
                        new Card(CardType.DIAMOND, CardNumber.JACK),
                        new Card(CardType.SPADE, CardNumber.THREE),
                        new Card(CardType.CLOVER, CardNumber.THREE)
                );
            }
        });

        Player player = new Player("율무", new Hit(new Cards()));
        player.hit(deck.draw());
        player.hit(deck.draw());

        Dealer dealer = Dealer.prepareGame(deck);

        // when
        GameResult result = GameResult.judge(player, dealer);

        // then
        Assertions.assertThat(result)
                .isEqualTo(GameResult.WIN);
    }
}
