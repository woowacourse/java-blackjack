package view;

import game.BlackJackGame;
import bank.GamblingStatement;
import bank.Money;
import card.Card;
import card.ParticipantCardDeck;
import participant.Participant;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {

    public void writeDrawTwoCards(final BlackJackGame blackJackGame) {
        System.out.println();
        for (Participant participant : blackJackGame.getParticipants().getParticipants()) {
            String name = participant.getNickname();
            List<Card> cards = participant.getCardDeck().getCards();
            if (participant.isDealer()) {
                System.out.printf("%s : %s%s%n", name, cards.getFirst().getNumber(), cards.getFirst().getCardSymbol());
                continue;
            }
            System.out.printf("%s 카드 : %s%s, %s%s%n", name,
                    cards.getFirst().getNumber(),
                    cards.getFirst().getCardSymbol(),
                    cards.getLast().getNumber(),
                    cards.getLast().getCardSymbol());
        }
    }

    public void writeDealerDrawOneCard(int count) {
        for (int i = 0; i < count; i ++) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
    }

    public void writePlayerDrawOneCard(Participant participant) {
        ParticipantCardDeck participantCardDeck = participant.getCardDeck();
        List<Card> cards = participantCardDeck.getCards();
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Card card : cards) {
            stringJoiner.add(card.getNumber() + card.getCardSymbol());
        }
        System.out.printf("%s 카드 : %s%n", participant.getNickname(), stringJoiner);
    }

    public void writeParticipantsScoreResult(BlackJackGame blackJackGame) {
        System.out.println();
        List<Participant> participants = blackJackGame.getParticipants().getParticipants();
        for (Participant participant : participants) {
            List<Card> cards = participant.getCardDeck().getCards();
            StringJoiner stringJoiner = new StringJoiner(", ");
            for (Card card : cards) {
                stringJoiner.add(card.getNumber() + card.getCardSymbol());
            }
            System.out.printf("%s 카드 : %s - 결과 : %s%n", participant.getNickname(), stringJoiner, participant.getScore());
        }
    }

    public void writeTotalProfit(GamblingStatement gamblingStatement) {
        System.out.println();
        System.out.println("## 최종 수익");
        Map<Participant, Money> participantProfit = gamblingStatement.getGamblingStatement();
        StringBuilder playerStringBuilder = new StringBuilder();
        double dealerProfit = 0;
        for (Map.Entry<Participant, Money> entry : participantProfit.entrySet()) {
            String name = entry.getKey().getNickname();
            String profit = String.valueOf(entry.getValue().getAmount());
            dealerProfit += (-Double.parseDouble(profit));
            playerStringBuilder.append(name).append(" : ").append(profit).append("\n");
        }
        System.out.println("딜러 : " + dealerProfit);
        System.out.println(playerStringBuilder);
    }
}
