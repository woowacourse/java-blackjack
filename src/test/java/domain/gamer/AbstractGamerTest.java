package domain.gamer;

import domain.card.providable.CardDeck;
import domain.gamer.action.TurnActions;
import domain.gamer.action.YesNo;
import domain.result.BlackJackRule;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractGamerTest {
    @Test
    void 처음에_카드_뽑기_테스트() {
        Player player = new Player(new Name("phobi"));
        CardDeck cardDeck = new CardDeck();

        player.drawInitialCards(cardDeck);

        assertThat(player.getHand().size()).isEqualTo(2);
    }

    @Test
    void 턴_진행_테스트() {
        BlackJackRule blackJackRule = new BlackJackRule();
        Player player = new Player(new Name("phobi"));
        CardDeck cardDeck = new CardDeck();

        player.playTurn(cardDeck, blackJackRule, new TurnActions(gamer -> YesNo.NO, gamer -> {
        }));
        assertThat(player.getHand().size()).isEqualTo(0);

        player.playTurn(cardDeck, blackJackRule, new TurnActions(gamer -> YesNo.YES, gamer -> {
        }));
        assertThat(player.getHand().size()).isGreaterThan(1);
    }

//    @Test
//    void 버스트_테스트() {
//        BlackJackRule blackJackRule = new BlackJackRule();
//        Hand hand = new Hand();
//
//        hand.drawCard(() -> Card.of(Rank.QUEEN, Suit.CLOVER));
//        hand.drawCard(() -> Card.of(Rank.KING, Suit.CLOVER));
//        Player player = new Player(new Name("phobi"), hand);
//        assertThat(player.isBurst(blackJackRule)).isFalse();
//
//        hand.drawCard(() -> Card.of(Rank.JACK, Suit.CLOVER));
//        Player player2 = new Player(new Name("phobi"), hand);
//        assertThat(player2.isBurst(blackJackRule)).isTrue();
//    }
}
