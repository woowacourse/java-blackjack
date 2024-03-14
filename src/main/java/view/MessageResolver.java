package view;

import domain.card.Card;
import domain.card.Score;
import domain.card.Symbol;
import domain.game.PlayerResult;
import domain.game.PlayerResults;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class MessageResolver {

    private static final String DEALER_NAME = "딜러";
    private static final String DELIMITER = ", ";
    private static final String WIN_TEXT = "승";
    private static final String TIE_TEXT = "무";
    private static final String LOSE_TEXT = "패";

    public String playerNamesText(List<Player> players) {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.joining(DELIMITER));
    }

    public String dealerNameAndStartingCardsText(Dealer dealer) {
        return String.format("%s 카드: %s", DEALER_NAME, cardText(dealer.firstCard()));
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

    public String participantNameAndCardsText(String name, List<Card> cards) {
        return String.format("%s 카드: %s", name, cardsText(cards));
    }

    public String scoreText(Score score) {
        return String.valueOf(score.toInt());
    }

    public String dealerResultText(Dealer dealer, PlayerResults playerResults) {
        return String.format("%s: %s%s%s",
            DEALER_NAME,
            dealerSingleResultText(playerResults.dealerWinCount(), WIN_TEXT),
            dealerSingleResultText(playerResults.dealerTieCount(), TIE_TEXT),
            dealerSingleResultText(playerResults.dealerLoseCount(), LOSE_TEXT)
        );
    }

    private String dealerSingleResultText(long count, String suffix) {
        if (count == 0) {
            return "";
        }
        return count + suffix;
    }

    public String playerResultText(Player player, PlayerResults playerResults) {
        return String.format("%s: %s", player.getName(), winLoseText(playerResults.playerWinLose(player)));
    }

    private String winLoseText(PlayerResult playerResult) {
        if (playerResult == PlayerResult.WIN) {
            return WIN_TEXT;
        }
        if (playerResult == PlayerResult.TIE) {
            return TIE_TEXT;
        }
        if (playerResult == PlayerResult.LOSE) {
            return LOSE_TEXT;
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 WinLose입니다.");
    }
}
