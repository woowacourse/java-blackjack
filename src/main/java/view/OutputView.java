package view;

import domain.card.Card;
import domain.game.Game;
import domain.game.GameResult;
import domain.participant.GameParticipant;
import domain.participant.Player;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class OutputView {
    private static final String DELIMITER = ", ";

    public void displayInitialDeal(Game game) {
        List<Player> players = game.getPlayers();
        List<String> playerNames = players.stream().map(Player::getName).toList();
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", String.join(DELIMITER, playerNames));
        System.out.printf("딜러카드: %s", game.getDealerCards().getFirst().getNotation());
        displayEmptyLine();
        displayAllParticipantsAndCards(players);
        displayEmptyLine();
    }

    public void displayDealerHitResult() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public void displayScore(Game game) {
        List<GameParticipant> participants = game.getParticipants();
        for (GameParticipant participant : participants) {
            displayParticipantAndCards(participant);
            System.out.printf(" - 결과: %d%n", participant.calculateScore());
        }
    }

    public void displayGameResult(Game game, EnumMap<GameResult, Integer> gameResults) {
        displayEmptyLine();
        System.out.println("## 최종 승패");
        System.out.print("딜러: ");
        displayDealerGameResult(gameResults);
        displayEmptyLine();
        displayPlayerGameResult(game);
    }

    public void displayParticipantAndCards(GameParticipant gameParticipant) {
        String name = gameParticipant.getName();
        List<String> cardNotations = getCardNotations(gameParticipant.getCards());
        System.out.printf("%s카드: %s", name, String.join(DELIMITER, cardNotations));
    }

    public void displayNameAndCards(String name, List<Card> cards) {
        List<String> cardNotations = getCardNotations(cards);
        System.out.printf("%s카드: %s", name, String.join(DELIMITER, cardNotations));
    }

    public void displayAllParticipantsAndCards(List<? extends GameParticipant> gameParticipants) {
        gameParticipants.forEach((participant) -> {
            getCardNotations(participant.getCards());
            displayParticipantAndCards(participant);
            displayEmptyLine();
        });
    }

    public void displayEmptyLine() {
        System.out.println();
    }

    private List<String> getCardNotations(List<Card> cards) {
        return cards.stream()
                .map(Card::getNotation)
                .toList();
    }

    private void displayPlayerGameResult(Game game) {
        game.getPlayers()
                .forEach(player -> System.out.printf("%s: %s%n", player.getName(),
                        player.calculateGameResult(game.getDealer()).getName()));
    }

    private void displayDealerGameResult(EnumMap<GameResult, Integer> gameResults) {
        Arrays.stream(GameResult.values())
                .filter((gameResult) -> gameResults.get(gameResult) != 0)
                .forEach((gameResult) -> System.out.printf("%d%s ", gameResults.get(gameResult), gameResult.getName()));
    }
}
