package view;

import domain.Game;
import domain.GameParticipant;
import domain.GameResult;
import domain.Player;
import domain.deck.Card;
import java.util.Arrays;
import java.util.List;

public class OutputView {
    private static final String DELIMITER = ", ";

    public void displayInitialDeal(Game game) {
        List<Player> players = game.getPlayers();
        List<String> playerNames = players.stream().map(Player::getName).toList();
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", String.join(DELIMITER, playerNames));
        System.out.printf("딜러카드: %s", getNotation(game.getDealerCards().getFirst()));
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
            System.out.printf(" - 결과: %s%n", participant.calculateScore());
        }
    }

    public void displayGameResult(Game game) {
        displayEmptyLine();
        System.out.println("## 최종 승패");
        System.out.print("딜러: ");
        displayDealerGameResult(game);
        displayEmptyLine();
        displayPlayerGameResult(game);
    }

    public void displayParticipantAndCards(GameParticipant gameParticipant) {
        String name = gameParticipant.getName();
        List<String> cardNotations = gameParticipant.getCards().stream()
                .map(this::getNotation)
                .toList();
        System.out.printf("%s카드: %s", name, String.join(DELIMITER, cardNotations));
    }

    public void displayAllParticipantsAndCards(List<? extends GameParticipant> gameParticipants) {
        gameParticipants.forEach((participant) -> {
            displayParticipantAndCards(participant);
            displayEmptyLine();
        });
    }

    public void displayEmptyLine() {
        System.out.println();
    }

    private void displayPlayerGameResult(Game game) {
        game.getPlayers()
                .forEach(player -> System.out.printf("%s: %s%n", player.getName(),
                        game.getPlayerGameResult(player).getName()));
    }

    private void displayDealerGameResult(Game game) {
        Arrays.stream(GameResult.values())
                .filter((gameResult) -> game.getDealerGameResultCount(gameResult) != 0)
                .forEach((gameResult) -> System.out.printf("%d%s ", game.getDealerGameResultCount(gameResult),
                        gameResult.getName()));
    }

    private String getNotation(Card card) {
        return card.getRank().getName() + card.getSuit().getName();
    }
}
