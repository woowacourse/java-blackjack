package participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.blackjackgame.CardValue;
import domain.blackjackgame.Suit;
import domain.blackjackgame.TrumpCard;
import domain.participant.BetManager;
import domain.participant.BlackjackHands;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BlackjackBetManagerTest {

    @Test
    void 이름과_카드로_플레이어의_수익_금액을_계산한다() {
        int money = 1000;
        BetManager betManager = new BetManager(List.of("투다"), List.of(1000));
        List<TrumpCard> trumpCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.TEN),
                new TrumpCard(Suit.CLOVER, CardValue.NINE),
                new TrumpCard(Suit.CLOVER, CardValue.A));
        List<TrumpCard> dealerCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.FIVE),
                new TrumpCard(Suit.CLOVER, CardValue.TWO),
                new TrumpCard(Suit.CLOVER, CardValue.EIGHT));
        BlackjackHands playerCardSum = new BlackjackHands(trumpCards);
        BlackjackHands dealerCardSum = new BlackjackHands(dealerCards);
        assertThat(betManager.playerProfit("투다", playerCardSum, dealerCardSum)).isEqualTo(money);
    }
}



