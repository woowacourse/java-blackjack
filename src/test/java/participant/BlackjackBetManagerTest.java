package participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.blackjackgame.BlackjackDeck;
import domain.blackjackgame.BlackjackGame;
import domain.blackjackgame.CardValue;
import domain.blackjackgame.Suit;
import domain.blackjackgame.TrumpCard;
import domain.participant.BetManager;
import domain.strategy.BlackjackDrawStrategy;
import domain.strategy.DeckGenerator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import strategy.TestDeckGenerateStrategy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BlackjackBetManagerTest {

    @Test
    void 블랙잭_게임을_기반으로_블랙잭_결과를_계산한다() {
        List<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.DIAMOND, CardValue.J),
                        new TrumpCard(Suit.SPADE, CardValue.EIGHT), new TrumpCard(Suit.SPADE, CardValue.J)));
        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new TestDeckGenerateStrategy(trumpCards));

        BlackjackGame blackjackGame = new BlackjackGame(List.of("투다"), deck);
        BetManager betManager = new BetManager(blackjackGame);
        Map<String, Double> blackjackBettingResult = betManager.blackjackBettingResult();
        Double earnMoney = blackjackBettingResult.get("투다");
        assertThat(earnMoney).isEqualTo(0);
    }
}



