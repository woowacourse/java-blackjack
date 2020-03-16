package blackjack;

import blackjack.domain.*;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;
import blackjack.domain.player.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    private Dealer dealer;
    private List<Card> cards;
    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>(
                Arrays.asList(
                        new Card(Symbol.HEART, Type.TEN),
                        new Card(Symbol.SPADE, Type.QUEEN),
                        new Card(Symbol.HEART, Type.SEVEN),
                        new Card(Symbol.DIAMOND, Type.JACK),
                        new Card(Symbol.CLOVER, Type.ACE),
                        new Card(Symbol.CLOVER, Type.EIGHT)
                )
        );
        cardDeck = new CardDeck(cards);
        dealer = new Dealer();
    }

    @DisplayName("SCORE 기반 결과 계산")
    @Test
    void calculateFameResultByScore() {
        User user1 = new User("bossdog");
        User user2 = new User("yes");
        Users users = new Users(new User[]{user1, user2});
        dealer.distributeInitialCards(cardDeck);
        for (User user : users.getUsers()) {
            user.distributeInitialCards(cardDeck);
        }

        Map<User, UserResult> expectedResult = new HashMap<>();
        expectedResult.put(user1, UserResult.LOSE);
        expectedResult.put(user2, UserResult.WIN);
        assertThat(GameResult.calculateGameResult(dealer, users).getGameResult()).isEqualTo(expectedResult);
    }

    @DisplayName("딜러와 유저의 최종 상태가 NONE일 경우 SCORE 기반 결과 계산")
    @Test
    void calculateGameResultByScoreWhenBothNone() {
        User user = new User("bossdog");
        for (int i = 0; i < 2; i++) {
            dealer.addCard(cardDeck);
            user.addCard(cardDeck);
        }
        assertThat(GameResult.calculatePlayerResult(dealer, user)).isEqualTo(UserResult.DRAW);
    }
}
