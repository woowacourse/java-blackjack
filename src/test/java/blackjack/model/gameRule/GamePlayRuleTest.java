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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamePlayRuleTest {

    GamePlayRule gamePlayRule = new GamePlayRule();

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
        gamePlayRule.cardScoringRule(deck);
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
        gamePlayRule.cardScoringRule(deck);
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
        gamePlayRule.cardScoringRule(deck);
        int changedCountElevenAce = deck.countElevenAce();

        //then
        assertAll(
                () -> assertThat(countElevenAce).isEqualTo(3),
                () -> assertThat(changedCountElevenAce).isEqualTo(0)
        );
    }

    @DisplayName("딜러 카드 오픈 규칙 : 딜러의 첫 번째 카드를 반환한다.")
    @Test
    void dealerInitialCardOpenRule() {
        //given
        Dealer dealer = new Dealer(1000);

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.FIVE));
        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.THREE));

        //when
        Card firstCard = gamePlayRule.dealerInitialCardOpenRule(dealer);

        //then
        assertThat(firstCard).isEqualTo(new Card(CardPattern.DIAMOND, CardNumber.FIVE));
    }

    @DisplayName("플레이어 히트 규칙 : 플레이어의 스코어가 21 미만이라면 히트할 수 있다.")
    @Test
    void playerHitRuleExpectedTrue() {
        //given
        Player player = new Player(new Name("ted"), 100);
        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));

        //when
        boolean canPlayerHit = gamePlayRule.playerHitRule(player);

        //then
        assertThat(canPlayerHit).isTrue();
    }

    @DisplayName("플레이어 히트 규칙 : 플레이어의 스코어가 21 이상이라면 히트할 수 없다.")
    @Test
    void playerHitRuleExpectedFalse() {
        //given
        Player player = new Player(new Name("ted"), 100);
        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.NINE));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.TWO));

        //when
        boolean canPlayerHit = gamePlayRule.playerHitRule(player);

        //then
        assertThat(canPlayerHit).isFalse();
    }

    @DisplayName("딜러 히트 규칙 : 딜러의 스코어가 17 미만이라면 히트할 수 있다.")
    @Test
    void dealerHitRuleExpectedTrue() {
        //given
        Dealer dealer = new Dealer(100);
        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.FIVE));
        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.THREE));

        //when
        boolean canDealerHit = gamePlayRule.dealerHitRule(dealer);

        //then
        assertThat(canDealerHit).isTrue();
    }

    @DisplayName("딜러 히트 규칙 : 딜러의 스코어가 17 이상이라면 히트할 수 없다.")
    @Test
    void dealerHitRuleExpectedFalse() {
        //given
        Dealer dealer = new Dealer(100);
        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.SEVEN));

        //when
        boolean canDealerHit = gamePlayRule.dealerHitRule(dealer);

        //then
        assertThat(canDealerHit).isFalse();
    }

    private Card findAceCard(Deck deck) {
        return deck.cards().stream()
                .filter(card -> card.number() == CardNumber.ACE)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ace 카드가 없습니다."));
    }
}
