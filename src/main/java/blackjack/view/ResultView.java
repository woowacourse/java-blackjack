package blackjack.view;

import blackjack.dto.DealerDTO;
import blackjack.dto.DeckDTO;
import blackjack.dto.EntryDTO;
import blackjack.dto.PlayerDTO;
import blackjack.dto.PlayersDTO;
import blackjack.dto.TrumpCardDTO;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    private static final String FORMAT_MESSAGE_DECK_INITIALIZED = "%n%s와 %s에게 2장의 카드를 나누었습니다.%n";
    private static final String FORMAT_DECK_INITIALIZED = "%s : %s%n";
    private static final String FORMAT_MESSAGE_BUST = "%n%s의 점수 합이 21을 넘어, 다음 참가자로 넘어갑니다.%n%n";
    private static final String FORMAT_MESSAGE_DEALER_HIT = "%n%s는 16이하라 %d장의 카드를 더 받았습니다.%n";
    private static final String FORMAT_SCORE = "%s 카드: %s - 결과: %d%n";

    private static final String DELIMITER_JOIN = ", ";

    public void printDeckInitialized(PlayersDTO players) {
        DealerDTO dealer = players.getDealer();
        System.out.printf(FORMAT_MESSAGE_DECK_INITIALIZED, dealer.getName(), joinStrings(players.getEntryNames()));
    }

    public void printInitializedDecks(PlayersDTO players) {
        for (PlayerDTO player : players.getPlayers()) {
            printFirstDeck(player);
        }
    }

    private void printFirstDeck(PlayerDTO player) {
        if (player instanceof DealerDTO) {
            printOnlyFirstCard((DealerDTO) player);
            return;
        }
        printDeck(player);
    }

    private void printOnlyFirstCard(DealerDTO dealer) {
        System.out.printf(FORMAT_DECK_INITIALIZED, dealer.getName(), getFirstCardToString(dealer.getDeck()));
    }

    private String getFirstCardToString(DeckDTO deck) {
        TrumpCardDTO firstCard = deck.getCards().get(0);
        return firstCard.getNumber() + firstCard.getSymbol();
    }

    public void printDeck(PlayerDTO player) {
        System.out.printf(FORMAT_DECK_INITIALIZED, player.getName(), joinDeck(player.getDeck()));
    }

    public void printBustMessage(EntryDTO entry) {
        System.out.printf(FORMAT_MESSAGE_BUST, entry.getName());
    }

    public void printDealerHitCount(DealerDTO dealer) {
        System.out.printf(FORMAT_MESSAGE_DEALER_HIT, dealer.getName(), dealer.getAddedCount());
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
        return joinCards(deck.getCards());
    }

    private String joinCards(List<TrumpCardDTO> cards) {
        return joinStrings(cards.stream()
                .map(card -> card.getNumber() + card.getSymbol())
                .collect(Collectors.toList()));
    }

    private String joinStrings(List<String> strings) {
        return String.join(DELIMITER_JOIN, strings);
    }
}
