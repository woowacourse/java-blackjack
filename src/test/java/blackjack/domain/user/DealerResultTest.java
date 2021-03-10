package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.UserDeck;
import blackjack.domain.result.DealerResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerResultTest {

    private final Card one = new Card("J", "클로버");
    private final Card winCard = new Card("A", "클로버");
    private final Card tieCard = new Card("J", "하트");
    private final Card loseCard = new Card("9", "다이아몬드");
    private final UserDeck dealerDeck = new UserDeck();
    {
        dealerDeck.add(one);
    }
    private final Money money = new Money(100);

    @Test
    @DisplayName("딜러의 게임 결과 확인")
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
                new Player(winDeck, "win", money),
                new Player(winDeck, "win", money),
                new Player(tieDeck, "tie", money),
                new Player(loseDeck, "lose", money)
            )
        );
        DealerResult dealerResult = new DealerResult(dealer, players);
        assertThat(dealerResult.getDealerResult().get("승")).isEqualTo(1);
        assertThat(dealerResult.getDealerResult().get("무")).isEqualTo(1);
        assertThat(dealerResult.getDealerResult().get("패")).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러의 게임 결과 확인")
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
                new Player(winDeck, "win", money),
                new Player(tieDeck, "tie", money),
                new Player(loseDeck, "lose", money)
            )
        );
        DealerResult dealerResult = new DealerResult(dealer, players);
        assertThat(dealerResult.getDealerResult().get("승")).isEqualTo(1);
        assertThat(dealerResult.getDealerResult().get("무")).isEqualTo(1);
        assertThat(dealerResult.getDealerResult().get("패")).isEqualTo(1);
    }
}
