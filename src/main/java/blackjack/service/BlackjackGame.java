package blackjack.service;

import blackjack.domain.Dealer;
import blackjack.domain.Hands;
import blackjack.domain.Player;
import blackjack.domain.PlayerName;
import blackjack.domain.Players;
import blackjack.domain.Score;
import blackjack.domain.WinStatus;
import blackjack.domain.WinningResult;
import blackjack.dto.FinalResultDTO;
import blackjack.dto.StartCardsDTO;
import blackjack.dto.WinningResultDTO;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackGame {
    public static final String DEALER_SIGNAL = "DEALER";
    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(final Players players) {
        this.players = players;
        this.dealer = (Dealer) Player.from(DEALER_SIGNAL);
    }

    public StartCardsDTO start() {
        dealer.shuffleDeck();
        dealer.addStartCard();

        int playersCardCount = players.count() * 2;
        players.divideCard(dealer.drawCards(playersCardCount));

        return getStartCards();
    }

    private StartCardsDTO getStartCards() {
        Hands dealerHands = dealer.getOpenedHands();
        Map<PlayerName, Hands> playersCard = players.getPlayerHands();
        playersCard.put(dealer.getName(), dealerHands);

        return StartCardsDTO.of(playersCard);
    }

    public FinalResultDTO getFinalResults() {
        Hands dealerHands = dealer.getHands();
        Score dealerScore = dealer.calculate();

        Map<PlayerName, Hands> playersHands = players.getPlayerHands();
        Map<PlayerName, Score> playersScores =  players.getPlayerScores();

        playersHands.put(dealer.getName(), dealerHands);
        playersScores.put(dealer.getName(), dealerScore);

        return FinalResultDTO.of(playersHands, playersScores);
    }


    public WinningResultDTO getWinningResults() {
        Map<PlayerName, WinStatus> rawWinningResult = players.determineWinStatus(dealer.calculate());

        Map<String, String> winningResults = new LinkedHashMap<>();
        rawWinningResult.forEach((key, value) -> winningResults.put(key.getName(), value.name()));

        WinningResult winningResult = new WinningResult(rawWinningResult);
        Map<WinStatus, Integer> dealerResult = winningResult.summarizeDealerResult();

        return WinningResultDTO.of(winningResults, dealerResult);
    }
}
