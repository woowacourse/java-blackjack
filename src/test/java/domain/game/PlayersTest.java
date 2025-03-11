package domain.game;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardNumber;
import domain.card.CardShuffler;
import domain.card.Pattern;
import domain.card.TestShuffler;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    void 플레이어를_정상적으로_생성한다() {
        //given
        List<String> playerNames = List.of("aa", "bb", "cc", "dd", "ee");

        //when & then
        assertThatCode(() -> new Players(playerNames, CardDeck.createCards(new CardShuffler())))
                .doesNotThrowAnyException();
    }

    @Test
    void 인원_수가_5명_초과이면_예외를_던진다() {
        //given
        List<String> playerNames = List.of("aa", "bb", "cc", "dd", "ee", "ff");

        //when & then
        assertThatThrownBy(
                () -> new Players(playerNames, CardDeck.createCards(new CardShuffler())))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 인원_수가_0명이면_예외를_던진다() {
        //given
        List<String> playerNames = List.of();

        //when & then
        assertThatThrownBy(() -> new Players(playerNames, CardDeck.createCards(new CardShuffler())))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 같은_이름의_플레이어가_여러_명_있으면_예외를_던진다() {
        //given
        List<String> playerNames = List.of("aa", "aa");

        //when & then
        assertThatThrownBy(() -> new Players(playerNames, CardDeck.createCards(new CardShuffler())))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 모든_플레이어는_게임_시작_시_카드를_드로우한다() {
        //given
        List<String> playerNames = List.of("aa", "bb");
        Players players = new Players(playerNames, CardDeck.createCards(new CardShuffler()));

        //when
        players.drawCard(2);

        //when
        Assertions.assertThat(players.getPlayers().get(0).getCardsCount()).isEqualTo(2);
        Assertions.assertThat(players.getPlayers().get(1).getCardsCount()).isEqualTo(2);
    }

    @Test
    void 모든_플레이어의_이름을_반환한다() {
        //given
        List<String> playerNames = List.of("aa", "bb");
        Players players = new Players(playerNames, CardDeck.createCards(new CardShuffler()));

        //when
        List<String> allPlayerNames = players.getAllPlayerNames();

        //then
        Assertions.assertThat(allPlayerNames).containsExactly("aa", "bb");
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.SEVEN));

        Players players = new Players(List.of("pobi"), cardDeck);
        Hand playerHand = players.getPlayers().getFirst().getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.SIX));

        //when
        List<GameResult> gameResult = players.judgeGameResult(dealer);

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.LOSE));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_둘다_버스트() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.HEART, CardNumber.TEN));

        Players players = new Players(List.of("pobi"), cardDeck);
        Hand playerHand = players.getPlayers().getFirst().getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        playerCards.add(new Card(Pattern.HEART, CardNumber.TEN));

        //when
        List<GameResult> gameResult = players.judgeGameResult(dealer);

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.LOSE));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_딜러만_버스트() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.HEART, CardNumber.TEN));

        Players players = new Players(List.of("pobi"), cardDeck);
        Hand playerHand = players.getPlayers().getFirst().getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));

        //when
        List<GameResult> gameResult = players.judgeGameResult(dealer);

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.WIN));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_둘다_블랙잭() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        Players players = new Players(List.of("pobi"), cardDeck);
        Hand playerHand = players.getPlayers().getFirst().getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when
        List<GameResult> gameResult = players.judgeGameResult(dealer);

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.DRAW));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_딜러만_블랙잭() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        Players players = new Players(List.of("pobi"), cardDeck);
        Hand playerHand = players.getPlayers().getFirst().getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.EIGHT));
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TWO));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when
        List<GameResult> gameResult = players.judgeGameResult(dealer);

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.LOSE));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_플레이어만_블랙잭() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand dealerHand = dealer.getHand();
        List<Card> dealerCards = dealerHand.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.EIGHT));
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TWO));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        Players players = new Players(List.of("pobi"), cardDeck);
        Hand playerHand = players.getPlayers().getFirst().getHand();
        List<Card> playerCards = playerHand.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when
        List<GameResult> gameResult = players.judgeGameResult(dealer);

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.WIN));
    }
}
