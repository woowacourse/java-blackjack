package domain.result;

import static config.BlackjackGameConstant.INITIAL_CARD_DRAW_COUNT;

import domain.betiing.BetAmount;
import domain.card.*;
import domain.participant.Dealer;
import domain.participant.ParticipantInitialInformation;
import domain.participant.ParticipantName;
import domain.participant.Players;
import domain.result.dto.GameResultDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class WinningStatusAnalyzerTest {

    @Test
    void 최종_승패_결과를_계산한다() {
        Card clover2 = Card.of(CardDenomination.TWO, CardEmblem.CLOVER);
        Card spade2 = Card.of(CardDenomination.TWO, CardEmblem.SPADE);
        Card spade3 = Card.of(CardDenomination.THREE, CardEmblem.SPADE);
        Card spade4 = Card.of(CardDenomination.FOUR, CardEmblem.SPADE);
        Card spade5 = Card.of(CardDenomination.FIVE, CardEmblem.SPADE);
        Card spade6 = Card.of(CardDenomination.SIX, CardEmblem.SPADE);
        List<Card> cards = List.of(clover2, spade2, spade3, spade4, spade5, spade6);

        CardDeck cardDeck = new CardDeckBuilder()
                .cards(cards)
                .build();
        Dealer dealerScore4 = Dealer.from();

        dealerScore4.drawCards(cardDeck, INITIAL_CARD_DRAW_COUNT);
        Players players = Players.from(List.of(
                ParticipantInitialInformation.of(ParticipantName.from("p7"), BetAmount.from(0)),
                ParticipantInitialInformation.of( ParticipantName.from("p11"), BetAmount.from(0))));
        players.giveInitialCardBundle(cardDeck);

        GameResultDto analysis = GameResultAnalyzer.analyze(players, dealerScore4);

        Assertions.assertThat(analysis.dealerGameResultDto()
                .resultStatistic()).containsEntry(WinningStatus.LOSS, 2);
    }

}
