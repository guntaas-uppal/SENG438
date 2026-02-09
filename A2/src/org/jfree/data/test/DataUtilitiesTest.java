package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.junit.jupiter.api.Test;

public class DataUtilitiesTest {

    // ---------------- calculateColumnTotal(Values2D, int) ----------------
    @Test
    void calculateColumnTotal_ValidData_ShouldReturnCorrectSum() {
        Values2D values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(7.5);
        when(values.getValue(1, 0)).thenReturn(2.5);

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(10.0, result, 1e-9);
    }

    @Test
    void calculateColumnTotal_InvalidColumnIndex_ShouldReturnZero() {
        Values2D values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(7.5);
        when(values.getValue(1, 0)).thenReturn(2.5);

        double result = DataUtilities.calculateColumnTotal(values, 99);
        assertEquals(0.0, result, 1e-9);
    }

    @Test
    void calculateColumnTotal_NullData_ShouldThrowException() {
        assertThrows(InvalidParameterException.class, () -> {
            DataUtilities.calculateColumnTotal(null, 0);
        });
    }

    // ---------------- calculateRowTotal(Values2D, int) ----------------
    @Test
    void calculateRowTotal_ValidData_ShouldReturnCorrectSum() {
        Values2D values = mock(Values2D.class);
        when(values.getColumnCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(1.0);
        when(values.getValue(0, 1)).thenReturn(2.0);
        when(values.getValue(0, 2)).thenReturn(3.0);

        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(6.0, result, 1e-9);
    }

    @Test
    void calculateRowTotal_InvalidRowIndex_ShouldReturnZero() {
        Values2D values = mock(Values2D.class);
        when(values.getColumnCount()).thenReturn(1);
        when(values.getValue(0, 0)).thenReturn(5.0);

        double result = DataUtilities.calculateRowTotal(values, 99);
        assertEquals(0.0, result, 1e-9);
    }

    @Test
    void calculateRowTotal_NullData_ShouldThrowException() {
        assertThrows(InvalidParameterException.class, () -> {
            DataUtilities.calculateRowTotal(null, 0);
        });
    }

    // ---------------- createNumberArray(double[]) ----------------
    @Test
    void createNumberArray_NormalArray_ShouldConvertValues() {
        double[] input = {1.0, 2.5};
        Number[] result = DataUtilities.createNumberArray(input);

        assertEquals(2, result.length);
        assertEquals(1.0, result[0].doubleValue(), 1e-9);
        assertEquals(2.5, result[1].doubleValue(), 1e-9);
    }

    @Test
    void createNumberArray_EmptyArray_ShouldReturnEmptyArray() {
        double[] input = {};
        Number[] result = DataUtilities.createNumberArray(input);
        assertEquals(0, result.length);
    }

    @Test
    void createNumberArray_NullArray_ShouldThrowException() {
        assertThrows(InvalidParameterException.class, () -> {
            DataUtilities.createNumberArray(null);
        });
    }

    // ---------------- createNumberArray2D(double[][]) ----------------
    @Test
    void createNumberArray2D_NormalArray_ShouldConvertValues() {
        double[][] input = {{1, 2}, {3, 4}};
        Number[][] result = DataUtilities.createNumberArray2D(input);

        assertEquals(2, result.length);
        assertEquals(2, result[0].length);
        assertEquals(1.0, result[0][0].doubleValue(), 1e-9);
        assertEquals(4.0, result[1][1].doubleValue(), 1e-9);
    }

    @Test
    void createNumberArray2D_EmptyArray_ShouldReturnEmptyArray() {
        double[][] input = {};
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertEquals(0, result.length);
    }

    @Test
    void createNumberArray2D_NullArray_ShouldThrowException() {
        assertThrows(InvalidParameterException.class, () -> {
            DataUtilities.createNumberArray2D(null);
        });
    }

    // ---------------- getCumulativePercentages(KeyedValues) ----------------
    @Test
    void getCumulativePercentages_MultipleValues_ShouldReturnCorrectPercentages() {
        KeyedValues kv = mock(KeyedValues.class);

        when(kv.getItemCount()).thenReturn(3);
        when(kv.getKey(0)).thenReturn(0);
        when(kv.getKey(1)).thenReturn(1);
        when(kv.getKey(2)).thenReturn(2);

        when(kv.getValue(0)).thenReturn(5);
        when(kv.getValue(1)).thenReturn(9);
        when(kv.getValue(2)).thenReturn(2);

        KeyedValues result = DataUtilities.getCumulativePercentages(kv);

        assertEquals(5.0 / 16.0, result.getValue(0).doubleValue(), 1e-9);
        assertEquals(14.0 / 16.0, result.getValue(1).doubleValue(), 1e-9);
        assertEquals(1.0, result.getValue(2).doubleValue(), 1e-9);
    }

    @Test
    void getCumulativePercentages_SingleValue_ShouldReturnOne() {
        KeyedValues kv = mock(KeyedValues.class);

        when(kv.getItemCount()).thenReturn(1);
        when(kv.getKey(0)).thenReturn("X");
        when(kv.getValue(0)).thenReturn(4);

        KeyedValues result = DataUtilities.getCumulativePercentages(kv);

        assertEquals(1.0, result.getValue(0).doubleValue(), 1e-9);
    }

    @Test
    void getCumulativePercentages_ZeroIncluded_ShouldHandleCorrectly() {
        KeyedValues kv = mock(KeyedValues.class);

        when(kv.getItemCount()).thenReturn(2);
        when(kv.getKey(0)).thenReturn("A");
        when(kv.getKey(1)).thenReturn("B");

        when(kv.getValue(0)).thenReturn(0);
        when(kv.getValue(1)).thenReturn(10);

        KeyedValues result = DataUtilities.getCumulativePercentages(kv);

        assertEquals(0.0, result.getValue(0).doubleValue(), 1e-9);
        assertEquals(1.0, result.getValue(1).doubleValue(), 1e-9);
    }

    @Test
    void getCumulativePercentages_NullData_ShouldThrowException() {
        assertThrows(InvalidParameterException.class, () -> {
            DataUtilities.getCumulativePercentages(null);
        });
    }
}
