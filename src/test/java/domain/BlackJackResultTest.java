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
import dto.ResultDto;
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
        Map<String, ResultDto> blackjackResult = blackJackResult.getBlackjackResult();
        ResultDto dealerResultDto = blackjackResult.get("딜러");
        ResultDto pobiResultDto = blackjackResult.get("포비");
        ResultDto dolbumResultDto = blackjackResult.get("돌범");
        ResultDto richardResultDto = blackjackResult.get("리차드");

        // then
        assertAll(
                () -> assertThat(dealerResultDto.getMatchResults()).isEqualTo(List.of(WIN, LOSE, LOSE)),
                () -> assertThat(pobiResultDto.getMatchResults()).isEqualTo(List.of(LOSE)),
                () -> assertThat(dolbumResultDto.getMatchResults()).isEqualTo(List.of(WIN)),
                () -> assertThat(richardResultDto.getMatchResults()).isEqualTo(List.of(WIN))
        );
    }

    private void cardSetup(Dealer dealer, List<Gambler> gamblers) {
        dealer.addCard(Card.of(Suit.SPADES, Denomination.JACK));
        dealer.addCard(Card.of(Suit.HEARTS, Denomination.THREE));

        gamblers.get(0).addCard(Card.of(Suit.SPADES, Denomination.JACK));
        gamblers.get(0).addCard(Card.of(Suit.CLUBS, Denomination.TWO));

        gamblers.get(1).addCard(Card.of(Suit.CLUBS, Denomination.JACK));
        gamblers.get(1).addCard(Card.of(Suit.CLUBS, Denomination.ACE));

        gamblers.get(2).addCard(Card.of(Suit.CLUBS, Denomination.ACE));
        gamblers.get(2).addCard(Card.of(Suit.CLUBS, Denomination.TEN));
    }
}
