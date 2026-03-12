package repository;

import domain.common.NameAndCardInfos;
import domain.common.PlayedGameResult;
import domain.gameplaying.Participants;
import java.util.List;

public class ParticipantRepository {

    private Participants participants;

    public void setup(Participants participants) {
        this.participants = participants;
    }

    public void updateParticipantsWith(List<String> playerNames) {
        this.participants = participants.join(playerNames);
    }

    public void distributeInitialCards() {
        participants.allParticipantsDrawInitialCards();
    }

    public void currentPlayerDrawCard() {
        participants.currentPlayerDrawCard();
    }

    public void dealerDrawCard() {
        participants.dealerDrawCard();
    }

    public List<String> getAllPlayerNames() {
        return participants.allPlayerNames();
    }

    public String getCurrentPlayerName() {
        return participants.currentPlayerName();
    }

    public NameAndCardInfos getCurrentPlayerCards() {
        return participants.currentPlayerCardInfos();
    }

    public List<NameAndCardInfos> getAllPlayersCards() {
        return participants.allPlayerCardInfos();
    }

    public NameAndCardInfos getDealerCards() {
        return participants.dealerCardInfos();
    }

    public boolean isCurrentPlayerPlayable() {
        return participants.isCurrentPlayerPlayable();
    }

    public boolean hasWaitingPlayers() {
        return participants.hasWaitingPlayers();
    }

    public boolean isDealerPlayable() {
        return participants.isDealerPlayable();
    }

    public PlayedGameResult getCurrentPlayerResult() {
        return participants.currentPlayerResult();
    }

    public PlayedGameResult getDealerResult() {
        return participants.dealerResult();
    }
}
