package domain;

import static domain.MatchResult.LOSE;
import static domain.MatchResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackResultTest {
    private Dealer dealer;
    private List<Gambler> gamblers;

    @BeforeEach
    void setup() {
        Gambler pobi = new Gambler("포비");
        Gambler dolbum = new Gambler("돌범");
        Gambler rich = new Gambler("리차드");
        gamblers = List.of(pobi, dolbum, rich);
        dealer = new Dealer("딜러");
    }

    @Test
    @DisplayName("딜러와 겜블러를 전달해서 게임 결과를 반환한다")
    void judgeGameResult() {
        // given
        cardSetup(dealer, gamblers);
        BlackJackResult blackJackResult = BlackJackResult.of(dealer, new Gamblers(gamblers));

        // when
        Map<MatchResult, Long> dealerResult = blackJackResult.getDealerResult();
        Map<String, MatchResult> playerResult = blackJackResult.getPlayerResult();

        // then
        assertAll(
                () -> assertThat(dealerResult.get(WIN)).isEqualTo(1),
                () -> assertThat(dealerResult.get(LOSE)).isEqualTo(2),
                () -> assertThat(playerResult.get("포비")).isEqualTo(LOSE),
                () -> assertThat(playerResult.get("돌범")).isEqualTo(WIN),
                () -> assertThat(playerResult.get("리차드")).isEqualTo(WIN)
        );
    }

    private void cardSetup(Dealer dealer, List<Gambler> gamblers) {
        dealer.addCard(Card.of(Suit.SPADES, Denomination.JACK));
        dealer.addCard(Card.of(Suit.HEARTS, Denomination.THREE)); // 13

        gamblers.get(0).addCard(Card.of(Suit.SPADES, Denomination.JACK));
        gamblers.get(0).addCard(Card.of(Suit.CLUBS, Denomination.TWO));

        gamblers.get(1).addCard(Card.of(Suit.CLUBS, Denomination.JACK));
        gamblers.get(1).addCard(Card.of(Suit.CLUBS, Denomination.ACE));

        gamblers.get(2).addCard(Card.of(Suit.CLUBS, Denomination.ACE));
        gamblers.get(2).addCard(Card.of(Suit.CLUBS, Denomination.TEN));
        gamblers.get(2).addCard(Card.of(Suit.SPADES, Denomination.ACE));
        gamblers.get(2).addCard(Card.of(Suit.SPADES, Denomination.ACE));
    }
}
