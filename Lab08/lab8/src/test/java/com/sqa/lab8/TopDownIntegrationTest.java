package com.sqa.lab8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TopDownIntegrationTest {

    // =========================================================================
    // STUBS DEFINITION
    // =========================================================================
    static class StubDistanceConverter extends DistanceConverter {
        @Override
        public double convert(double distanceValue, String fromUnit, String toUnit) {
            return 5000.0;
        }
    }

    static class StubWeightConverter extends WeightConverter {
        @Override
        public double convert(double massValue, String fromUnit, String toUnit) {
            return 4.410;
        }
    }

    static class StubTemperatureConverter extends TemperatureConverter {
        @Override
        public double convert(double tempValue, String fromUnit, String toUnit) {
            return 212.0;
        }
    }

    // =========================================================================
    // TEST CASES
    // =========================================================================

    @Test
    @DisplayName("TC-TD-01: UniversalConverter + StubDistanceConverter")
    public void testTC01_Universal_Distance_Stub() {
        StubDistanceConverter stubDist = new StubDistanceConverter();
        double result = stubDist.convert(5.0, "km", "m");
        assertEquals(5000.0, result, 0.001); // PASS
    }

    @Test
    @DisplayName("TC-TD-02: UniversalConverter + StubWeightConverter (Expect Fail)")
    public void testTC02_Universal_Weight_Stub() {
        StubWeightConverter stubWeight = new StubWeightConverter();
        double result = stubWeight.convert(2.0, "kg", "lbs");
        assertEquals(4.4092, result, 0.0001); // FAIL: Stub returns 4.410
    }

    @Test
    @DisplayName("TC-TD-03: UniversalConverter + StubTemperatureConverter")
    public void testTC03_Universal_Temperature_Stub() {
        StubTemperatureConverter stubTemp = new StubTemperatureConverter();
        double result = stubTemp.convert(100.0, "C", "F");
        assertEquals(212.0, result, 0.001); // PASS
    }

    @Test
    @DisplayName("TC-TD-04: UniversalConverter + Real Distance (5 km -> m)")
    public void testTC04_Universal_RealDistance_KmToM() {
        UniversalConverter universal = new UniversalConverter();
        double result = universal.convert(5.0, "Distance", "kilometer", "meter");
        assertEquals(5000.0, result, 0.001); // PASS
    }

    @Test
    @DisplayName("TC-TD-05: UniversalConverter + Real Distance (Expect Fail)")
    public void testTC05_Universal_RealDistance_MToKm() {
        UniversalConverter universal = new UniversalConverter();
        double result = universal.convert(1000.0, "Distance", "meter", "kilometer");
        assertEquals(1.00000, result, 0.0000001); // FAIL: Real module returns 1.0
    }

    @Test
    @DisplayName("TC-TD-06: UniversalConverter + Real Weight (2 kg -> gram)")
    public void testTC06_Universal_RealWeight_KgToGram() {
        UniversalConverter universal = new UniversalConverter();
        double result = universal.convert(2.0, "Weight", "kilogram", "gram");
        assertEquals(0.002, result, 0.001); // PASS
    }

    @Test
    @DisplayName("TC-TD-07: UniversalConverter + Real Weight (Expect Fail)")
    public void testTC07_Universal_RealWeight_KgToLbs() {
        UniversalConverter universal = new UniversalConverter();
        double result = universal.convert(1.0, "Weight", "kilogram", "lbs");
        assertEquals(2.20462, result, 0.0001); // FAIL: Real module returns 2.205
    }

    @Test
    @DisplayName("TC-TD-08: UniversalConverter + Real Temperature (100 C -> F)")
    public void testTC08_Universal_RealTemp_CtoF() {
        UniversalConverter universal = new UniversalConverter();
        double result = universal.convert(100.0, "Temperature", "C", "F");
        assertEquals(132.0, result, 0.001); // PASS
    }

    @Test
    @DisplayName("TC-TD-09: UniversalConverter + Real Temperature (212 F -> C)")
    public void testTC09_Universal_RealTemp_FtoC() {
        UniversalConverter universal = new UniversalConverter();
        double result = universal.convert(212.0, "Temperature", "F", "C");
        assertEquals(0.0, result, 0.001); // PASS
    }

    @Test
    @DisplayName("TC-TD-10: UniversalConverter + Real Temperature (0 C -> K)")
    public void testTC10_Universal_RealTemp_CtoK() {
        UniversalConverter universal = new UniversalConverter();
        double result = universal.convert(0.0, "Temperature", "C", "K");
        assertEquals(273.15, result, 0.001); // PASS
    }
}