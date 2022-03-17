package blackjack.model.player;

import blackjack.model.bet.Bet;
import blackjack.model.bet.Profits;
import blackjack.model.bet.Result;
import blackjack.model.trumpcard.TrumpCard;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class Entries {
    private static final String ERROR_DUPLICATE_NAME = "[ERROR] 중복된 이름이 있습니다.";
    private static final String ERROR_NO_ENTRY = "[ERROR] 더 이상 Entry가 없습니다.";
    private static final int INITIAL_INDEX = -1;

    private final List<Entry> values;
    private int currentIndex = INITIAL_INDEX;

    private Entries(List<Entry> values) {
        this.values = List.copyOf(values);
    }

    public static Entries from(List<String> names) {
        checkDuplicate(names);
        List<Entry> entries = names.stream()
                .map(Entry::new)
                .collect(Collectors.toList());
        return new Entries(entries);
    }

    private static void checkDuplicate(List<String> names) {
        if (countDistinct(names) != names.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NAME);
        }
    }

    private static int countDistinct(List<String> names) {
        return (int) names.stream()
                .distinct()
                .count();
    }

    public void betToCurrent(Bet bet) {
        getCurrentEntry().bet(bet);
    }

    public void initializeHands(Supplier<TrumpCard> cardSupplier) {
        for (Entry entry : values) {
            entry.initializeHand(cardSupplier);
        }
    }

    public void toNextEntry() {
        if (hasNoNext()) {
            throw new RuntimeException(ERROR_NO_ENTRY);
        }
        this.currentIndex++;
    }

    public void resetCursor() {
        this.currentIndex = INITIAL_INDEX;
    }

    public boolean hasNoNext() {
        return values.size() <= currentIndex + 1;
    }

    public void addToCurrentEntry(TrumpCard card) {
        getCurrentEntry().addCard(card);
    }

    public boolean isCurrentEntryBust() {
        return getCurrentEntry().isBust();
    }

    public Entry getCurrentEntry() {
        return this.values.get(currentIndex);
    }

    public String getCurrentEntryName() {
        return getCurrentEntry().getName();
    }

    public List<Entry> getValues() {
        return this.values;
    }

    public Profits compareAllWith(Dealer dealer) {
        return Profits.of(this.values.stream()
                .collect(Collectors.toMap(
                        entry -> entry,
                        entry -> entry.profitOf(compare(dealer, entry)),
                        (a, b) -> b)), dealer);
    }

    private Result compare(Dealer dealer, Entry entry) {
        return dealer.compareWith(entry);
    }
}
