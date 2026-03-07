package blackjack.view.message;

import blackjack.domain.card.Rank;

public enum RankMessage {

    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"),
    SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"),
    JACK("J"), QUEEN("Q"), KING("K"), ACE("A");

    private final String name;

    RankMessage(String name) {
        this.name = name;
    }

    public static String of(Rank rank) {
        for (RankMessage rankMessage : RankMessage.values()) {
            if (rank.name().equals(rankMessage.name())) return rankMessage.getName();
        }

        throw new IllegalArgumentException("존재하지 않는 카드입니다.");
    }

    public String getName() {
        return name;
    }
}
