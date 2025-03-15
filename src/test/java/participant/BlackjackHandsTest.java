package participant;


import static org.assertj.core.api.Assertions.assertThat;

import domain.blackjackgame.CardValue;
import domain.blackjackgame.Suit;
import domain.blackjackgame.TrumpCard;
import domain.participant.BlackjackHands;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BlackjackHandsTest {

    @Test
    void 카드의_합을_계산한다() {
        List<TrumpCard> trumpCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.A),
                new TrumpCard(Suit.CLOVER, CardValue.TEN));
        BlackjackHands blackjackHands = new BlackjackHands(trumpCards);
        assertThat(blackjackHands.calculateCardSum()).isEqualTo(21);
    }

    @Test
    void 에이스가_버스트인_경우_최적의_값을_계산한다() {
        List<TrumpCard> trumpCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.A),
                new TrumpCard(Suit.CLOVER, CardValue.TEN), new TrumpCard(Suit.CLOVER, CardValue.NINE));
        BlackjackHands blackjackHands = new BlackjackHands(trumpCards);
        assertThat(blackjackHands.calculateCardSum()).isEqualTo(20);
    }

    @Test
    void 버스트일시_반환_테스트() {
        List<TrumpCard> trumpCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.A),
                new TrumpCard(Suit.CLOVER, CardValue.TEN), new TrumpCard(Suit.CLOVER, CardValue.NINE),
                new TrumpCard(Suit.CLOVER, CardValue.EIGHT));
        BlackjackHands blackjackHands = new BlackjackHands(trumpCards);
        assertThat(blackjackHands.isBust()).isEqualTo(true);
    }

    @Test
    void 버스트가_아닐시_반환_테스트() {
        List<TrumpCard> trumpCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.FOUR),
                new TrumpCard(Suit.CLOVER, CardValue.TEN));
        BlackjackHands blackjackHands = new BlackjackHands(trumpCards);
        assertThat(blackjackHands.isBust()).isEqualTo(false);
    }
}

