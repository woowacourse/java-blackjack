package domain.score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Denomination;

public class TotalSumValues {

    private static final int SPECIAL_VALUE = 1;
    private static final int ANOTHER_SPECIAL_VALUE = 11;
    private static final int LIMIT_VALUE = 22;

    private final List<Integer> values;

    public TotalSumValues(final List<Integer> values) {
        this.values = new ArrayList<>(values);
    }

    public TotalSumValues addValuesToAllElement(TotalSumValues totalSumValues, List<Integer> addValues) {
        for (Integer addValue : addValues) {
            totalSumValues = getTotalSumValuesBySpecialValueExistence(totalSumValues, addValue);
        }
        return totalSumValues;
    }

    private TotalSumValues getTotalSumValuesBySpecialValueExistence(TotalSumValues totalSumValues, Integer addValue) {
        if (addValue == SPECIAL_VALUE) {
            TotalSumValues totalSumValuesWithAceValue = addValueToAllElement(totalSumValues, SPECIAL_VALUE);
            TotalSumValues totalSumValuesWithAnotherAceValue = addValueToAllElement(totalSumValues, ANOTHER_SPECIAL_VALUE);
            totalSumValues = totalSumValuesWithAceValue.merge(totalSumValuesWithAnotherAceValue);

        }
        if (addValue != Denomination.ACE_VALUE) {
            totalSumValues = addValueToAllElement(totalSumValues, addValue);
        }
        return totalSumValues;
    }

    private TotalSumValues addValueToAllElement(TotalSumValues totalSumValues, Integer addValue) {
        return new TotalSumValues(totalSumValues.values.stream()
                .map(value -> value + addValue)
                .collect(Collectors.toUnmodifiableList())
        );
    }

    private TotalSumValues merge(TotalSumValues toMergeTotalSumValues) {
        values.addAll(toMergeTotalSumValues.values);
        return new TotalSumValues(values);
    }

    public Integer getLessThanLimitMaxValue() {
        return this.values.stream()
                .filter(value -> value < LIMIT_VALUE)
                .max(Integer::compare)
                .orElse(LIMIT_VALUE);
    }

    public int getMaxValue() {
        return this.values.stream()
                .max(Integer::compare)
                .orElse(LIMIT_VALUE);
    }

    public List<Integer> getValues() {
        return values;
    }
}
