package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.Map;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.card.Cards;
import model.player.Dealer;
import model.player.Participant;
import model.player.Player;
import model.player.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {

    @DisplayName("참가자에게 카드를 준다.")
    @Test
    void offerCardToPlayers() {
        Players players = new Players(List.of(new Participant("배키"), new Dealer()), new Cards());
        BlackJack blackJack = new BlackJack(players);
        blackJack.offerCardToPlayers(2);
        List<Player> result = players.getPlayers();

        assertAll(
                () -> assertThat(result.get(0).getCards()).hasSize(2),
                () -> assertThat(result.get(1).getCards()).hasSize(2)
        );
    }


    @DisplayName("이름이 일치하는 참가자에게만 카드를 줄 수 있다.")
    @Test
    void offerCardToPlayer() {
        Players players = new Players(List.of(new Participant("배키"), new Dealer()), new Cards());
        BlackJack blackJack = new BlackJack(players);
        blackJack.offerCardToPlayer("배키", 1);
        List<Player> result = players.getPlayers();

        assertAll(
                () -> assertThat(result.get(0).getCards()).hasSize(1),
                () -> assertThat(result.get(1).getCards()).hasSize(0)
        );
    }

    @DisplayName("카드의 합이 21을 초과하면 패한다.")
    @Test
    void findLoseResult() {
        Participant participant = new Participant("배키");
        participant.addCard(new Card(CardShape.SPACE, CardNumber.NINE));
        participant.addCard(new Card(CardShape.DIAMOND, CardNumber.NINE));

        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardShape.SPACE, CardNumber.EIGHT));
        dealer.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));

        Players players = new Players(List.of(participant, dealer), new Cards());
        BlackJack blackJack = new BlackJack(players);

        Map<Player, GameResult> result = blackJack.findResult();
        Assertions.assertThat(result).isEqualTo(Map.of(participant, GameResult.WIN));
    }

    @DisplayName("참가자 카드의 합이 딜러와 동일하면 무승부다.")
    @Test
    void findDrawResult() {
        Participant participant = new Participant("배키");
        participant.addCard(new Card(CardShape.SPACE, CardNumber.NINE));
        participant.addCard(new Card(CardShape.DIAMOND, CardNumber.NINE));

        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardShape.SPACE, CardNumber.NINE));
        dealer.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));

        Players players = new Players(List.of(participant, dealer), new Cards());
        BlackJack blackJack = new BlackJack(players);

        Map<Player, GameResult> result = blackJack.findResult();
        Assertions.assertThat(result).isEqualTo(Map.of(participant, GameResult.DRAW));
    }

    @DisplayName()
}
