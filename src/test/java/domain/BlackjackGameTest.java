package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.result.GameResultStatus.LOSE;
import static domain.result.GameResultStatus.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BlackjackGameTest {
    Dealer dealer = new Dealer(getCards());
    Player player = new Player(new Name("hotea"));
    Players players = new Players(List.of(player));

    @DisplayName("주어진 횟수만큼 딜러와 플레이어에게 카드를 나누어준다.")
    @Test
    void handOutCards() {
        BlackjackGame game = new BlackjackGame(dealer, players);
        game.handOutCards(dealer, dealer, 2);
        game.handOutCards(dealer, player, 2);
        assertAll(
                () -> assertThat(dealer.score()).isEqualTo(15),
                () -> assertThat(player.score()).isEqualTo(5)
        );
    }

    @DisplayName("딜러와 플레이어의 결과를 알 수 있다.")
    @Test
    void resultOf() {
        BlackjackGame game = new BlackjackGame(dealer, players);
        game.handOutCards(dealer, dealer, 2);
        game.handOutCards(dealer, player, 2);
        assertAll(
                () -> assertThat(game.resultsOfPlayerPosition()
                                     .getResult()
                                     .get(player)).isEqualTo(LOSE),
                () -> assertThat(game.resultsOfDealerPosition()
                                     .getResult()
                                     .get(player)).isEqualTo(WIN)
        );

    }

    private static Cards getCards() {
        Cards cards = new Cards(
                List.of(
                        new Card(CardShape.SPADE, CardNumber.THREE),
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.SPADE, CardNumber.EIGHT),
                        new Card(CardShape.CLOVER, CardNumber.SEVEN)
                )
        );
        return cards;
    }


}
