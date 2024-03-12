package blackjack.view;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Players;

import blackjack.view.mapper.CardRankMapper;
import blackjack.view.mapper.CardSuitMapper;
import java.util.stream.Collectors;

public class MessageResolver {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String SEPARATOR = ", ";
    private static final String DEALER_NAME = "딜러";
    private static final String PARTICIPANT_HAND_FORMAT = "%s 카드: %s";

    public String resolveDealDescriptionMessage(Players players) {
        String message = String.format("딜러와 %s에게 2장을 나누었습니다.", resolveNamesMessage(players));
        return String.join("", LINE_SEPARATOR, message);
    }

    private String resolveNamesMessage(Players players) {
        return players.getPlayers().stream()
                .map(Player::getPlayerName)
                .map(PlayerName::getValue)
                .collect(Collectors.joining(SEPARATOR));
    }

    public String resolveDealToAllMessage(Participants participants) {
        return participants.getParticipants().stream()
                .map(this::resolveDealToOneMessage)
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private String resolveDealToOneMessage(Participant participant) {
        if (participant instanceof Dealer) {
            return resolveDealerFirstCardMessage(participant.getHand());
        }
        if (participant instanceof Player) {
            return resolveParticipantHandMessage(participant);
        }
        throw new IllegalArgumentException();
    }

    private String resolveDealerFirstCardMessage(Hand hand) {
        return String.format(PARTICIPANT_HAND_FORMAT, DEALER_NAME, resolveCardMessage(hand.getCards().get(0)));
    }

    public String resolveParticipantHandMessage(Participant participant) {
        return String.format(PARTICIPANT_HAND_FORMAT, resolveNameMessage(participant),
                resolveHandMessage(participant.getHand()));
    }

    private String resolveNameMessage(Participant participant) {
        if (participant instanceof Dealer) {
            return DEALER_NAME;
        }
        if (participant instanceof Player) {
            return ((Player) participant).getPlayerName().getValue();
        }
        throw new IllegalArgumentException();
    }

    private String resolveHandMessage(Hand hand) {
        return hand.getCards().stream()
                .map(this::resolveCardMessage)
                .collect(Collectors.joining(SEPARATOR));
    }

    private String resolveCardMessage(Card card) {
        String rankSymbol = CardRankMapper.toSymbol(card.getCardRank());
        String suitSymbol = CardSuitMapper.toSymbol(card.getCardSuit());
        return String.format("%s%s", rankSymbol, suitSymbol);
    }

    public String resolveDrawToDealerMessage() {
        return String.format("%s는 16이하라 한장의 카드를 더 받았습니다.", DEALER_NAME);
    }

    public String resolveParticipantsHandScoreMessage(Participants participants) {
        return participants.getParticipants().stream()
                .map(this::resolveParticipantHandScoreMessage)
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private String resolveParticipantHandScoreMessage(Participant participant) {
        return String.format("%s - 결과: %d", resolveParticipantHandMessage(participant),
                participant.calculateHandTotal());
    }

    public String resolveDealerResult(Result result) {
        return String.format("%s: %d승 %d패", DEALER_NAME, result.getWinCount(), result.getLoseCount());
    }

    public String resolvePlayersResult(Players players, Dealer dealer) {
        return players.getPlayers().stream()
                .map(player -> resolvePlayerResult(player, dealer))
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private String resolvePlayerResult(Player player, Dealer dealer) {
        if (player.judge(dealer).getWinCount() == 1) {
            return String.format("%s: 승", player.getPlayerName().getValue());
        }
        return String.format("%s: 패", player.getPlayerName().getValue());
    }
}
