package domain;

import domain.strategy.RandomStrategy;
import domain.vo.NameAndCardInfos;
import dto.NameAndCardsResponse;
import java.util.List;

public class GameTable {

    private final Participants participants;
    private final DrawStrategy drawStrategy;

    public GameTable() {
        Dealer dealer = Dealer.of("딜러");
        this.participants = new Participants(dealer);
        this.drawStrategy = new RandomStrategy();
    }

    public void addPlayer(String name) {
        participants.addPlayer(name);
    }

    public List<String> allPlayerNames() {
        return participants.allPlayerNames();
    }

    public void allParticipantsDrawInitialCards() {
        participants.allParticipantsDrawInitialCards(drawStrategy);
    }

    public NameAndCardInfos dealerCardsInfo() {
        return participants.dealerCardInfos();
    }

    public List<NameAndCardInfos> allPlayerCardInfos() {
        return participants.allPlayerCardInfos();
    }

    public NameAndCardInfos currentPlayersCardInfos() {
        return participants.currentPlayerCardInfos();
    }

//
//    public List<GameStatus> initGameStatus() {
//        List<GameStatus> gameStatuses = new ArrayList<>();
//        for (Participant participant : participants) {
//            gameStatuses.add(participant.status());
//        }
//
//        return gameStatuses;
//    }
//
//    public List<GameStatus> endedGameStatus() {
//        return scoreBoard.gameStatuses();
//    }
//
//    public void playCurrentPlayer() {
//        if (isPlayerExist()) {
//            currentPlayer().draw(drawStrategy);
//        }
//    }
//
//    public void playDealer() {
//        dealer().draw(drawStrategy);
//    }
//
//    public GameStatus currentPlayerStatus() {
//        return currentParticipant().status();
//    }
//
//    public Participant currentPlayer() {
//        Participant participant = participants.peek();
//
//        if (!participants.isEmpty() && !participant.isPlayer()) {
//            participants.add(participants.poll());
//        }
//
//        return participants.peek();
//    }
//
//    public boolean isPlayerExist() {
//        return !participants.isEmpty() && !hasOnlyDealer();
//    }

//    public void recordResult() {
//        Participant current = participants.poll();
//        scoreBoard.record(current.status());
//    }
//
//    public List<GameResult> result() {
//        return scoreBoard.playerResults();
//    }
//
//    public String currentPlayerName() {
//        return currentPlayer().name();
//    }
//
//    public boolean isCurrentPlayerPlayable() {
//        return currentPlayer().isPlayable();
//    }
//
//    public boolean isDealerPlayable() {
//        return currentParticipant().isPlayable();
//    }
//
//    private Participant dealer() {
//        return participants.stream().filter(p -> !p.isPlayer()).findFirst().orElse(null);
//    }
//
//    private boolean hasOnlyDealer() {
//        return participants.stream().noneMatch(Participant::isPlayer);
//    }
//
//    private Participant currentParticipant() {
//        return participants.peek();
//    }
//
//    public Participant dealersCard() {
//        return participants.stream()
//                .filter(participant -> !participant.isPlayer())
//                .findAny();
//    }

//    public NameAndCardInfos dealerCardsInfo() {
//        return participants.dealerCardInfos();
//    }
//
//    public NameAndCardInfos currentPlayersCardInfos() {
//        return participants.currentPlayerCardInfos();
//    }

//    public List<>
}
