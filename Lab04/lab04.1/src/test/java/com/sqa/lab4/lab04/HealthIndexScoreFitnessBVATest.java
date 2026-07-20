package com.sqa.lab4.lab04;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * CP353201 Software Quality Assurance (1/2569)
 * Lab#4 - Boundary value analysis testing
 *
 * Boundary value test cases for HealthIndexScore's total score
 * and fitness level, covering the boundaries of:
 *  - VO2 Max score category (highest defined bucket: 51-60 vs >60)
 *  - Resting Heart Rate score category (40-60 vs 85-220)
 *  - Heart Rate Recovery score category (highest defined bucket: >=25)
 *
 * Nominal (control) values: vo2Max = 45, RHR = 70, HRR = 15
 */
public class HealthIndexScoreFitnessBVATest {

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

    // ---------- vo2Max boundaries (min side of valid domain) ----------

    @Test
    @DisplayName("TC002: vo2Max=0 (min) -> Score=6, Standard")
    void tc002_vo2Max_min() {
        HealthIndexScore h = new HealthIndexScore(0, NOM_RHR, NOM_HRR);
        assertEquals(6, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    @Test
    @DisplayName("TC003: vo2Max=1 (min+1) -> Score=6, Standard")
    void tc003_vo2Max_minPlus1() {
        HealthIndexScore h = new HealthIndexScore(1, NOM_RHR, NOM_HRR);
        assertEquals(6, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    // ---------- vo2Max boundaries (top of highest defined category) ----------

    @Test
    @DisplayName("TC004: vo2Max=60 (max-) -> Score=10, Standard")
    void tc004_vo2Max_maxMinus() {
        HealthIndexScore h = new HealthIndexScore(60, NOM_RHR, NOM_HRR);
        assertEquals(10, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    @Test
    @DisplayName("TC005: vo2Max=61 (max) -> Score=11, Standard")
    void tc005_vo2Max_max() {
        HealthIndexScore h = new HealthIndexScore(61, NOM_RHR, NOM_HRR);
        assertEquals(11, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    // ---------- restingHeartRate boundaries (min side) ----------

    @Test
    @DisplayName("TC006: RHR=40 (min) -> Score=11, Standard")
    void tc006_rhr_min() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, 40, NOM_HRR);
        assertEquals(11, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    @Test
    @DisplayName("TC007: RHR=41 (min+1) -> Score=11, Standard")
    void tc007_rhr_minPlus1() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, 41, NOM_HRR);
        assertEquals(11, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    // ---------- restingHeartRate boundaries (max side) ----------

    @Test
    @DisplayName("TC008: RHR=219 (max-1) -> Score=7, Standard")
    void tc008_rhr_maxMinus1() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, 219, NOM_HRR);
        assertEquals(7, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    @Test
    @DisplayName("TC009: RHR=220 (max) -> Score=7, Standard")
    void tc009_rhr_max() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, 220, NOM_HRR);
        assertEquals(7, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    // ---------- heartRateRecovery boundaries (min side) ----------

    @Test
    @DisplayName("TC010: HRR=0 (min) -> Score=7, Standard")
    void tc010_hrr_min() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, NOM_RHR, 0);
        assertEquals(7, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    @Test
    @DisplayName("TC011: HRR=1 (min+1) -> Score=7, Standard")
    void tc011_hrr_minPlus1() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, NOM_RHR, 1);
        assertEquals(7, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    // ---------- heartRateRecovery boundaries (top of highest defined category) ----------

    @Test
    @DisplayName("TC012: HRR=29 (max-) -> Score=11, Standard")
    void tc012_hrr_maxMinus() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, NOM_RHR, 29);
        assertEquals(11, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }

    @Test
    @DisplayName("TC013: HRR=30 (max) -> Score=11, Standard")
    void tc013_hrr_max() {
        HealthIndexScore h = new HealthIndexScore(NOM_VO2, NOM_RHR, 30);
        assertEquals(11, h.getTotalScore());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, h.getFitnessLevel());
    }
}