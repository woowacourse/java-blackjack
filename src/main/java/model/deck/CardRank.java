package model.deck;

public enum CardRank {
    ACE("A", new SoftValue(1, 11)),
    TWO("2", new HardValue(2)),
    THREE("3", new HardValue(3)),
    FOUR("4", new HardValue(4)),
    FIVE("5", new HardValue(5)),
    SIX("6", new HardValue(6)),
    SEVEN("7", new HardValue(7)),
    EIGHT("8", new HardValue(8)),
    NINE("9", new HardValue(9)),
    TEN("10", new HardValue(10)),
    JACK("J", new HardValue(10)),
    QUEEN("Q", new HardValue(10)),
    KING("K", new HardValue(10));

    private final String name;
    private final CardValue value;

    CardRank(String name, CardValue cardValue) {
        this.name = name;
        this.value = cardValue;
    }

    public String getName() {
        return name;
    }

    public int getDefaultValue() {
        return value.getDefaultValue();
    }

    public int getMaxValue() {
        return value.getMaxValue();
    }

    public boolean hasMaxValue() {
        return value.hasMaxValue();
    }

    public static class SoftValue implements CardValue {
        private final int defaultValue;
        private final int maxValue;

        public SoftValue(int defaultValue, int maxValue) {
            this.defaultValue = defaultValue;
            this.maxValue = maxValue;
        }

        @Override
        public int getDefaultValue() {
            return defaultValue;
        }

        @Override
        public boolean hasMaxValue() {
            return true;
        }

        @Override
        public int getMaxValue() {
            return maxValue;
        }
    }

    public static class HardValue implements CardValue {
        private final int defaultValue;

        public HardValue(int defaultValue) {
            this.defaultValue = defaultValue;
        }

        @Override
        public int getDefaultValue() {
            return defaultValue;
        }

        @Override
        public boolean hasMaxValue() {
            return false;
        }

        @Override
        public int getMaxValue() {
            throw new IllegalStateException("최대 값을 선택할 수 없는 카드입니다.");
        }
    }
}
