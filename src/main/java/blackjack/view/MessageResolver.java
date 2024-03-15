package blackjack.view;

import static blackjack.domain.card.Hand.INITIAL_HAND_SIZE;
import static blackjack.view.CardDescription.NUMBER_NAME;
import static blackjack.view.CardDescription.SHAPE_NAME;

import blackjack.domain.bet.Profit;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Hand;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.PlayerProfitResult;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageResolver {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String SEPARATOR = ", ";

    public String resolvePlayerHandMessage(Participant participant) {
        return String.format("%s 카드: %s", participant.getName(), resolveHandMessage(participant.getHand()));
    }

    public String resolveInitialHandsMessage(Dealer dealer, Players players) {
        return new StringBuilder()
                .append(resolveHandOutEventMessage(players))
                .append(LINE_SEPARATOR)
                .append(resolveDealerHandMessage(dealer))
                .append(LINE_SEPARATOR)
                .append(resolvePlayersHandMessage(players))
                .toString();
    }

    public String resolveCompletedHandsMessage(Dealer dealer, Players players) {
        return new StringBuilder()
                .append(resolveDealerPopCountMessage(dealer))
                .append(resolvePlayerScore(dealer))
                .append(LINE_SEPARATOR)
                .append(resolvePlayersScore(players))
                .toString();
    }

    public String resolveProfitResultMessage(PlayerProfitResult playerProfitResult) {
        return new StringBuilder()
                .append(LINE_SEPARATOR)
                .append("##최종 수익")
                .append(LINE_SEPARATOR)
                .append(resolveParticipantProfitMessage(playerProfitResult.calculateDealerProfit()))
                .append(LINE_SEPARATOR)
                .append(resolvePlayerProfitMessage(playerProfitResult))
                .toString();
    }

    private String resolveHandOutEventMessage(Players players) {
        String namesMessage = resolveNamesMessage(players);
        String message = String.format("딜러와 %s에게 %d장을 나누었습니다.", namesMessage, INITIAL_HAND_SIZE);
        return String.join("", LINE_SEPARATOR, message);
    }

    private String resolveNamesMessage(Players players) {
        return players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(SEPARATOR));
    }

    private String resolveDealerHandMessage(Dealer dealer) {
        Card card = dealer.getHand().getCards().get(0);
        return String.format("%s: %s", dealer.getName(), resolveCardMessage(card));
    }

    private String resolvePlayersHandMessage(Players players) {
        return players.getPlayers().stream()
                .map(this::resolvePlayerHandMessage)
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private String resolveHandMessage(Hand hand) {
        return hand.getCards().stream()
                .map(this::resolveCardMessage)
                .collect(Collectors.joining(SEPARATOR));
    }

    private String resolveCardMessage(Card card) {
        CardNumber cardNumber = card.getCardNumber();
        CardShape cardShape = card.getCardShape();
        return String.format("%s%s", NUMBER_NAME.get(cardNumber), SHAPE_NAME.get(cardShape));
    }

    private String resolveDealerPopCountMessage(Dealer dealer) {
        if (dealer.countDraw() > 0) {
            String message = String.format("딜러는 %d이하라 %d장의 카드를 더 받았습니다.", Dealer.HIT_THRESHOLD, dealer.countDraw());
            return String.join("", LINE_SEPARATOR, message, LINE_SEPARATOR);
        }
        return "";
    }

    private String resolvePlayersScore(Players players) {
        return players.getPlayers().stream()
                .map(this::resolvePlayerScore)
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private String resolvePlayerScore(Participant participant) {
        String handMessage = resolvePlayerHandMessage(participant);
        return String.format("%s - 결과: %d", handMessage, participant.calculateHandScore().getValue());
    }

    private String resolveParticipantProfitMessage(Profit dealerProfit) {
        return String.format("딜러: %d", dealerProfit.getValue());
    }

    private String resolvePlayerProfitMessage(PlayerProfitResult playerProfitResult) {
        Map<Player, Profit> playerProfitMap = playerProfitResult.getPlayerProfitMap();
        return playerProfitMap.entrySet().stream()
                .map(this::resolveSingleProfitMessage)
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private String resolveSingleProfitMessage(Map.Entry<Player, Profit> playerProfit) {
        String playerName = playerProfit.getKey().getName();
        int profit = playerProfit.getValue().getValue();
        return String.format("%s: %d", playerName, profit);
    }
}
