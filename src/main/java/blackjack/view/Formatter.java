package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Participant;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.Score;
import java.util.List;
import java.util.stream.Collectors;

public final class Formatter {

    private Formatter() {
    }

    public static String formatPlayerCardResult(PlayerResult playerResult) {
        String message = formatPlayerCardStatus(playerResult.getPlayer()) + " - 결과: ";
        return message + formatCardResultValue(playerResult);
    }

    public static String formatPlayerCardStatus(Participant participant) {
        return participant.getName() + "카드: " + formatCardStatus(participant);
    }

    public static String formatDealerCardStatus(Dealer dealer) {
        List<Card> cards = dealer.getCards();
        return "딜러카드: " + Formatter.formatCard(cards.getFirst());
    }

    public static String formatDealerCardResult(Dealer dealer, DealerResult dealerResult) {
        int scoreValue = dealerResult.getScoreValue();
        return "딜러 카드: " + formatCardStatus(dealer) + " - 결과: " + scoreValue;
    }

    private static String formatCardResultValue(PlayerResult playerResult) {
        Score score = playerResult.getScore();

        if (score.isBusted()) {
            return "Busted!";
        }

        return java.lang.String.valueOf(score.getScoreValue());
    }

    private static String formatCardStatus(Participant participant) {
        return participant.getCards().stream()
                .map(Formatter::formatCard)
                .collect(Collectors.joining(", "));
    }

    public static String formatCard(Card card) {
        CardRank rank = card.getRank();
        CardSuit suit = card.getSuit();

        String formatCardRank = formatCardRank(rank);
        String formatCardSuit = formatCardSuit(suit);

        return formatCardRank + formatCardSuit;
    }

    private static String formatCardRank(CardRank cardRank) {
        if (cardRank == CardRank.ACE) {
            return "A";
        }

        if (cardRank == CardRank.KING) {
            return "K";
        }

        if (cardRank == CardRank.QUEEN) {
            return "Q";
        }

        if (cardRank == CardRank.JACK) {
            return "J";
        }

        List<Integer> values = cardRank.getValues();
        return String.valueOf(values.getFirst());
    }

    private static String formatCardSuit(CardSuit cardSuit) {
        if (cardSuit == CardSuit.SPADE) {
            return "스페이드";
        }

        if (cardSuit == CardSuit.CLUB) {
            return "클로버";
        }
        if (cardSuit == CardSuit.DIAMOND) {
            return "다이아몬드";
        }
        return "하트";
    }
}
