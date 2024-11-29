package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IncrementalGeneratorTest {

    private IncrementalGenerator toTest;

    @BeforeEach
    void setUp() {
        toTest = new IncrementalGenerator();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void generateId_calledTwice_idIsIncremented() {
        // Act
        int id1 = toTest.generateId();
        int id2 = toTest.generateId();
        // Assert
        assertThat(id1).isEqualTo(0);
        assertThat(id2).isEqualTo(1);
    }


}