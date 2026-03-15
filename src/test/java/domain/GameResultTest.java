package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Rank;
import domain.card.Suit;
import domain.game.GameResult;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static domain.BlackjackRule.DEALER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {
    Player pobi;
    Players players;
    Dealer dealer;

    @BeforeEach
    void setUp() {
        pobi = new Player("pobi");
        players = new Players(List.of(pobi));

        pobi.bet(BigDecimal.valueOf(1000));

        dealer = new Dealer(DEALER_NAME);
    }

    @Test
    void 플레이어가_처음_받은_카드_두_장의_합이_21이고_딜러는_아닐_경우_자신이_베팅한_금액의_1_5배를_딜러에게_받는다() {
        Cards playerCards = cards(card(Rank.ACE, Suit.HEART), card(Rank.JACK, Suit.HEART));
        pobi.receiveInitialCards(playerCards);

        Cards dealerCards = cards(card(Rank.SEVEN, Suit.HEART), card(Rank.EIGHT, Suit.HEART));
        dealer.receiveInitialCards(dealerCards);
        dealer.receive(card(Rank.SIX, Suit.HEART));

        GameResult gameResult = new GameResult(players, dealer);

        assertThat(gameResult.participantResultInfo(pobi).profit()).isEqualByComparingTo(BigDecimal.valueOf(1500));
        assertThat(gameResult.participantResultInfo(dealer).profit()).isEqualByComparingTo(BigDecimal.valueOf(-1500));
    }

    @Test
    void 플레이어가_승리할_경우_자신이_베팅한_금액만큼_딜러에게_받는다() {
        Cards playerCards = cards(card(Rank.ACE, Suit.HEART), card(Rank.NINE, Suit.HEART));
        pobi.receiveInitialCards(playerCards);

        Cards dealerCards = cards(card(Rank.THREE, Suit.HEART), card(Rank.FOUR, Suit.HEART));
        dealer.receiveInitialCards(dealerCards);

        GameResult gameResult = new GameResult(players, dealer);

        assertThat(gameResult.participantResultInfo(pobi).profit()).isEqualByComparingTo(BigDecimal.valueOf(1000));
        assertThat(gameResult.participantResultInfo(dealer).profit()).isEqualByComparingTo(BigDecimal.valueOf(-1000));
    }

    @Test
    void 딜러가_승리할_경우_플레이어는_자신이_베팅한_금액을_딜러에게_준다() {
        Cards playerCards = cards(card(Rank.ACE, Suit.HEART), card(Rank.TWO, Suit.HEART));
        pobi.receiveInitialCards(playerCards);

        Cards dealerCards = cards(card(Rank.ACE, Suit.CLOVER), card(Rank.THREE, Suit.HEART));
        dealer.receiveInitialCards(dealerCards);
        dealer.receive(card(Rank.FOUR, Suit.HEART));

        GameResult gameResult = new GameResult(players, dealer);

        assertThat(gameResult.participantResultInfo(pobi).profit()).isEqualByComparingTo(BigDecimal.valueOf(-1000));
        assertThat(gameResult.participantResultInfo(dealer).profit()).isEqualByComparingTo(BigDecimal.valueOf(1000));
    }

    @Test
    void 무승부일_경우_플레이어는_자신이_베팅한_금액을_돌려받는다() {
        Cards playerCards = cards(card(Rank.TWO, Suit.HEART), card(Rank.JACK, Suit.HEART));
        pobi.receiveInitialCards(playerCards);

        Cards dealerCards = cards(card(Rank.TWO, Suit.CLOVER), new Card(Rank.JACK, Suit.CLOVER));
        dealer.receiveInitialCards(dealerCards);

        GameResult gameResult = new GameResult(players, dealer);

        assertThat(gameResult.participantResultInfo(pobi).profit()).isEqualByComparingTo(BigDecimal.valueOf(0));
        assertThat(gameResult.participantResultInfo(dealer).profit()).isEqualByComparingTo(BigDecimal.valueOf(0));
    }

    private Cards cards(Card... cards) {
        return new Cards(List.of(cards));
    }

    private Card card(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }
}
