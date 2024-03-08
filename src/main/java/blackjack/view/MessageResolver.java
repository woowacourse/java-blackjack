package blackjack.view;

import blackjack.domain.DealerGameResult;
import blackjack.domain.card.Card;
import blackjack.domain.player.Hand;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.rule.Score;

import java.util.stream.Collectors;

public class MessageResolver {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String SEPARATOR = ", ";

    public String resolveHandOutEventMessage(Players players, int handOutCount) {
        String namesMessage = resolveNamesMessage(players);
        return String.format("딜러와 %s에게 %d장을 나누었습니다.", namesMessage, handOutCount);
    }

    private String resolveNamesMessage(Players players) {
        return players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(SEPARATOR));
    }

    public String resolvePlayerHandMessage(Player player) {
        return String.format("%s 카드: %s", player.getName(), resolveHandMessage(player.getHand()));
    }

    private String resolveHandMessage(Hand hand) {
        return hand.getCards().stream()
                .map(this::resolveCardMessage)
                .collect(Collectors.joining(SEPARATOR));
    }

    private String resolveCardMessage(Card card) {
        return String.format("%s%s", card.getCardNumberName(), card.getCardShape());
    }

    public String resolveDealerHandMessage(Player dealer) {
        Card card = dealer.getHand().getCards().get(0);
        return String.format("딜러: %s", resolveCardMessage(card));
    }

    public String resolveDealerPopCountMessage(int dealerDrawThreshold, int popCount) {
        return String.format("딜러는 %d이하라 %d장의 카드를 더 받았습니다.", dealerDrawThreshold, popCount);
    }

    public String resolvePlayerScoreMessage(Player player, Score score) {
        String handMessage = resolvePlayerHandMessage(player);
        return String.format("%s - 결과: %d", handMessage, score.getValue());
    }

    public String resolvePlayerGameResult(Player player, boolean win) {
        return String.format("%s: %s", player.getName(), resolveGameResultMessage(win));
    }

    private String resolveGameResultMessage(boolean win) {
        if (win) {
            return "승";
        }
        return "패";
    }

    public String resolveDealerGameResult(DealerGameResult dealerGameResult) {
        return String.format("딜러: %d승 %d패", dealerGameResult.getWinCount(), dealerGameResult.getLoseCount());
    }

}
