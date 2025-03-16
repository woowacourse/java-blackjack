import console.GameSetupConsole;
import model.card.Deck;
import model.card.DeckGenerator;
import model.participant.Players;

public final class Application {
    public static void main(String[] args) {
        Deck deck = DeckGenerator.generateRandomDeck();
        GameSetupConsole gameSetupConsole = new GameSetupConsole();
        Players players = gameSetupConsole.registerPlayers(deck);
    }
}
