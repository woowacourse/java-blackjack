import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private Deck deck = new Deck();
    private ScoreCalculator scoreCalculator = new ScoreCalculator();
    private Players players = new Players();
    private Dealer dealer = new Dealer();

    public GameManager() {
        deck.init();
        deck.shuffle();
    }

    public void startGame() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players.getPlayers()) {
                player.addCard(deck.draw());
            }
            dealer.addCard(deck.draw());
        }
    }

    public void judgeBust(int score, Player currentPlayer) {
        if (scoreCalculator.isBust(score)) {
            currentPlayer.setBust();
        }
    }

    public int calculateScore(List<Card> hand) {
        return scoreCalculator.calculateScore(hand);
    }

    public List<String> drawCard(Player player) {
        player.addCard(deck.draw());
        judgeBust(calculateScore(player.getHand()), player);
        return player.getHandToString();
    }

    public void addPlayer(String name) {
        players.add(new Player(name));
    }

    public List<Player> getPlayerSequence() {
        return players.getPlayers();
    }

    public List<GameScoreResultDto> getScoreResults(){
        List<GameScoreResultDto> results = new ArrayList<>();
        // dealer
        results.add(new GameScoreResultDto(
                dealer.getName(),
                dealer.getHandToString(),
                scoreCalculator.calculateScore(dealer.getHand())
                ));
        //players
        for (Player player : players.getPlayers()) {
            results.add(new GameScoreResultDto(
                    player.getName(),
                    player.getHandToString(),
                    scoreCalculator.calculateScore(dealer.getHand())
            ));
        }

        return results;
    }

    public List<GameInitialInfoDto> getInitialInfo() {
        List<GameInitialInfoDto> results = new ArrayList<>();

        // 딜러 첫 카드 공개
        List<String> dealerOpenCard = new ArrayList<>();
        dealerOpenCard.add(dealer.getHandToString().getFirst());

        // dealer
        results.add(new GameInitialInfoDto(
                dealer.getName(),
                2,
                dealerOpenCard
        ));
        //players
        for (Player player : players.getPlayers()) {
            results.add(new GameInitialInfoDto(
                    player.getName(),
                    2,
                    player.getHandToString()
            ));
        }

        return results;
    }

    public boolean isBlackjack(Player player) {
        return false;
    }
}
