package com.sqa.lab4;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class HealthIndexScoreRobustnessTest {

    // Baseline nominal values
    private static final double NOM_VO2 = 45.0;
    private static final int NOM_RHR = 70;
    private static final int NOM_HRR = 15;

    // ---------- TC001: Control case (all nominal) ----------

    @Test
    @DisplayName("TC001: vo2Max=45, RHR=70, HRR=15 -> Score=9, Standard")
    void tc001_allNominal() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, NOM_RHR, NOM_HRR);
        assertEquals(9, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    // ---------- vo2Max robustness (min- / min / min+) ----------

    @Test
    @DisplayName("TC002: vo2Max=-1 (min-) -> Invalid")
    void tc002_vo2Max_minMinus() {
        assertThrows(IllegalArgumentException.class,
                () -> new HealthIndexScore(-1, NOM_RHR, NOM_HRR));
    }

    @Test
    @DisplayName("TC003: vo2Max=0 (min) -> Score=6, Standard")
    void tc003_vo2Max_min() {
        HealthIndexScore h = new HealthIndexScore(0, NOM_RHR, NOM_HRR);
        assertEquals(6, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    @Test
    @DisplayName("TC004: vo2Max=1 (min+) -> Score=6, Standard")
    void tc004_vo2Max_minPlus() {
        HealthIndexScore h = new HealthIndexScore(1, NOM_RHR, NOM_HRR);
        assertEquals(6, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    // ---------- vo2Max robustness (max- / max / max+) ----------

    @Test
    @DisplayName("TC005: vo2Max=60 (max-) -> Score=10, Standard")
    void tc005_vo2Max_maxMinus() {
        HealthIndexScore h = new HealthIndexScore(60, NOM_RHR, NOM_HRR);
        assertEquals(10, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    @Test
    @DisplayName("TC006: vo2Max=61 (max) -> Score=11, Standard")
    void tc006_vo2Max_max() {
        HealthIndexScore h = new HealthIndexScore(61, NOM_RHR, NOM_HRR);
        assertEquals(11, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    @Test
    @DisplayName("TC007: vo2Max=62 (max+) -> Score=11, Standard")
    void tc007_vo2Max_maxPlus() {
        HealthIndexScore h = new HealthIndexScore(62, NOM_RHR, NOM_HRR);
        assertEquals(11, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    // ---------- restingHeartRate robustness (min- / min / min+) ----------

    @Test
    @DisplayName("TC008: RHR=39 (min-) -> Invalid")
    void tc008_rhr_minMinus() {
        assertThrows(IllegalArgumentException.class,
                () -> new HealthIndexScore(NOM_VO2, 39, NOM_HRR));
    }

    @Test
    @DisplayName("TC009: RHR=40 (min) -> Score=11, Standard")
    void tc009_rhr_min() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, 40, NOM_HRR);
        assertEquals(11, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    @Test
    @DisplayName("TC010: RHR=41 (min+) -> Score=11, Standard")
    void tc010_rhr_minPlus() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, 41, NOM_HRR);
        assertEquals(11, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    // ---------- restingHeartRate robustness (max- / max / max+) ----------

    @Test
    @DisplayName("TC011: RHR=219 (max-) -> Score=7, Standard")
    void tc011_rhr_maxMinus() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, 219, NOM_HRR);
        assertEquals(7, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    @Test
    @DisplayName("TC012: RHR=220 (max) -> Score=7, Standard")
    void tc012_rhr_max() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, 220, NOM_HRR);
        assertEquals(7, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    @Test
    @DisplayName("TC013: RHR=221 (max+) -> Invalid")
    void tc013_rhr_maxPlus() {
        assertThrows(IllegalArgumentException.class,
                () -> new HealthIndexScore(NOM_VO2, 221, NOM_HRR));
    }

    // ---------- heartRateRecovery robustness (min- / min / min+) ----------

    @Test
    @DisplayName("TC014: HRR=-1 (min-) -> Invalid")
    void tc014_hrr_minMinus() {
        assertThrows(IllegalArgumentException.class,
                () -> new HealthIndexScore(NOM_VO2, NOM_RHR, -1));
    }

    @Test
    @DisplayName("TC015: HRR=0 (min) -> Score=7, Standard")
    void tc015_hrr_min() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, NOM_RHR, 0);
        assertEquals(7, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    @Test
    @DisplayName("TC016: HRR=1 (min+) -> Score=7, Standard")
    void tc016_hrr_minPlus() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, NOM_RHR, 1);
        assertEquals(7, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    // ---------- heartRateRecovery robustness (max- / max / max+) ----------

    @Test
    @DisplayName("TC017: HRR=29 (max-) -> Score=11, Standard")
    void tc017_hrr_maxMinus() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, NOM_RHR, 29);
        assertEquals(11, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    @Test
    @DisplayName("TC018: HRR=30 (max) -> Score=11, Standard")
    void tc018_hrr_max() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, NOM_RHR, 30);
        assertEquals(11, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    @Test
    @DisplayName("TC019: HRR=31 (max+) -> Score=11, Standard")
    void tc019_hrr_maxPlus() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, NOM_RHR, 31);
        assertEquals(11, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }
}