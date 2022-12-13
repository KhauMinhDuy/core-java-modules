package com.khauminhduy.removeduplicates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveDuplicateFromStringUnitTest {

    private final static String STR1 = "racecar";
    private final static String STR2 = "J2ee programming";
    private final static String STR_EMPTY = "";

    private RemoveDuplicateFromString removeDuplicateFromString;

    @BeforeEach
    public void executedBeforeEach() {
        removeDuplicateFromString = new RemoveDuplicateFromString();
    }

    @Test
    public void whenUsingCharArray_DuplicatesShouldBeRemovedWithoutKeepingStringOrder() {
        String str1 = removeDuplicateFromString.removeDuplicatesUsingCharArray(STR1);
        String str2 = removeDuplicateFromString.removeDuplicatesUsingCharArray(STR2);
        String strEmpty = removeDuplicateFromString.removeDuplicatesUsingCharArray(STR_EMPTY);
        assertEquals("", strEmpty);
        assertEquals("ecar", str1);
        assertEquals("J2e poraming", str2);
    }

    @Test
    public void whenUsingLinkedHashSet_DuplicatesShouldBeRemovedAndItKeepStringOrder() {
        String str1 = removeDuplicateFromString.removeDuplicatesUsingLinkedHashSet(STR1);
        String str2 = removeDuplicateFromString.removeDuplicatesUsingLinkedHashSet(STR2);

        String strEmpty = removeDuplicateFromString.removeDuplicatesUsingLinkedHashSet(STR_EMPTY);
        assertEquals("", strEmpty);
        assertEquals("race", str1);
        assertEquals("J2e progamin", str2);
    }

    @Test
    public void whenUsingSorting_DuplicatesShouldBeRemovedWithoutKeepingStringOrder() {
        String str1 = removeDuplicateFromString.removeDuplicatesUsingSorting(STR1);
        String str2 = removeDuplicateFromString.removeDuplicatesUsingSorting(STR2);

        String strEmpty = removeDuplicateFromString.removeDuplicatesUsingSorting(STR_EMPTY);
        assertEquals("", strEmpty);
        assertEquals("acer", str1);
        assertEquals(" 2Jaegimnopr", str2);
    }

    @Test
    public void whenUsingHashSet_DuplicatesShouldBeRemovedWithoutKeepingStringOrder() {
        String str1 = removeDuplicateFromString.removeDuplicatesUsingHashSet(STR1);
        String str2 = removeDuplicateFromString.removeDuplicatesUsingHashSet(STR2);
        String strEmpty = removeDuplicateFromString.removeDuplicatesUsingHashSet(STR_EMPTY);
        assertEquals("", strEmpty);
        assertEquals("arce", str1);
        assertEquals(" pa2regiJmno", str2);
    }

    @Test
    public void whenUsingIndexOf_DuplicatesShouldBeRemovedWithoutKeepingStringOrder() {
        String str1 = removeDuplicateFromString.removeDuplicatesUsingIndexOf(STR1);
        String str2 = removeDuplicateFromString.removeDuplicatesUsingIndexOf(STR2);
        String strEmpty = removeDuplicateFromString.removeDuplicatesUsingIndexOf(STR_EMPTY);
        assertEquals("", strEmpty);
        assertEquals("ecar", str1);
        assertEquals("J2e poraming", str2);
    }

    @Test
    public void whenUsingJava8_DuplicatesShouldBeRemovedAndItKeepStringOrder() {
        String str1 = removeDuplicateFromString.removeDuplicatesUsingDistinct(STR1);
        String str2 = removeDuplicateFromString.removeDuplicatesUsingDistinct(STR2);
        String strEmpty = removeDuplicateFromString.removeDuplicatesUsingDistinct(STR_EMPTY);
        assertEquals("", strEmpty);
        assertEquals("race", str1);
        assertEquals("J2e progamin", str2);
    }

}
