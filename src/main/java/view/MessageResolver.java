package view;

import domain.card.Card;
import domain.card.Symbol;
import domain.game.Result;
import domain.game.WinLose;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class MessageResolver {

    private static final String DELIMITER = ", ";
    private static final String WIN_TEXT = "승";
    private static final String TIE_TEXT = "무";
    private static final String LOSE_TEXT = "패";

    public String playerNamesText(List<Player> players) {
        return players.stream()
            .map(Participant::getName)
            .collect(Collectors.joining(DELIMITER));
    }

    public String dealerNameAndStartingCardsText(Dealer dealer) {
        List<Card> dealerCards = dealer.getCards();
        if (dealerCards.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 딜러 카드가 없습니다.");
        }
        return String.format("%s 카드: %s", dealer.getName(), cardText(dealerCards.get(0)));
    }

    public String cardsText(List<Card> cards) {
        return cards.stream()
            .map(this::cardText)
            .collect(Collectors.joining(DELIMITER));
    }

    public String cardText(Card card) {
        return card.rank().getDescription() + symbolText(card.symbol());
    }

    public String symbolText(Symbol symbol) {
        if (symbol == Symbol.CLUB) {
            return "클로버";
        }
        if (symbol == Symbol.DIAMOND) {
            return "다이아몬드";
        }
        if (symbol == Symbol.HEART) {
            return "하트";
        }
        if (symbol == Symbol.SPADE) {
            return "스페이드";
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 Symbol입니다.");
    }

    public String participantNameAndCardsText(Participant participant) {
        return String.format("%s 카드: %s", participant.getName(), cardsText(participant.getCards()));
    }

    public String scoreText(int score) {
        return String.valueOf(score);
    }

    public String dealerResultText(Dealer dealer, Result result) {
        return String.format("%s: %s%s%s",
            dealer.getName(),
            dealerSingleResultText(result.dealerWinCount(), WIN_TEXT),
            dealerSingleResultText(result.dealerTieCount(), TIE_TEXT),
            dealerSingleResultText(result.dealerLoseCount(), LOSE_TEXT)
        );
    }

    private String dealerSingleResultText(long count, String suffix) {
        if (count == 0) {
            return "";
        }
        return count + suffix;
    }

    public String playerResultText(Player player, Result result) {
        return String.format("%s: %s", player.getName(), winLoseText(result.playerWinLose(player)));
    }

    private String winLoseText(WinLose winLose) {
        if (winLose == WinLose.WIN) {
            return WIN_TEXT;
        }
        if (winLose == WinLose.TIE) {
            return TIE_TEXT;
        }
        if (winLose == WinLose.LOSE) {
            return LOSE_TEXT;
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 WinLose입니다.");
    }
}
