**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group \#:      |  7  |
| -------------- | --- |
| Student Names: | Guntaas Singh Uppal |
|                | Jindjeet Singh Cheema |
|                | Sukhansh Singh Nagi |
|                | Rizam Goyal |
|                | Tahil Goyal |

# 1 Introduction

The objective of this lab is to design and implement automated unit tests based strictly on **requirements specified in Javadoc documentation**, rather than on observed system behavior. This lab focuses on applying requirements-based testing principles using the **JUnit 5 (Jupiter)** framework and **Mockito** for mocking dependencies.

The system under test (SUT) for this lab is **JFreeChart v1.0**, a Java-based charting framework. For the purpose of this assignment, only a subset of the framework is tested, specifically classes in the `org.jfree.data` package. The tested classes were selected because they contain well-defined Javadoc specifications that can be used as test oracles.

Through this lab, the group gained experience in:
- Designing test cases using **black-box techniques** such as equivalence partitioning and boundary value analysis,
- Translating Javadoc specifications into executable unit tests,
- Using mocking to isolate units under test,
- Executing test suites against a version of the system containing known defects and analyzing failures


# 2 Detailed description of unit test strategy

The unit test strategy for this lab follows a **requirements-based, black-box testing approach**. Test cases were designed solely using the specifications provided in the Javadoc documentation, without reference to the internal implementation of the methods.

## 2.1 Classes Under Test

The following classes were selected for testing:

- **`org.jfree.data.DataUtilities`**  
  All **five** public static methods were tested.

- **`org.jfree.data.Range`**  
  **Five** out of fifteen public methods were selected for testing:
  - `contains(double value)`
  - `constrain(double value)`
  - `combine(Range range1, Range range2)`
  - `expandToInclude(Range range, double value)`
  - `shift(Range base, double delta, boolean allowZeroCrossing)`

These methods were chosen because they exhibit clear input domains, boundary conditions, and special-case behavior (e.g., null handling, zero-crossing).

## 2.2 Test Design Techniques

Two primary black-box test design techniques were applied:

### 2.2.1 Equivalence Partitioning

For each input parameter, the input domain was divided into **equivalence classes** such that all values in a class are expected to produce similar behavior. One representative test case was selected from each equivalence class.

Examples include:
- Valid vs. invalid indices for row/column-based methods,
- Values inside, outside, or equal to the bounds of a range,
- Null vs. non-null input objects.

### 2.2.2 Boundary Value Analysis

Boundary value analysis was applied wherever the Javadoc specification implied limits or transitions in behavior. This was especially relevant for:
- Range boundaries (`lower` and `upper`),
- Zero-crossing behavior in the `shift` method,
- Empty arrays and single-element data structures.

Robustness testing was incorporated through invalid and null-input cases (e.g., null data objects and out-of-range indices). Worst-case scenarios were also considered by including multi-value inputs, such as datasets with multiple elements for cumulative percentage calculations.

## 2.3 Use of Mocking

Several methods in `DataUtilities` accept interface types (`Values2D` and `KeyedValues`) as parameters. Because these interfaces do not provide concrete implementations, **Mockito** was used to create mock objects.

Mocking allowed the tests to:
- Control return values for methods such as `getValue()`, `getRowCount()`, and `getItemCount()`,
- Isolate the method under test from external dependencies,
- Focus strictly on verifying compliance with the Javadoc specifications.

The use of mocking improves test isolation but also introduces limitations, such as the risk of tests not reflecting real-world object behavior. These trade-offs are discussed further in Section 5.


# 3 Test Cases Developed

This section documents the unit test cases developed for this lab. Each test case corresponds to a **single JUnit 5 test method** and is derived directly from the equivalence partitions and boundary conditions defined in Section 2. The tables below describe what each test verifies, the inputs used, and the expected outcome based on the Javadoc specifications.

## 3.1 DataUtilities Test Cases

Five public static methods of `org.jfree.data.DataUtilities` were selected and tested based on the Javadoc requirements.

### Class: `DataUtilitiesTest`

### Method: `calculateColumnTotal(Values2D data, int column)`

| Test Case ID | Test Method Name | Input Description | Partition / Boundary Covered | Expected Result |
|-------------|-----------------|------------------|------------------------------|----------------|
| DU-CT-01 | `calculateColumnTotal_ValidData_ShouldReturnCorrectSum` | Valid `Values2D` with two rows and known values in column 0 | Valid data, valid column index | Sum of values in column |
| DU-CT-02 | `calculateColumnTotal_InvalidColumnIndex_ShouldReturnZero` | Valid `Values2D` with column index out of range | Invalid column index | `0.0` |
| DU-CT-03 | `calculateColumnTotal_NullData_ShouldThrowException` | `data = null` | Null input | `InvalidParameterException` |

### Method: `calculateRowTotal(Values2D data, int row)`

| Test Case ID | Test Method Name | Input Description | Partition / Boundary Covered | Expected Result |
|-------------|-----------------|------------------|------------------------------|----------------|
| DU-RT-01 | `calculateRowTotal_ValidData_ShouldReturnCorrectSum` | Valid `Values2D` with known values in row 0 | Valid data, valid row index | Sum of values in row |
| DU-RT-02 | `calculateRowTotal_InvalidRowIndex_ShouldReturnZero` | Valid `Values2D` with row index out of range | Invalid row index | `0.0` |
| DU-RT-03 | `calculateRowTotal_NullData_ShouldThrowException` | `data = null` | Null input | `InvalidParameterException` |

### Method: `createNumberArray(double[] data)`

| Test Case ID | Test Method Name | Input Description | Partition / Boundary Covered | Expected Result |
|-------------|-----------------|------------------|------------------------------|----------------|
| DU-NA-01 | `createNumberArray_NormalArray_ShouldConvertValues` | Non-empty array `{1.0, 2.5}` | Normal input array | `Number[]{1.0, 2.5}` |
| DU-NA-02 | `createNumberArray_EmptyArray_ShouldReturnEmptyArray` | Empty array `{}` | Empty array boundary | Empty `Number[]` |
| DU-NA-03 | `createNumberArray_NullArray_ShouldThrowException` | `data = null` | Null input | `InvalidParameterException` |

### Method: `createNumberArray2D(double[][] data)`

| Test Case ID | Test Method Name | Input Description | Partition / Boundary Covered | Expected Result |
|-------------|-----------------|------------------|------------------------------|----------------|
| DU-NA2D-01 | `createNumberArray2D_NormalArray_ShouldConvertValues` | 2D array `{{1,2},{3,4}}` | Normal 2D array | Equivalent `Number[][]` |
| DU-NA2D-02 | `createNumberArray2D_EmptyArray_ShouldReturnEmptyArray` | Empty 2D array `{}` | Empty array boundary | Empty `Number[][]` |
| DU-NA2D-03 | `createNumberArray2D_NullArray_ShouldThrowException` | `data = null` | Null input | `InvalidParameterException` |

### Method: `getCumulativePercentages(KeyedValues data)`

| Test Case ID | Test Method Name | Input Description | Partition / Boundary Covered | Expected Result |
|-------------|-----------------|------------------|------------------------------|----------------|
| DU-CP-01 | `getCumulativePercentages_MultipleValues_ShouldReturnCorrectPercentages` | Values `{5, 9, 2}` | Multiple positive values | `{0.3125, 0.875, 1.0}` |
| DU-CP-02 | `getCumulativePercentages_SingleValue_ShouldReturnOne` | Single value `{4}` | Single-element boundary | `{1.0}` |
| DU-CP-03 | `getCumulativePercentages_ZeroIncluded_ShouldHandleCorrectly` | Values `{0, 10}` | Zero included | `{0.0, 1.0}` |
| DU-CP-04 | `getCumulativePercentages_NullData_ShouldThrowException` | `data = null` | Null input | `InvalidParameterException` |

## 3.2 Range Test Cases

Five public methods of `org.jfree.data.Range` were selected based on clear Javadoc specifications and suitability for boundary value analysis.

### Class: `RangeTest`

### Method: `contains(double value)`

| Test Case ID | Test Method Name | Range | value | Partition / Boundary Covered | Expected Result |
|-------------|-----------------|-------|-------|------------------------------|----------------|
| R-C-01 | `contains_ValueBelowLowerBound_ShouldReturnFalse` | `[1,5]` | `0.9` | Below lower bound | `false` |
| R-C-02 | `contains_ValueEqualLowerBound_ShouldReturnTrue` | `[1,5]` | `1.0` | Lower boundary | `true` |
| R-C-03 | `contains_ValueWithinRange_ShouldReturnTrue` | `[1,5]` | `3.0` | Inside range | `true` |
| R-C-04 | `contains_ValueEqualUpperBound_ShouldReturnTrue` | `[1,5]` | `5.0` | Upper boundary | `true` |
| R-C-05 | `contains_ValueAboveUpperBound_ShouldReturnFalse` | `[1,5]` | `5.1` | Above upper bound | `false` |


### Method: `constrain(double value)`

| Test Case ID | Test Method Name | Range | value | Partition / Boundary Covered | Expected Result |
|-------------|-----------------|-------|-------|------------------------------|----------------|
| R-CN-01 | `constrain_ValueBelowLowerBound_ShouldReturnLowerBound` | `[1,5]` | `0` | Below lower bound | `1` |
| R-CN-02 | `constrain_ValueWithinRange_ShouldReturnSameValue` | `[1,5]` | `3` | Inside range | `3` |
| R-CN-03 | `constrain_ValueAboveUpperBound_ShouldReturnUpperBound` | `[1,5]` | `10` | Above upper bound | `5` |

### Method: `combine(Range range1, Range range2)`

| Test Case ID | Test Method Name | range1 | range2 | Partition Covered | Expected Result |
|-------------|-----------------|--------|--------|------------------|----------------|
| R-CB-01 | `combine_FirstRangeNull_ShouldReturnSecondRange` | `null` | `[1,3]` | First range null | `[1,3]` |
| R-CB-02 | `combine_SecondRangeNull_ShouldReturnFirstRange` | `[1,3]` | `null` | Second range null | `[1,3]` |
| R-CB-03 | `combine_BothRangesNull_ShouldReturnNull` | `null` | `null` | Both ranges null | `null` |
| R-CB-04 | `combine_OverlappingRanges_ShouldReturnMergedRange` | `[1,5]` | `[3,7]` | Overlapping ranges | `[1,7]` |
| R-CB-05 | `combine_DisjointRanges_ShouldReturnSpanningRange` | `[1,2]` | `[5,6]` | Disjoint ranges | `[1,6]` |

### Method: `expandToInclude(Range range, double value)`

| Test Case ID | Test Method Name | Range | value | Partition Covered | Expected Result |
|-------------|-----------------|-------|-------|------------------|----------------|
| R-EI-01 | `expandToInclude_NullRange_ShouldCreateSingleValueRange` | `null` | `5` | Null range | `[5,5]` |
| R-EI-02 | `expandToInclude_ValueWithinRange_ShouldReturnOriginalRange` | `[1,5]` | `3` | Value inside range | `[1,5]` |
| R-EI-03 | `expandToInclude_ValueBelowLowerBound_ShouldExpandLowerBound` | `[1,5]` | `-2` | Below lower bound | `[-2,5]` |
| R-EI-04 | `expandToInclude_ValueAboveUpperBound_ShouldExpandUpperBound` | `[1,5]` | `10` | Above upper bound | `[1,10]` |

### Method: `shift(Range base, double delta, boolean allowZeroCrossing)`

| Test Case ID | Test Method Name | Base Range | delta | allowZeroCrossing | Partition Covered | Expected Result |
|-------------|-----------------|------------|-------|------------------|------------------|----------------|
| R-S-01 | `shift_NoZeroCrossing_ShouldShiftRangeNormally` | `[1,5]` | `+2` | `false` | No zero crossing | `[3,7]` |
| R-S-02 | `shift_ZeroCrossingNotAllowed_ShouldClampToZero` | `[-1,1]` | `+1` | `false` | Zero crossing not allowed | `[0,2]` |
| R-S-03 | `shift_ZeroCrossingNotAllowed_ShouldClampBothBounds` | `[1,2]` | `-5` | `false` | Zero crossing not allowed | `[0,0]` |
| R-S-04 | `shift_ZeroCrossingAllowed_ShouldAllowCrossing` | `[1,2]` | `-5` | `true` | Zero crossing allowed | `[-4,-3]` |

All test cases listed in this section were implemented as JUnit 5 test methods and executed against JFreeChart v1.0, where some failures were expected due to intentionally introduced defects in the system under test.



# 4 How the team work/effort was divided and managed

To ensure the workload for this lab was divided fairly, the group organized the work around the **ten total methods** selected for testing: **five** from `org.jfree.data.DataUtilities` and **five** from `org.jfree.data.Range`.

Each group member was assigned responsibility for **two methods**, with one member taking on an additional method and coordination tasks to keep the overall effort balanced. In addition to implementing unit tests, all members participated in reviewing Javadoc specifications, validating test expectations, and discussing observed failures.

The initial division of work was as follows:

**`org.jfree.data.DataUtilities`**
- **Guntaas Singh Uppal**  
  - `calculateColumnTotal(Values2D, int)`  
  - `calculateRowTotal(Values2D, int)`
- **Jindjeet Singh Cheema**  
  - `createNumberArray(double[])`  
  - `createNumberArray2D(double[][])`
- **Sukhansh Singh Nagi**  
  - `getCumulativePercentages(KeyedValues)`  
  - Assisted with Mockito setup and mock configuration

**`org.jfree.data.Range`**
- **Rizam Goyal**  
  - `contains(double)`  
  - `constrain(double)`
- **Tahil Goyal**  
  - `combine(Range, Range)`  
  - `expandToInclude(Range, double)`  
  - `shift(Range, double, boolean)`

Although each member had primary ownership of specific methods, the group collaborated closely throughout the lab. Members regularly reviewed each other’s tests, discussed Javadoc interpretations, and helped diagnose failing test cases. After implementation, all members walked through the complete test suite together to ensure a shared understanding of the methods tested and the overall testing strategy. This approach ensured that the workload was balanced and that all members contributed equally to the final outcome.


# 5 Difficulties encountered, challenges overcome, and lessons learned

One of the main challenges encountered during this lab was interpreting the Javadoc specifications precisely and translating them into correct test expectations. In several cases, the documented behavior did not align with the actual behavior observed during test execution, especially for exception handling and boundary cases. This initially caused confusion, as some tests failed even though they appeared logically correct based on the implementation.

Another challenge involved testing methods that accept interface types, such as `Values2D` and `KeyedValues`. Setting up Mockito mocks for these interfaces required careful configuration of return values and method behavior. Small mistakes in mock setup often resulted in misleading failures, which took time to diagnose and correct.

Through this process, the group learned the importance of strictly following requirements rather than relying on observed behavior when designing tests. The lab reinforced the idea that failing tests do not necessarily indicate incorrect test code, but may instead reveal inconsistencies or defects in the system under test. Additionally, the team gained practical experience using mocking frameworks and learned how effective collaboration and peer review can significantly reduce debugging time.



# 6 Comments/feedback on the lab itself

Overall, this lab was a valuable exercise in understanding requirements-based testing and reinforced concepts that are highly relevant to real-world software testing. The emphasis on using Javadoc specifications as the sole test oracle helped clarify the distinction between testing expected behavior versus implementation behavior.

While the lab was challenging at times, especially when dealing with unexpected test failures, these challenges ultimately contributed to a deeper understanding of testing principles. One area that could be improved is providing a brief example of how to interpret ambiguous Javadoc requirements, as this would help reduce initial uncertainty for students new to requirements-based testing.

Despite this, the lab was well-structured and effectively encouraged critical thinking, collaboration, and practical experience with industry-standard testing tools such as JUnit and Mockito.
