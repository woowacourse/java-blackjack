package domain.result;

import domain.betting.BetAmount;
import domain.card.Card;
import domain.card.CardDenomination;
import domain.card.CardEmblem;
import domain.participant.Dealer;
import domain.participant.ParticipantInitialInformation;
import domain.participant.ParticipantName;
import domain.participant.Players;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultAnalyzerTest {

    @Test
    void 플레이어가_승리한_경우_수익을_얻는다() {
        // given
        Card spade2 = Card.of(CardDenomination.TWO, CardEmblem.SPADE);
        Card heart3 = Card.of(CardDenomination.THREE, CardEmblem.HEART);
        Card heart4 = Card.of(CardDenomination.FOUR, CardEmblem.HEART);
        Card spadeAce = Card.of(CardDenomination.ACE, CardEmblem.SPADE);
        List<Card> dealerCards = List.of(spade2, heart3);
        List<Card> playerCards = List.of(heart4, spadeAce);

        Players players = Players.from(List.of(
                ParticipantInitialInformation.of(ParticipantName.from("test"), BetAmount.from(1000))
        ));
        Dealer dealer = Dealer.create();

        dealer.drawCards(dealerCards);
        players.stream().forEach(player -> player.drawCards(playerCards));

        // when
        BettingResults bettingResults = GameResultAnalyzer.analyzeBettingResults(players, dealer);

        // then
        BettingResult result = bettingResults.playerBettingResults().getFirst();
        Assertions.assertThat(result.getParticipantName().name()).isEqualTo("test");
        Assertions.assertThat(result.getProfit()).isEqualTo(1000);
    }

    @Test
    void 플레이어가_패배한_경우_배팅금액을_잃는다() {
        // given
        Card spade2 = Card.of(CardDenomination.TWO, CardEmblem.SPADE);
        Card heart3 = Card.of(CardDenomination.THREE, CardEmblem.HEART);
        Card heart4 = Card.of(CardDenomination.FOUR, CardEmblem.HEART);
        Card spadeAce = Card.of(CardDenomination.ACE, CardEmblem.SPADE);
        List<Card> playerCards = List.of(spade2, heart3);
        List<Card> dealerCards = List.of(heart4, spadeAce);
        Players players = Players.from(List.of(
                ParticipantInitialInformation.of(ParticipantName.from("test"), BetAmount.from(1000))
        ));
        Dealer dealer = Dealer.create();

        dealer.drawCards(dealerCards);
        players.stream().forEach(player -> player.drawCards(playerCards));

        // when
        BettingResults bettingResults = GameResultAnalyzer.analyzeBettingResults(players, dealer);

        // then
        BettingResult result = bettingResults.playerBettingResults().getFirst();
        Assertions.assertThat(result.getParticipantName().name()).isEqualTo("test");
        Assertions.assertThat(result.getProfit()).isEqualTo(-1000);
    }

    @Test
    @DisplayName(value = "플레이어만 블랙잭인 경우 1.5배 수익을 얻는다")
    void 플레이어만_블랙잭인_경우_수익을_얻는다() {
        // given
        Card spadeKing = Card.of(CardDenomination.KING, CardEmblem.SPADE);
        Card heartAce = Card.of(CardDenomination.ACE, CardEmblem.HEART);
        Card heart4 = Card.of(CardDenomination.FOUR, CardEmblem.HEART);
        Card spadeAce = Card.of(CardDenomination.ACE, CardEmblem.SPADE);
        List<Card> playerCards = List.of(spadeKing, heartAce);
        List<Card> dealerCards = List.of(heart4, spadeAce);
        Players players = Players.from(List.of(
                ParticipantInitialInformation.of(ParticipantName.from("test"), BetAmount.from(1000))
        ));
        Dealer dealer = Dealer.create();

        // when
        dealer.drawCards(dealerCards);
        players.stream().forEach(player -> player.drawCards(playerCards));
        BettingResults bettingResults = GameResultAnalyzer.analyzeBettingResults(players, dealer);

        // then
        BettingResult result = bettingResults.playerBettingResults().getFirst();
        Assertions.assertThat(result.getParticipantName().name()).isEqualTo("test");
        Assertions.assertThat(result.getProfit()).isEqualTo(1500);
    }

    @Test
    void 딜러만_블랙잭인_경우_플레이어는_배팅금액을_잃는다() {
        // given
        Card spadeKing = Card.of(CardDenomination.KING, CardEmblem.SPADE);
        Card heartAce = Card.of(CardDenomination.ACE, CardEmblem.HEART);
        Card heart4 = Card.of(CardDenomination.FOUR, CardEmblem.HEART);
        Card spadeAce = Card.of(CardDenomination.ACE, CardEmblem.SPADE);
        List<Card> dealerCards = List.of(spadeKing, heartAce);
        List<Card> playerCards = List.of(heart4, spadeAce);

        Players players = Players.from(List.of(
                ParticipantInitialInformation.of(ParticipantName.from("test"), BetAmount.from(1000))
        ));
        Dealer dealer = Dealer.create();

        dealer.drawCards(dealerCards);
        players.stream().forEach(player -> player.drawCards(playerCards));

        // when
        BettingResults bettingResults = GameResultAnalyzer.analyzeBettingResults(players, dealer);

        // then
        BettingResult result = bettingResults.playerBettingResults().getFirst();
        Assertions.assertThat(result.getParticipantName().name()).isEqualTo("test");
        Assertions.assertThat(result.getProfit()).isEqualTo(-1000);
    }

    @Test
    void 딜러가_버스트_되는_경우_나머지_플레이어는_승리한다() {
        // given
        Card spade2 = Card.of(CardDenomination.TWO, CardEmblem.SPADE);
        Card heart3 = Card.of(CardDenomination.THREE, CardEmblem.HEART);
        Card heart4 = Card.of(CardDenomination.FOUR, CardEmblem.HEART);
        Card spadeQueen = Card.of(CardDenomination.QUEEN, CardEmblem.SPADE);
        Card spadeKing = Card.of(CardDenomination.KING, CardEmblem.SPADE);
        List<Card> playerCards = List.of(spade2, heart3);
        List<Card> dealerCards = List.of(heart4, spadeQueen, spadeKing);

        Players players = Players.from(List.of(
                ParticipantInitialInformation.of(ParticipantName.from("test"), BetAmount.from(1000))
        ));
        Dealer dealer = Dealer.create();

        dealer.drawCards(dealerCards);
        players.stream().forEach(player -> player.drawCards(playerCards));

        // when
        BettingResults bettingResults = GameResultAnalyzer.analyzeBettingResults(players, dealer);

        // then
        BettingResult result = bettingResults.playerBettingResults().getFirst();
        Assertions.assertThat(result.getParticipantName().name()).isEqualTo("test");
        Assertions.assertThat(result.getProfit()).isEqualTo(1000);
    }

}
