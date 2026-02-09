package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

public class RangeTest {

    // ---------------- contains(double) ----------------
    @Test
    void contains_ValueBelowLowerBound_ShouldReturnFalse() {
        Range r = new Range(1, 5);
        assertFalse(r.contains(0.9));
    }

    @Test
    void contains_ValueEqualLowerBound_ShouldReturnTrue() {
        Range r = new Range(1, 5);
        assertTrue(r.contains(1.0));
    }

    @Test
    void contains_ValueWithinRange_ShouldReturnTrue() {
        Range r = new Range(1, 5);
        assertTrue(r.contains(3.0));
    }

    @Test
    void contains_ValueEqualUpperBound_ShouldReturnTrue() {
        Range r = new Range(1, 5);
        assertTrue(r.contains(5.0));
    }

    @Test
    void contains_ValueAboveUpperBound_ShouldReturnFalse() {
        Range r = new Range(1, 5);
        assertFalse(r.contains(5.1));
    }

    // ---------------- constrain(double) ----------------
    @Test
    void constrain_ValueBelowLowerBound_ShouldReturnLowerBound() {
        Range r = new Range(1, 5);
        assertEquals(1.0, r.constrain(0.0), 1e-9);
    }

    @Test
    void constrain_ValueWithinRange_ShouldReturnSameValue() {
        Range r = new Range(1, 5);
        assertEquals(3.0, r.constrain(3.0), 1e-9);
    }

    @Test
    void constrain_ValueAboveUpperBound_ShouldReturnUpperBound() {
        Range r = new Range(1, 5);
        assertEquals(5.0, r.constrain(10.0), 1e-9);
    }

    // ---------------- combine(Range, Range) ----------------
    @Test
    void combine_FirstRangeNull_ShouldReturnSecondRange() {
        Range r2 = new Range(1, 3);
        Range result = Range.combine(null, r2);
        assertEquals(r2, result);
    }

    @Test
    void combine_SecondRangeNull_ShouldReturnFirstRange() {
        Range r1 = new Range(1, 3);
        Range result = Range.combine(r1, null);
        assertEquals(r1, result);
    }

    @Test
    void combine_BothRangesNull_ShouldReturnNull() {
        assertNull(Range.combine(null, null));
    }

    @Test
    void combine_OverlappingRanges_ShouldReturnMergedRange() {
        Range r1 = new Range(1, 5);
        Range r2 = new Range(3, 7);
        Range result = Range.combine(r1, r2);
        assertEquals(1.0, result.getLowerBound(), 1e-9);
        assertEquals(7.0, result.getUpperBound(), 1e-9);
    }

    @Test
    void combine_DisjointRanges_ShouldReturnSpanningRange() {
        Range r1 = new Range(1, 2);
        Range r2 = new Range(5, 6);
        Range result = Range.combine(r1, r2);
        assertEquals(1.0, result.getLowerBound(), 1e-9);
        assertEquals(6.0, result.getUpperBound(), 1e-9);
    }

    // ---------------- expandToInclude(Range, double) ----------------
    @Test
    void expandToInclude_NullRange_ShouldCreateSingleValueRange() {
        Range result = Range.expandToInclude(null, 5.0);
        assertEquals(5.0, result.getLowerBound(), 1e-9);
        assertEquals(5.0, result.getUpperBound(), 1e-9);
    }

    @Test
    void expandToInclude_ValueWithinRange_ShouldReturnOriginalRange() {
        Range r = new Range(1, 5);
        Range result = Range.expandToInclude(r, 3);
        assertEquals(1.0, result.getLowerBound(), 1e-9);
        assertEquals(5.0, result.getUpperBound(), 1e-9);
    }

    @Test
    void expandToInclude_ValueBelowLowerBound_ShouldExpandLowerBound() {
        Range r = new Range(1, 5);
        Range result = Range.expandToInclude(r, -2);
        assertEquals(-2.0, result.getLowerBound(), 1e-9);
        assertEquals(5.0, result.getUpperBound(), 1e-9);
    }

    @Test
    void expandToInclude_ValueAboveUpperBound_ShouldExpandUpperBound() {
        Range r = new Range(1, 5);
        Range result = Range.expandToInclude(r, 10);
        assertEquals(1.0, result.getLowerBound(), 1e-9);
        assertEquals(10.0, result.getUpperBound(), 1e-9);
    }

    // ---------------- shift(Range, double, boolean) ----------------
    @Test
    void shift_NoZeroCrossing_ShouldShiftRangeNormally() {
        Range base = new Range(1, 5);
        Range result = Range.shift(base, 2, false);
        assertEquals(3.0, result.getLowerBound(), 1e-9);
        assertEquals(7.0, result.getUpperBound(), 1e-9);
    }

    @Test
    void shift_ZeroCrossingNotAllowed_ShouldClampToZero() {
        Range base = new Range(-1, 1);
        Range result = Range.shift(base, 1, false);
        assertEquals(0.0, result.getLowerBound(), 1e-9);
        assertEquals(2.0, result.getUpperBound(), 1e-9);
    }

    @Test
    void shift_ZeroCrossingNotAllowed_ShouldClampBothBounds() {
        Range base = new Range(1, 2);
        Range result = Range.shift(base, -5, false);
        assertEquals(0.0, result.getLowerBound(), 1e-9);
        assertEquals(0.0, result.getUpperBound(), 1e-9);
    }

    @Test
    void shift_ZeroCrossingAllowed_ShouldAllowCrossing() {
        Range base = new Range(1, 2);
        Range result = Range.shift(base, -5, true);
        assertEquals(-4.0, result.getLowerBound(), 1e-9);
        assertEquals(-3.0, result.getUpperBound(), 1e-9);
    }
}
