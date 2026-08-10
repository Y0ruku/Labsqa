package com.sqa.lab6_1;

/**
 * CP353201 Software Quality Assurance (1/2569)
 * Lab#6 – Decision Table
 * Instructor: Asst.Prof. Chitsutha Soomlek
 * 
 * RootResult Class
 * Stores the results of roots calculated from a quadratic equation
 * Supports both real numbers and complex numbers
 * format: root = realPart + (imaginaryPart)i
 */
public class RootResult {
    
    private final double root1Real;
    private final double root1Imaginary;
    private final double root2Real;
    private final double root2Imaginary;
    
    public RootResult(double root1Real, double root1Imaginary, 
                       double root2Real, double root2Imaginary) {
        this.root1Real = root1Real;
        this.root1Imaginary = root1Imaginary;
        this.root2Real = root2Real;
        this.root2Imaginary = root2Imaginary;
    }
    
    public double getRoot1Real() {
        return root1Real;
    }
    
    public double getRoot1Imaginary() {
        return root1Imaginary;
    }
    
    public double getRoot2Real() {
        return root2Real;
    }
    
    public double getRoot2Imaginary() {
        return root2Imaginary;
    }
    
    /**
     * @return true if it is a complex number (imaginary part != 0)
     */
    public boolean isComplex() {
        return root1Imaginary != 0 || root2Imaginary != 0;
    }
    
    @Override
    public String toString() {
        String r1 = formatRoot(root1Real, root1Imaginary);
        String r2 = formatRoot(root2Real, root2Imaginary);
        return "Root1 = " + r1 + ", Root2 = " + r2;
    }
    
    /**
     * @param real = real number
     * @param imaginary = imaginary number
     * @return String = complex number
     */
    private String formatRoot(double real, double imaginary) {
        if (imaginary == 0) {
            return String.format("%.4f", real);
        } else if (imaginary > 0) {
            return String.format("%.4f + %.4fi", real, imaginary);
        } else {
            return String.format("%.4f - %.4fi", real, Math.abs(imaginary));
        }
    }
}