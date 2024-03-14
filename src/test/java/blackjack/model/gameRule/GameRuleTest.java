package blackjack.model.gameRule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardPattern;
import blackjack.model.deck.Deck;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Name;
import blackjack.model.gamer.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameRuleTest {

    @DisplayName("플레이어의 초기 결과값을 설정한다.")
    @Test
    void initializePlayerResults() {
        //given
        Player player = new Player(new Name("ted"), 100);

        //when
        GameRule.initialSetting(List.of(player));
        PlayersResult playersResult = GameRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.NONE);
    }

    @DisplayName("카드 점수 계산 규칙 : 덱이 버스트인 경우 덱에 있는 Ace 카드의 값을 1로 한다.")
    @Test
    void cardScoringRule() {
        //given
        Deck deck = new Deck();
        deck.add(new Card(CardPattern.DIAMOND, CardNumber.ACE));
        deck.add(new Card(CardPattern.DIAMOND, CardNumber.NINE));
        deck.add(new Card(CardPattern.DIAMOND, CardNumber.TWO));

        Card aceCard = findAceCard(deck);
        int aceCardScore = aceCard.getScore();

        //when
        GameRule.cardScoringRule(deck);
        Card changedaceCard = findAceCard(deck);
        int changedAceCardScore = changedaceCard.getScore();

        //then
        assertAll(
                () -> assertThat(aceCardScore).isEqualTo(11),
                () -> assertThat(changedAceCardScore).isEqualTo(1)
        );
    }

    @DisplayName("카드 점수 계산 규칙 : "
            + "Ace 카드가 여러장일 때, 한 장의 Ace 카드 값을 바꿔서 덱이 버스트가 아니게 된다면 나머지 Ace 카드의 값을 변경하지 않는다.")
    @Test
    void cardScoringRuleByNoneBust() {
        //given
        Deck deck = new Deck();
        deck.add(new Card(CardPattern.DIAMOND, CardNumber.ACE));
        deck.add(new Card(CardPattern.CLOVER, CardNumber.ACE));
        deck.add(new Card(CardPattern.DIAMOND, CardNumber.SEVEN));
        deck.add(new Card(CardPattern.DIAMOND, CardNumber.TWO));

        int countElevenAce = deck.countElevenAce();

        //when
        GameRule.cardScoringRule(deck);
        int changedCountElevenAce = deck.countElevenAce();

        //then
        assertAll(
                () -> assertThat(countElevenAce).isEqualTo(2),
                () -> assertThat(changedCountElevenAce).isEqualTo(1)
        );
    }

    @DisplayName("카드 점수 계산 규칙 : "
            + "Ace 카드가 여러장일 때, 한 장의 Ace 카드 값을 바꿔도 덱이 버스트라면 나머지 Ace 카드의 값도 변경한다.")
    @Test
    void cardScoringRuleByBust() {
        //given
        Deck deck = new Deck();
        deck.add(new Card(CardPattern.DIAMOND, CardNumber.ACE));
        deck.add(new Card(CardPattern.CLOVER, CardNumber.ACE));
        deck.add(new Card(CardPattern.HEART, CardNumber.ACE));
        deck.add(new Card(CardPattern.SPADE, CardNumber.TEN));
        deck.add(new Card(CardPattern.SPADE, CardNumber.SEVEN));

        int countElevenAce = deck.countElevenAce();

        //when
        GameRule.cardScoringRule(deck);
        int changedCountElevenAce = deck.countElevenAce();

        //then
        assertAll(
                () -> assertThat(countElevenAce).isEqualTo(3),
                () -> assertThat(changedCountElevenAce).isEqualTo(0)
        );
    }

    @DisplayName("초기 게임 결과 규칙 : 플레이어와 딜러가 모두 블랙잭일 경우 플레이어는 푸시이다.")
    @Test
    void InitialResultRuleByDealerPlayerBlackjack() {
        //given
        Player player = new Player(new Name("ted"), 100);
        Dealer dealer = new Dealer(100);

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.ACE));

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.SPADE, CardNumber.ACE));

        //when
        GameRule.initialResultRule(dealer, List.of(player));
        PlayersResult playersResult = GameRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.PUSH);
    }

    @DisplayName("초기 게임 결과 규칙 : 플레이어가 블랙잭이고, 딜러가 블랙잭이 아닐 경우 플레이어는 블랙잭이다.")
    @Test
    void InitialResultRuleByPlayerBlackjack() {
        //given
        Player player = new Player(new Name("ted"), 100);
        Dealer dealer = new Dealer(100);

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.ACE));

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.SPADE, CardNumber.TWO));

        //when
        GameRule.initialResultRule(dealer, List.of(player));
        PlayersResult playersResult = GameRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.BLACKJACK);
    }

    @DisplayName("초기 게임 결과 규칙 : 플레이어가 블랙잭이 아니고, 딜러가 블랙잭일 경우 플레이어는 패배한다.")
    @Test
    void InitialResultRuleByDealerBlackjack() {
        //given
        Player player = new Player(new Name("ted"), 100);
        Dealer dealer = new Dealer(100);

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.EIGHT));

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.SPADE, CardNumber.ACE));

        //when
        GameRule.initialResultRule(dealer, List.of(player));
        PlayersResult playersResult = GameRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.LOSE);
    }

    @DisplayName("최종 게임 결과 규칙 : 플레이어가 버스트일 경우 딜러의 결과에 관계없이 플레이어는 패배한다.")
    @Test
    void finalResultRuleByPlayerBust() {
        //given
        Player player = new Player(new Name("ted"), 100);
        Dealer dealer = new Dealer(100);

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.TEN));

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.SPADE, CardNumber.TEN));

        //when
        GameRule.finalResultRule(dealer, List.of(player));
        PlayersResult playersResult = GameRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.LOSE);
    }

    @DisplayName("최종 게임 결과 규칙 : 플레이어가 버스트가 아니고, 딜러가 버스트일 경우 플레이어는 승리한다.")
    @Test
    void finalResultRuleByDealerBust() {
        //given
        Player player = new Player(new Name("ted"), 100);
        Dealer dealer = new Dealer(100);

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.TWO));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.THREE));

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.SPADE, CardNumber.TEN));

        //when
        GameRule.finalResultRule(dealer, List.of(player));
        PlayersResult playersResult = GameRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.WIN);
    }

    @DisplayName("최종 게임 결과 규칙 : 플레이어와 딜러가 버스트나 블랙잭이 아닐때, 딜러보다 점수가 작을 경우 플레이어는 패배한다.")
    @Test
    void finalResultRuleByLowerThanDealer() {
        //given
        Player player = new Player(new Name("ted"), 100);
        Dealer dealer = new Dealer(100);

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.EIGHT));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.TWO));

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.SPADE, CardNumber.ACE));

        //when
        GameRule.finalResultRule(dealer, List.of(player));
        PlayersResult playersResult = GameRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.LOSE);
    }

    @DisplayName("최종 게임 결과 규칙 : 플레이어와 딜러가 버스트나 블랙잭이 아닐때, 딜러보다 점수가 높을 경우 플레이어는 승리한다.")
    @Test
    void finalResultRuleByHigherThanDealer() {
        //given
        Player player = new Player(new Name("ted"), 100);
        Dealer dealer = new Dealer(100);

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.ACE));

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.HEART, CardNumber.EIGHT));
        dealer.receiveCard(new Card(CardPattern.SPADE, CardNumber.TWO));

        //when
        GameRule.finalResultRule(dealer, List.of(player));
        PlayersResult playersResult = GameRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.WIN);
    }

    @DisplayName("최종 게임 결과 규칙 : 플레이어와 딜러가 버스트나 블랙잭이 아닐때, 딜러와 점수가 동일할 경우 플레이어는 푸시이다.")
    @Test
    void finalResultRuleBySameScore() {
        //given
        Player player = new Player(new Name("ted"), 100);
        Dealer dealer = new Dealer(100);

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.ACE));

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.SPADE, CardNumber.ACE));

        //when
        GameRule.finalResultRule(dealer, List.of(player));
        PlayersResult playersResult = GameRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.PUSH);
    }

    @DisplayName("히트/스테이 규칙 : 결과가 정해지지 않은 플레이어만 히트/스테이 대상이다.")
    @Test
    void hitStayTargetPlayerDecisionRule() {
        //given
        Player player1 = new Player(new Name("ted"), 100);
        player1.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player1.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.ACE));

        Player player2 = new Player(new Name("ted"), 100);
        player2.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.FIVE));
        player2.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));

        Dealer dealer = new Dealer(200);
        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.FIVE));
        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        //when
        GameRule.initialSetting(players);
        GameRule.initialResultRule(dealer, players);
        List<Player> hitStayTargetPlayer = GameRule.hitStayTargetPlayerDecisionRule(players);
        PlayersResult playersResult = GameRule.getPlayersResult();
        Result player1Result = playersResult.findPlayerResult(player1);
        Result player2Result = playersResult.findPlayerResult(player2);

        //then
        assertAll(
                () -> assertThat(player1Result).isEqualTo(Result.BLACKJACK),
                () -> assertThat(player2Result).isEqualTo(Result.NONE),
                () -> assertThat(hitStayTargetPlayer).containsExactly(player2)
        );
    }

    private Card findAceCard(Deck deck) {
        return deck.cards().stream()
                .filter(card -> card.number() == CardNumber.ACE)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ace 카드가 없습니다."));
    }
}
