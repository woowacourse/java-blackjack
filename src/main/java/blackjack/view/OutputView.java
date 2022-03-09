package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.CardNumber;
import blackjack.domain.CardPattern;
import blackjack.dto.PlayerInfo;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PLAYER_NAME_DELIMITER = ", ";
    private static final String CARD_INFO_DELIMITER = ", ";

    private OutputView() {
        throw new UnsupportedOperationException();
    }

    public static void showGameInitInfo(final PlayerInfo dealerInfo, final List<PlayerInfo> playerInfos) {
        System.out.printf("%s와 %s에게 2장의 나누었습니다.\n", dealerInfo.getName(), joinPlayerNames(playerInfos));
        System.out.printf("%s: %s\n", dealerInfo.getName(), joinPlayerCardInfos(dealerInfo.getCards()));
        playerInfos.forEach(OutputView::printPlayerCardInfo);
    }

    private static String joinPlayerNames(final List<PlayerInfo> playerInfos) {
        return playerInfos.stream()
                .map(PlayerInfo::getName)
                .collect(Collectors.joining(PLAYER_NAME_DELIMITER));
    }

    private static String joinPlayerCardInfos(final List<Card> cards) {
        return cards.stream()
                .map(card -> joinCardInfo(card.getPattern(), card.getNumber()))
                .collect(Collectors.joining(CARD_INFO_DELIMITER));
    }

    private static String joinCardInfo(final CardPattern pattern, final CardNumber number) {
        return number.getPrintValue() + pattern.getName();
    }

    private static PrintStream printPlayerCardInfo(final PlayerInfo playerInfo) {
        return System.out.printf("%s카드: %s\n", playerInfo.getName(), joinPlayerCardInfos(playerInfo.getCards()));
    }
}
