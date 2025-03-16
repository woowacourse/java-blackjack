import console.GamePlayConsole;
import console.GameSetupConsole;
import model.card.Deck;
import model.card.DeckGenerator;
import model.participant.Participants;

public final class Application {
    public static void main(String[] args) {
        Deck deck = DeckGenerator.generateRandomDeck();
        GameSetupConsole gameSetupConsole = new GameSetupConsole();
        Participants participants = gameSetupConsole.registerParticipants(deck);
        gameSetupConsole.displaySetupResult(participants);
        GamePlayConsole gamePlayConsole = new GamePlayConsole();
        gamePlayConsole.dealAllPlayerCards(participants.getPlayers(), deck);
    }
}
