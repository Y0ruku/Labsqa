package com.sqa.lab6_1;

/**
 * CP353201 Software Quality Assurance (1/2569)
 * Lab#6 – Decision Table
 * Instructor: Asst.Prof. Chitsutha Soomlek
 * 
 * QuadraticEquation Class
 * (ax² + bx + c = 0)
 * Discriminant (D) = b² - 4ac
 * - if a = 0 -> NOT_QUADRATIC
 * - if D > 0 -> REAL_ROOTS
 * - if D = 0 -> EQUAL_ROOTSAC
 * - if D < 0 -> IMAGINARY_ROOTS
 */
public class QuadraticEquation {
    
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 100;
    
    public RootNature determineRootNature(int a, int b, int c) {
        validateInput(a, b, c);
        
        if (a == 0) {
            return RootNature.NOT_QUADRATIC;
        }
        
        double discriminant = calculateDiscriminant(a, b, c);
        
        if (discriminant > 0) {
            return RootNature.REAL_ROOTS;
        } else if (discriminant == 0) {
            return RootNature.EQUAL_ROOTS;
        } else {
            return RootNature.IMAGINARY_ROOTS;
        }
    }
    
    /**
     * @return RootResult object (contains 2 values)
     * @throws IllegalArgumentException when a, b or c are not in the range [0, 100]
     * @throws IllegalStateException when a = 0 (not a quadratic equation)
     */
    public RootResult calculateRoots(int a, int b, int c) {
        RootNature nature = determineRootNature(a, b, c);
        
        if (nature == RootNature.NOT_QUADRATIC) {
            throw new IllegalStateException(
                "Not a quadratic equation (a = 0)"
            );
        }
        
        double discriminant = calculateDiscriminant(a, b, c);
        double twoA = 2.0 * a;
        
        switch (nature) {
            case REAL_ROOTS:
                return calculateRealRoots(b, discriminant, twoA);
                
            case EQUAL_ROOTS:
                return calculateEqualRoots(b, twoA);
                
            case IMAGINARY_ROOTS:
                return calculateImaginaryRoots(b, discriminant, twoA);
                
            default:
                throw new IllegalStateException("Cannot calculate roots"); //just to be safe, should not happen
        }
    }
   
    private RootResult calculateRealRoots(int b, double discriminant, double twoA) {
        double sqrtD = Math.sqrt(discriminant);
        double root1 = (-b + sqrtD) / twoA;
        double root2 = (-b - sqrtD) / twoA;
        return new RootResult(root1, 0, root2, 0);
    }
    
    private RootResult calculateEqualRoots(int b, double twoA) {
        double root = -b / twoA;
        return new RootResult(root, 0, root, 0);
    }
    
    private RootResult calculateImaginaryRoots(int b, double discriminant, double twoA) {
        double realPart = -b / twoA;
        double imaginaryPart = Math.sqrt(-discriminant) / twoA;
        return new RootResult(realPart, imaginaryPart, realPart, -imaginaryPart);
    }
    
    private double calculateDiscriminant(int a, int b, int c) {
        double bSquare = (double) b * b;
        double fourAC = 4.0 * a * c;
        return bSquare - fourAC;
    }
    
    private void validateInput(int a, int b, int c) {
        validateRange(a, "a");
        validateRange(b, "b");
        validateRange(c, "c");
    }
    
    private void validateRange(int value, String paramName) {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IllegalArgumentException(
                "The value of " + paramName + " must be [" + MIN_VALUE + ", " + MAX_VALUE + "] " +
                ", but found: " + value
            );
        }
    }
}