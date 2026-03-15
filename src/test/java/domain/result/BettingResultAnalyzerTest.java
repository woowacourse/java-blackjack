package domain.result;

import domain.betiing.BetAmount;
import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardDeckBuilder;
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

class BettingResultAnalyzerTest {

    @Test
    void 플레이어가_승리한_경우_수익을_얻는다() {
        Card spade2 = Card.of(CardDenomination.TWO, CardEmblem.SPADE);
        Card heart3 = Card.of(CardDenomination.THREE, CardEmblem.HEART);
        Card heart4 = Card.of(CardDenomination.FOUR, CardEmblem.HEART);
        Card spadeAce = Card.of(CardDenomination.ACE, CardEmblem.SPADE);
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(spade2, heart3, heart4, spadeAce)
                .build();

        ParticipantName playerName = ParticipantName.from("test");
        BetAmount betAmount = BetAmount.from(1000);
        ParticipantInitialInformation participantInitialInformation = ParticipantInitialInformation.of(playerName,
                betAmount);
        Players players = Players.from(List.of(participantInitialInformation));
        Dealer dealer = Dealer.from();

        dealer.drawCards(cardDeck, 2);
        players.stream().forEach(
                player -> player.drawCards(cardDeck, 2));
        List<BettingResult> bettingResults = GameResultAnalyzer.analyzePlayerBettingResults(players, dealer);

        Assertions.assertThat(bettingResults.getFirst().getParticipantName().name()).isEqualTo("test");
        Assertions.assertThat(bettingResults.getFirst().getProfit()).isEqualTo(1000);
    }

    @Test
    void 플레이어가_패배한_경우_배팅금액을_잃는다() {
        Card spade2 = Card.of(CardDenomination.TWO, CardEmblem.SPADE);
        Card heart3 = Card.of(CardDenomination.THREE, CardEmblem.HEART);
        Card heart4 = Card.of(CardDenomination.FOUR, CardEmblem.HEART);
        Card spadeAce = Card.of(CardDenomination.ACE, CardEmblem.SPADE);
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(spade2, heart3, heart4, spadeAce)
                .build();
        ParticipantName playerName = ParticipantName.from("test");
        BetAmount betAmount = BetAmount.from(1000);
        ParticipantInitialInformation participantInitialInformation = ParticipantInitialInformation.of(playerName,
                betAmount);
        Players players = Players.from(List.of(participantInitialInformation));
        Dealer dealer = Dealer.from();

        players.stream().forEach(
                player -> player.drawCards(cardDeck, 2));
        dealer.drawCards(cardDeck, 2);

        List<BettingResult> bettingResults = GameResultAnalyzer.analyzePlayerBettingResults(players, dealer);

        Assertions.assertThat(bettingResults.getFirst().getParticipantName().name()).isEqualTo("test");
        Assertions.assertThat(bettingResults.getFirst().getProfit()).isEqualTo(-1000);
    }

    @Test
    @DisplayName(value = "플레이어만 블랙잭인 경우 1.5배 수익을 얻는다")
    void 플레이어만_블랙잭인_경우_수익을_얻는다() {
        Card spadeKing = Card.of(CardDenomination.KING, CardEmblem.SPADE);
        Card heartAce = Card.of(CardDenomination.ACE, CardEmblem.HEART);
        Card heart4 = Card.of(CardDenomination.FOUR, CardEmblem.HEART);
        Card spadeAce = Card.of(CardDenomination.ACE, CardEmblem.SPADE);
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(spadeKing, heartAce, heart4, spadeAce)
                .build();

        ParticipantName playerName = ParticipantName.from("test");
        BetAmount betAmount = BetAmount.from(1000);
        ParticipantInitialInformation participantInitialInformation = ParticipantInitialInformation.of(playerName,
                betAmount);
        Players players = Players.from(List.of(participantInitialInformation));
        Dealer dealer = Dealer.from();

        players.stream().forEach(player -> player.drawCards(cardDeck, 2));
        dealer.drawCards(cardDeck, 2);

        List<BettingResult> bettingResults = GameResultAnalyzer.analyzePlayerBettingResults(players, dealer);

        Assertions.assertThat(bettingResults.getFirst().getParticipantName().name()).isEqualTo("test");
        Assertions.assertThat(bettingResults.getFirst().getProfit()).isEqualTo(1500);

    }

    @Test
    void 딜러만_블랙잭인_경우_플레이어는_배팅금액을_잃는다() {
        Card spadeKing = Card.of(CardDenomination.KING, CardEmblem.SPADE);
        Card heartAce = Card.of(CardDenomination.ACE, CardEmblem.HEART);
        Card heart4 = Card.of(CardDenomination.FOUR, CardEmblem.HEART);
        Card spadeAce = Card.of(CardDenomination.ACE, CardEmblem.SPADE);
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(spadeKing, heartAce, heart4, spadeAce)
                .build();

        ParticipantName playerName = ParticipantName.from("test");
        BetAmount betAmount = BetAmount.from(1000);
        ParticipantInitialInformation participantInitialInformation = ParticipantInitialInformation.of(playerName,
                betAmount);
        Players players = Players.from(List.of(participantInitialInformation));
        Dealer dealer = Dealer.from();

        dealer.drawCards(cardDeck, 2);
        players.stream().forEach(player -> player.drawCards(cardDeck, 2));

        List<BettingResult> bettingResults = GameResultAnalyzer.analyzePlayerBettingResults(players, dealer);

        Assertions.assertThat(bettingResults.getFirst().getParticipantName().name()).isEqualTo("test");
        Assertions.assertThat(bettingResults.getFirst().getProfit()).isEqualTo(-1000);
    }

    @Test
    void 딜러가_버스트_되는_경우_나머지_플레이어는_승리한다() {
        Card spade2 = Card.of(CardDenomination.TWO, CardEmblem.SPADE);
        Card heart3 = Card.of(CardDenomination.THREE, CardEmblem.HEART);
        Card heart4 = Card.of(CardDenomination.FOUR, CardEmblem.HEART);
        Card spadeQueen = Card.of(CardDenomination.QUEEN, CardEmblem.SPADE);
        Card spadeKing = Card.of(CardDenomination.KING, CardEmblem.SPADE);
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(spade2, heart3, heart4, spadeQueen, spadeKing)
                .build();

        ParticipantName playerName = ParticipantName.from("test");
        BetAmount betAmount = BetAmount.from(1000);
        ParticipantInitialInformation participantInitialInformation = ParticipantInitialInformation.of(playerName,
                betAmount);
        Players players = Players.from(List.of(participantInitialInformation));
        Dealer dealer = Dealer.from();

        players.stream().forEach(
                player -> player.drawCards(cardDeck, 2));
        dealer.drawCards(cardDeck, 3);

        Assertions.assertThat(dealer.isBusted()).isTrue();
        List<BettingResult> bettingResults = GameResultAnalyzer.analyzePlayerBettingResults(players, dealer);

        Assertions.assertThat(bettingResults.getFirst().getParticipantName().name()).isEqualTo("test");
        Assertions.assertThat(bettingResults.getFirst().getProfit()).isEqualTo(1000);
    }

}
