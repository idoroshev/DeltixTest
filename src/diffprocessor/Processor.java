package diffprocessor;

/**
 * Created by VavilauA on 6/19/2015.
 */
public class Processor {
    public Processor(long limit) {

    }

    public void doProcess(SortedLimitedList<Double> mustBeEqualTo, SortedLimitedList<Double> expectedOutput) {
        SortedLimitedList.Entry<Double> currentEntry = mustBeEqualTo.first;
        SortedLimitedList.Entry<Double> expectedEntry = expectedOutput.first;

        while (currentEntry != null && expectedEntry != null) {
            if (currentEntry.getValue().equals(expectedEntry.getValue())) {
                currentEntry = currentEntry.getNext();
                expectedEntry = expectedEntry.getNext();
            }
            else if (currentEntry.getValue() > expectedEntry.getValue()) {
                expectedEntry = expectedEntry.getNext();
            }
            else {
                SortedLimitedList.Entry<Double> temp = currentEntry.getNext();
                mustBeEqualTo.remove(currentEntry);
                currentEntry = temp;
            }

        }

        while (currentEntry != null) {
            SortedLimitedList.Entry<Double> temp = currentEntry.getNext();
            mustBeEqualTo.remove(currentEntry);
            currentEntry = temp;
        }

        currentEntry = mustBeEqualTo.first;
        expectedEntry = expectedOutput.first;

        if (currentEntry == null || currentEntry.getValue() > expectedEntry.getValue()) {
            if (expectedEntry != null) {
                mustBeEqualTo.addFirst(expectedEntry.getValue());
                currentEntry = mustBeEqualTo.first;
            }
        }

        while (expectedEntry != null && expectedEntry.getNext() != null) {
            SortedLimitedList.Entry<Double> currentNext = currentEntry.getNext();
            SortedLimitedList.Entry<Double> expectedNext = expectedEntry.getNext();
            if (currentNext == null || currentNext.getValue() > expectedNext.getValue())
                mustBeEqualTo.addAfter(currentEntry, expectedNext.getValue());

            currentEntry = currentEntry.getNext();
            expectedEntry = expectedEntry.getNext();
        }

        // 0. Processor will be created once and then will be used billion times.
        // 1. Use methods: AddFirst, AddLast, AddBefore, AddAfter, Remove to modify list.
        // 2. Do not change expectedOutput list.
        // 3. At any time number of elements in list could not exceed the "Limit".
        // 4. "Limit" will be passed into Processor's constructor. All "mustBeEqualTo" and "expectedOutput" lists will have the same "Limit" value.
        // 5. At any time list elements must be in non-descending order.
        // 6. Implementation must perform minimal possible number of actions (AddFirst, AddLast, AddBefore, AddAfter, Remove).
        // 7. Implementation must be fast and do not allocate excess memory.
    }
}
