package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.card.Cards;
import domain.name.Name;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.BettingMoney;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BlackjackGameTest {
    Dealer dealer = new Dealer(getCards());
    Player player = Player.register(new Name("hotea"), new BettingMoney(5000));
    Players players = new Players(List.of(player));

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

    @DisplayName("주어진 횟수만큼 딜러와 플레이어에게 카드를 나누어준다.")
    @Test
    void handOutCards() {
        BlackjackGame game = new BlackjackGame(dealer, players);
        game.handOutCards(dealer, 2);
        game.handOutCards(player, 2);
        assertAll(
                () -> assertThat(dealer.score()).isEqualTo(15),
                () -> assertThat(player.score()).isEqualTo(5)
        );
    }
}
