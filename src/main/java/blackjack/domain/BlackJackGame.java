package blackjack.domain;

import static java.util.stream.Collectors.toList;

import blackjack.dto.ParticipantStatusResponse;
import blackjack.dto.ParticipantTotalStatusResponse;
import blackjack.dto.PlayerGameResult;
import blackjack.dto.TotalGameResultResponse;
import java.util.List;

public class BlackJackGame {
    private final Participants participants;
    private final Deck deck;

    public BlackJackGame(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public List<Name> getPlayerNames() {
        return participants.getPlayers().stream()
                .map(Participant::getName)
                .collect(toList());
    }

    public void drawMoreCardForPlayer(Name playerName) {
        Player player = participants.getPlayerByName(playerName);
        if (!player.canDrawCard()) {
            throw new IllegalStateException("[ERROR] 더이상 카드를 뽑을 수 없습니다.");
        }
        player.addCard(deck.drawCard());
    }

    public boolean drawMoreCardForDealer() {
        Dealer dealer = participants.getDealer();
        if (dealer.canDrawCard()) {
            dealer.addCard(deck.drawCard());
            return true;
        }
        return false;
    }

    public ParticipantStatusResponse getPlayerStatusByName(Name playerName) {
        Player player = participants.getPlayerByName(playerName);
        return ParticipantStatusResponse.of(player);
    }

    public List<ParticipantStatusResponse> getStartStatusResponse() {
        return participants.getParticipants().stream()
                .map(ParticipantStatusResponse::ofStart)
                .collect(toList());
    }

    public TotalGameResultResponse getTotalGameResult() {
        Dealer dealer = participants.getDealer();
        List<PlayerGameResult> playerGameResults = getPlayerGameResults(dealer);
        int dealerTotalProfit = playerGameResults.stream()
                .mapToInt(PlayerGameResult::getProfit)
                .sum() * -1;
        return new TotalGameResultResponse(dealerTotalProfit, playerGameResults);
    }

    private List<PlayerGameResult> getPlayerGameResults(Dealer dealer) {
        return participants.getPlayers()
                .stream()
                .map(player -> PlayerGameResult.of(player.getName(), player.matchGameWithBet(dealer)))
                .collect(toList());
    }

    public List<ParticipantTotalStatusResponse> getAllParticipantTotalResponse() {
        return participants.getParticipants().stream()
                .map(ParticipantTotalStatusResponse::of)
                .collect(toList());
    }
}
