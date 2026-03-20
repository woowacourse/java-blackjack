package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.dto.DealerGameResult;
import blackjack.dto.DrawResult;
import blackjack.dto.ParticipantResult;
import blackjack.dto.PlayerGameResult;
import blackjack.dto.TotalGameResult;
import java.util.ArrayList;
import java.util.List;

public class Participants {

    private final Players players;
    private final Dealer dealer;

    public Participants(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public Deck dealInitialCards(Deck deck) {
        List<Participant> participants = getParticipants();
        for (Participant participant : participants) {
            DrawResult drawResult = participant.dealInitialCards(deck);
            deck = drawResult.drewDeck();
        }
        return deck;
    }

    private List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getAllPlayers());
        return participants;
    }

    public List<ParticipantResult> getInitialResult() {
        ParticipantResult dealerResult = dealer.getInitialResult();
        List<ParticipantResult> playersResult = players.getInitialResult();
        List<ParticipantResult> participantResults = new ArrayList<>();
        participantResults.add(dealerResult);
        participantResults.addAll(playersResult);
        return participantResults;
    }

    public List<ParticipantResult> getGameResult() {
        List<Participant> participants = getParticipants();
        List<ParticipantResult> participantsTotalGameResult = new ArrayList<>();
        for (Participant participant : participants) {
            participantsTotalGameResult.add(ParticipantResult.from(participant));
        }
        return participantsTotalGameResult;
    }


    public boolean hasDrawablePlayer() {
        return players.hasDrawablePlayer();
    }

    public String getDrawablePlayerNickname() {
        return players.getDrawablePlayerNickname();
    }

    public DrawResult hitPlayer(Deck deck) {
        DrawResult drawResult = deck.draw();
        List<Card> drawCard = drawResult.drewCard().getCards();
        List<Card> drawDeck = drawResult.drewDeck().getCards();

        Hand playerHand = players.hitPlayer(drawCard);

        return DrawResult.of(playerHand.getCards(), drawDeck);
    }

    public void dontWantDraw() {
        players.dontWantDraw();
    }

    public boolean isDealerDraw() {
        return dealer.isDealerDraw();
    }

    public Deck dealerDraw(Deck deck) {
        DrawResult drawResult = deck.draw();
        Hand drawCard = drawResult.drewCard();
        dealer.receiveCard(drawCard.getCards());
        return drawResult.drewDeck();
    }

    public List<String> getAllPlayerNickname() {
        return players.getAllPlayerNickname();
    }

    public TotalGameResult getWinningResult() {
        List<PlayerGameResult> winningResultsWithDealer = players.getWinningResultsWithDealer(dealer);
        DealerGameResult dealerWinningResult = dealer.calculateDealerProfitResult(winningResultsWithDealer);
        return TotalGameResult.of(dealerWinningResult, winningResultsWithDealer);
    }
}
