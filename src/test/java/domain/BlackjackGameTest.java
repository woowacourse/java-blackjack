package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.card.Cards;
import domain.name.Name;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.BettingMoney;
import vo.Profit;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BlackjackGameTest {
    Dealer dealer = new Dealer(getCards());

    private static Cards getCards() {
        Cards cards = new Cards(
                List.of(
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.THREE),
                        new Card(CardShape.HEART, CardNumber.EIGHT),
                        new Card(CardShape.HEART, CardNumber.SEVEN),
                        new Card(CardShape.SPADE, CardNumber.ACE),
                        new Card(CardShape.HEART, CardNumber.KING),
                        new Card(CardShape.CLOVER, CardNumber.SIX),
                        new Card(CardShape.SPADE, CardNumber.EIGHT),
                        new Card(CardShape.SPADE, CardNumber.EIGHT),
                        new Card(CardShape.CLOVER, CardNumber.SEVEN)
                )
        );
        return cards;
    }

    @DisplayName("주어진 횟수만큼 딜러와 플레이어에게 카드를 나누어준다.")
    @Test
    void handOutCards() {
        Player player = Player.register(new Name("hotea"), new BettingMoney(5000));
        Players players = new Players(List.of(player));
        BlackjackGame game = new BlackjackGame(dealer, players);
        game.handOutCards(dealer, 2);
        game.handOutCards(player, 2);
        assertAll(
                () -> assertThat(dealer.score()).isEqualTo(15),
                () -> assertThat(player.score()).isEqualTo(14)
        );
    }


    @DisplayName("딜러와 플레이어의 수익을 알 수 있다.")
    @Test
    void resultsOfParticipants() {
        Player hotea = Player.register(new Name("hotea"), new BettingMoney(5000));
        Player tobi = Player.register(new Name("tobi"), new BettingMoney(3000));
        Player pobi = Player.register(new Name("pobi"), new BettingMoney(10000));
        Player zeus = Player.register(new Name("zeus"), new BettingMoney(7000));
        Map<Participant, Profit> expected = new LinkedHashMap<>();
        expected.put(dealer, new Profit(7500));
        expected.put(hotea, new Profit(-5000));
        expected.put(tobi, new Profit(4500));
        expected.put(pobi, new Profit(0));
        expected.put(zeus, new Profit(-7000));

        BlackjackGame blackjackGame = new BlackjackGame(dealer, new Players(List.of(hotea, tobi, pobi, zeus)));
        blackjackGame.prepare();
        assertThat(blackjackGame.resultsOfParticipants().getResult()).isEqualTo(expected);
    }


}
