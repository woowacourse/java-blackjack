package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.UserDeck;
import blackjack.domain.money.Money;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerResultTest {

    private Card one = Card.from("J", "클로버");
    private Card winCard = Card.from("A", "클로버");
    private Card tieCard = Card.from("J", "하트");
    private Card loseCard = Card.from("9", "다이아몬드");
    private UserDeck dealerDeck = new UserDeck();
    {
        dealerDeck.add(one);
    }

    @Test
    @DisplayName("딜러의 게임 결과 수합1")
    void dealerResult() {
        Dealer dealer = new Dealer(dealerDeck);
        UserDeck winDeck = new UserDeck();
        winDeck.add(winCard);
        UserDeck tieDeck = new UserDeck();
        tieDeck.add(tieCard);
        UserDeck loseDeck = new UserDeck();
        loseDeck.add(loseCard);
        List<Player> players = new ArrayList<>(
            Arrays.asList(
                new Player("win", winDeck, new Money(0)),
                new Player("win", winDeck, new Money(0)),
                new Player("tie", tieDeck, new Money(0)),
                new Player("lose", loseDeck, new Money(0))
            )
        );
        DealerResult dealerResult = new DealerResult(dealer, players);

        Map<OneGameResult, Integer> statisticResult = dealerResult.getResult();
        int win_case = statisticResult.get(OneGameResult.WIN);
        int tie_case = statisticResult.get(OneGameResult.TIE);
        int lose_case = statisticResult.get(OneGameResult.LOSE);

        assertThat(win_case).isEqualTo(1);
        assertThat(tie_case).isEqualTo(1);
        assertThat(lose_case).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러의 게임 결과 수합2")
    void dealerResult2() {
        Dealer dealer = new Dealer(dealerDeck);
        UserDeck winDeck = new UserDeck();
        winDeck.add(winCard);
        UserDeck tieDeck = new UserDeck();
        tieDeck.add(tieCard);
        UserDeck loseDeck = new UserDeck();
        loseDeck.add(loseCard);
        List<Player> players = new ArrayList<>(
            Arrays.asList(
                new Player("win", winDeck, new Money(0)),
                new Player("tie", tieDeck, new Money(0)),
                new Player("lose", loseDeck, new Money(0))
            )
        );
        DealerResult dealerResult = new DealerResult(dealer, players);

        Map<OneGameResult, Integer> statisticResult = dealerResult.getResult();
        int win_case = statisticResult.get(OneGameResult.WIN);
        int tie_case = statisticResult.get(OneGameResult.TIE);
        int lose_case = statisticResult.get(OneGameResult.LOSE);

        assertThat(win_case).isEqualTo(1);
        assertThat(tie_case).isEqualTo(1);
        assertThat(lose_case).isEqualTo(1);
    }
}
