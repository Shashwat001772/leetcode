class Solution {
    public int smallestNumber(int n, int t) {
        int currentNumber = n;
        while (true) {
            int product = getDigitProduct(currentNumber);
            if (product % t == 0) {
                return currentNumber;
            }
            currentNumber++;
        }
    }
    private int getDigitProduct(int num) {
        if (num == 0) {
            return 0;
        }        
        int product = 1;
        while (num > 0) {
            int digit = num % 10;   
            product *= digit;       
            num /= 10;              
        }
        return product;
    }
}