package blackjack;

import blackjack.domain.*;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    private Dealer dealer;
    private User user;
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
        dealer = Dealer.getDealer();
        user = new User("bossdog");
    }

    @DisplayName("딜러와 유저의 최종 상태가 NONE일 경우 SCORE 기반 결과 계산")
    @Test
    void calculateGameResultByScore() {
        for (int i = 0; i < 2; i++) {
            dealer.addCard(cardDeck);
            user.addCard(cardDeck);
        }
        assertThat(GameResult.calculatePlayerResult(dealer, user)).isEqualTo(UserResult.DRAW);
    }
}
