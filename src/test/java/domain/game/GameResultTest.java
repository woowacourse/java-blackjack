package domain.game;

import static domain.card.CardDeck.DRAW_COUNT_WHEN_HIT;
import static domain.card.CardDeck.DRAW_COUNT_WHEN_START;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardNumber;
import domain.card.Pattern;
import domain.card.TestShuffler;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());

        Dealer dealer = new Dealer(cardDeck);
        dealer.drawCard(DRAW_COUNT_WHEN_START);

        Player player = new Player("pobi", cardDeck);
        player.drawCard(DRAW_COUNT_WHEN_START);
        player.drawCard(DRAW_COUNT_WHEN_HIT);

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_둘다_버스트() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);

        dealer.drawCard(DRAW_COUNT_WHEN_START);
        dealer.drawCard(DRAW_COUNT_WHEN_HIT);

        Player player = new Player("pobi", cardDeck);
        player.drawCard(DRAW_COUNT_WHEN_START);
        player.drawCard(DRAW_COUNT_WHEN_HIT);

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_딜러만_버스트() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);

        dealer.drawCard(DRAW_COUNT_WHEN_START);
        dealer.drawCard(DRAW_COUNT_WHEN_HIT);

        Player player = new Player("pobi", cardDeck);
        player.drawCard(DRAW_COUNT_WHEN_START);

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_딜러의_총합이_21에_가깝다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        cardDeck.drawCard(1);

        Dealer dealer = new Dealer(cardDeck);
        dealer.drawCard(DRAW_COUNT_WHEN_START);

        Player player = new Player("pobi", cardDeck);
        player.drawCard(DRAW_COUNT_WHEN_START);

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_플레이어의_총합이_21에_가깝다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        cardDeck.drawCard(1);

        Player player = new Player("pobi", cardDeck);
        player.drawCard(DRAW_COUNT_WHEN_START);

        Dealer dealer = new Dealer(cardDeck);
        dealer.drawCard(DRAW_COUNT_WHEN_START);

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_딜러와_플레이어의_총합이_같다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());

        Dealer dealer = new Dealer(cardDeck);
        dealer.drawCard(DRAW_COUNT_WHEN_START);

        Player player = new Player("pobi", cardDeck);
        player.drawCard(DRAW_COUNT_WHEN_START);

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.DRAW);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_둘다_블랙잭() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        Player player = new Player("pobi", cardDeck);
        Hand playerHand = player.getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.DRAW);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_딜러만_블랙잭() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        Player player = new Player("pobi", cardDeck);
        Hand playerHand = player.getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.EIGHT));
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TWO));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_플레이어만_블랙잭() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.EIGHT));
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TWO));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        Player player = new Player("pobi", cardDeck);
        Hand playerHand = player.getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @Test
    void 딜러의_승리_횟수를_계산한다() {
        //when
        int winCount = GameResult.WIN.countGameResult(
                List.of(GameResult.WIN, GameResult.WIN, GameResult.LOSE, GameResult.DRAW));

        //then
        Assertions.assertThat(winCount).isEqualTo(1);

    }

    @Test
    void 딜러의_패배_횟수를_계산한다() {
        //when
        int winCount = GameResult.LOSE.countGameResult(
                List.of(GameResult.WIN, GameResult.WIN, GameResult.LOSE, GameResult.DRAW));

        //then
        Assertions.assertThat(winCount).isEqualTo(2);

    }

    @Test
    void 딜러의_무승부_횟수를_계산한다() {
        //when
        int winCount = GameResult.DRAW.countGameResult(
                List.of(GameResult.WIN, GameResult.WIN, GameResult.LOSE, GameResult.DRAW));

        //then
        Assertions.assertThat(winCount).isEqualTo(1);
    }
}
