package team.blackjack.domain;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import team.blackjack.config.AppConfig;
import team.blackjack.domain.rule.BlackjackRule;

public class Hand {
    private final BlackjackRule blackjackRule;
    private final Set<Card> cards = new LinkedHashSet<>();

    public Hand() {
        this.blackjackRule = AppConfig.getInstance().blackjackRule();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards.stream().toList();
    }

    public List<String> getCardNames() {
        return cards.stream()
                .map(Card::getCardName)
                .toList();
    }

    public int getScore() {
        return blackjackRule.calculateBestScore(this.getCards());
    }
}
