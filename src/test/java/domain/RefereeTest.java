package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Emblem;
import domain.card.Grade;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.GameResult;
import domain.result.Referee;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RefereeTest {

    @Test
    void 딜러와_참여중인_모든_플레이어의_승패_여부를_판정한다() {
        // given
        List<Card> testCard = List.of(
                new Card(Emblem.CLOVER, Grade.ACE), new Card(Emblem.SPADE, Grade.JACK),
                new Card(Emblem.HEART, Grade.JACK), new Card(Emblem.DIAMOND, Grade.SEVEN),
                new Card(Emblem.SPADE, Grade.SEVEN), new Card(Emblem.HEART, Grade.SEVEN),
                new Card(Emblem.DIAMOND, Grade.QUEEN), new Card(Emblem.HEART, Grade.KING),
                new Card(Emblem.CLOVER, Grade.NINE), new Card(Emblem.SPADE, Grade.EIGHT)
        );
        Deck deck = new Deck(new FixShuffleStrategy(testCard));

        Referee referee = new Referee();
        Player tori = new Player("토리", 10000);
        tori.initHand(deck);
        Player eden = new Player("에덴", 20000);
        eden.initHand(deck);
        Player ian = new Player("이안", 30000);
        ian.initHand(deck);
        Player nuynung = new Player("녀녕", 40000);
        nuynung.initHand(deck);

        List<Player> playerList = List.of(tori,
                eden, ian, nuynung);

        Players players = new Players(playerList);
        Dealer dealer = new Dealer();
        dealer.initHand(deck);
        Map<Player, GameResult> gameResultMap = referee.judge(dealer, players);

        // when & then
        Assertions.assertThat(gameResultMap.size()).isEqualTo(4);
        Assertions.assertThat(gameResultMap.get(tori)).isEqualTo(GameResult.BLACKJACK);
        Assertions.assertThat(gameResultMap.get(eden)).isEqualTo(GameResult.DRAW);
        Assertions.assertThat(gameResultMap.get(ian)).isEqualTo(GameResult.LOSE);
        Assertions.assertThat(gameResultMap.get(nuynung)).isEqualTo(GameResult.WIN);

    }
}
