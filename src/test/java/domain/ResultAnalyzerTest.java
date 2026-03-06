package domain;

import domain.analyzer.ResultAnalyzer;
import domain.card.*;
import domain.dealer.Dealer;
import domain.player.Player;
import domain.player.PlayerName;
import domain.player.Players;
import domain.analyzer.dto.ResultAnalysisDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ResultAnalyzerTest {

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
        Dealer dealerScore4 = Dealer.of(cardDeck);

        dealerScore4.dealMyself();
        Player playerScore7 = Player.from(PlayerName.from("p7"));
        Player playerScore11 = Player.from(PlayerName.from("p11"));
        Players players = Players.from(List.of(playerScore7, playerScore11));
        players.dealCardBundle(dealerScore4);

        ResultAnalyzer resultAnalyzer = ResultAnalyzer.getInstance();
        ResultAnalysisDto analysis = resultAnalyzer.analyze(players, dealerScore4);

        Assertions.assertThat(analysis.getDealerResult())
                .isEqualTo("2패");
    }

}
