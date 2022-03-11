package blackjack.view;

import blackjack.dto.DealerDTO;
import blackjack.dto.DeckDTO;
import blackjack.dto.EntryDTO;
import blackjack.dto.PlayerDTO;
import blackjack.dto.PlayersDTO;
import java.util.List;

public class ResultView {
    private static final String FORMAT_MESSAGE_DECK_INITIALIZED = "%n딜러와 %s에게 2장의 카드를 나누었습니다.%n";
    private static final String FORMAT_DECK_INITIALIZED = "%s : %s%n";
    private static final String FORMAT_MESSAGE_BUST = "%s의 점수 합이 21을 넘어, 다음 참가자로 넘어갑니다.%n";
    private static final String FORMAT_MESSAGE_DEALER_HIT = "%n딜러는 16이하라 %d장의 카드를 더 받았습니다.%n";
    private static final String FORMAT_SCORE = "%s 카드: %s - 결과: %d%n";

    private static final String DELIMITER_JOIN = ", ";

    public void printDeckInitialized(PlayersDTO players) {
        System.out.printf(FORMAT_MESSAGE_DECK_INITIALIZED, joinStrings(players.getNames()));
    }

    public void printInitializedDecks(PlayersDTO players) {
        for (PlayerDTO player : players.getPlayers()) {
            printDeck(player);
        }
    }

    public void printDeck(PlayerDTO player) {
        System.out.printf(FORMAT_DECK_INITIALIZED, player.getName(), joinDeck(player.getDeck()));
    }

    public void printBustMessage(EntryDTO entry) {
        System.out.printf(FORMAT_MESSAGE_BUST, entry.getName());
    }

    public void printDealerHitCount(DealerDTO dealer) {
        System.out.printf(FORMAT_MESSAGE_DEALER_HIT, dealer.getAddedCount());
    }

    public void printScores(PlayersDTO players) {
        for (PlayerDTO player : players.getPlayers()) {
            printScore(player);
        }
    }

    private void printScore(PlayerDTO player) {
        System.out.printf(FORMAT_SCORE, player.getName(), joinDeck(player.getDeck()), player.getScore());
    }

    private String joinDeck(DeckDTO deck) {
        return joinStrings(deck.getCards());
    }
    private String joinStrings(List<String> strings) {
        return String.join(DELIMITER_JOIN, strings);
    }
}
