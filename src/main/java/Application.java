import console.GamePlayConsole;
import console.GameResultConsole;
import console.GameSetupConsole;
import card.Deck;
import card.DeckGenerator;
import participant.Participants;

public final class Application {
    public static void main(String[] args) {
        Deck deck = DeckGenerator.generateRandomDeck();
        GameSetupConsole gameSetupConsole = new GameSetupConsole();
        Participants participants = gameSetupConsole.registerParticipants(deck);
        gameSetupConsole.displaySetupResult(participants);
        GamePlayConsole gamePlayConsole = new GamePlayConsole();
        gamePlayConsole.drawAllPlayerCards(participants.getPlayers(), deck);
        gamePlayConsole.drawDealerCards(participants.getDealer(), deck);
        GameResultConsole gameResultConsole = new GameResultConsole();
        gameResultConsole.getFinalScores(participants);
        gameResultConsole.getFinalProfits(participants);
    }
}
