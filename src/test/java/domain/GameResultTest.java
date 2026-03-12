package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Emblem;
import domain.card.Grade;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.result.GameResult;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    @Test
    public void 플레이어의_점수가_딜러보다_높으면_승리한다() {
        // given
        List<Card> cards = List.of(new Card(Emblem.CLOVER, Grade.NINE), new Card(Emblem.HEART, Grade.JACK),
                new Card(Emblem.HEART, Grade.ACE), new Card(Emblem.SPADE, Grade.SEVEN));

        Deck deck = new Deck(new FixShuffleStrategy(cards));
        Player player = Player.from("토리", 1000);
        player.initHand(deck);
        Dealer dealer = new Dealer(deck);

        // when
        GameResult gameResult = GameResult.judge(dealer, player);

        // then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @Test
    public void 플레이어가_블랙잭이고_딜러가_블랙잭이_아니면_블랙잭_승리이다() {
        // given
        List<Card> cards = List.of(new Card(Emblem.CLOVER, Grade.ACE), new Card(Emblem.HEART, Grade.JACK),
                new Card(Emblem.HEART, Grade.ACE), new Card(Emblem.SPADE, Grade.SEVEN));

        Deck deck = new Deck(new FixShuffleStrategy(cards));
        Player player = Player.from("토리", 1000);
        player.initHand(deck);
        Dealer dealer = new Dealer(deck);

        // when
        GameResult gameResult = GameResult.judge(dealer, player);

        // then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.BLACKJACK);
    }

    @Test
    public void 플레이어가_버스트인_경우_패배한다() {
        // given
        List<Card> cards = List.of(new Card(Emblem.CLOVER, Grade.KING), new Card(Emblem.HEART, Grade.JACK),
                new Card(Emblem.HEART, Grade.FIVE), new Card(Emblem.HEART, Grade.ACE),
                new Card(Emblem.SPADE, Grade.SEVEN));

        Deck deck = new Deck(new FixShuffleStrategy(cards));
        Player player = Player.from("토리", 1000);
        player.initHand(deck);
        player.playTurn(deck);
        Dealer dealer = new Dealer(deck);

        // when
        GameResult gameResult = GameResult.judge(dealer, player);

        // then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

}
