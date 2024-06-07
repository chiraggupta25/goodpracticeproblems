// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import java.util.*;

//float is 32 bit whereas decimal is 64 bit. 
class StringToDecimal {
    public static void main(String[] args) {
        System.out.println("Try programiz.pro");
        StringToDecimal stringToDecimal = new StringToDecimal();
        System.out.printf("%f %n",stringToDecimal.parseStringToDecimal(".54689"));
    }
    
    double parseStringToDecimal(String s){
        boolean isInvalidDecimal = false;
        Set<Character> whiteListSet = new HashSet<>();
        //adding in a for loop doesn't match the character. Will need to check in the future why that's the case
        whiteListSet.add('1');
        whiteListSet.add('2');
        whiteListSet.add('3');
        whiteListSet.add('4');
        whiteListSet.add('5');
        whiteListSet.add('6');
        whiteListSet.add('7');
        whiteListSet.add('8');
        whiteListSet.add('9');
        whiteListSet.add('0');
        whiteListSet.add('-');
        whiteListSet.add('.');
        char[] inputArray = s.toCharArray();
        int decimalCount = 0;
        int decimalIndex=-1;
        boolean isNegative = false;
        for(int i = 0; i< s.length(); i++){
            if(!whiteListSet.contains(inputArray[i])){
                isInvalidDecimal = true;
                break;
            }
            if(Character.compare(inputArray[i], '-') == 0){
                isNegative = true;
                if(i > 0){
                    isInvalidDecimal = true;
                    break;
                }
            }
           
            if(Character.compare(inputArray[i], '.') == 0){
                decimalIndex = i;
                decimalCount++;
                if(decimalCount > 1){
                    isInvalidDecimal = true;
                    break;
                }
            }
        }
        if(isInvalidDecimal){
            return 0.0;
        }
        char[] firstPartArray = null;
        char[] secondPartArray = null;
        if(decimalIndex > -1){
            if(isNegative){
                firstPartArray = s.substring(1,decimalIndex).toCharArray();
            }else{
                firstPartArray = s.substring(0,decimalIndex).toCharArray();
            }
            secondPartArray = s.substring(decimalIndex+1, s.length()).toCharArray();
        }
        else{
            firstPartArray = s.toCharArray();
        }
        double beforeDecimalValue = convertCharArrayToDecimal(firstPartArray,false);
        
        double afterDecimalValue = 0.0f;
        if(decimalIndex > -1){
            afterDecimalValue = convertCharArrayToDecimal(secondPartArray,true);
        }
        beforeDecimalValue += afterDecimalValue;
        if(isNegative){
            beforeDecimalValue *= (-1);
        }
        
        return beforeDecimalValue;
    }
    
    double convertCharArrayToDecimal(char[] value, boolean isDecimal){
        double output = 0.0f;
        int multiplier = 1;
        int multiplierFactor = 10;

        for(int i = value.length-1; i >=0; i--){
            output += Character.getNumericValue(value[i]) * multiplier;
            multiplier = multiplier*multiplierFactor;
        }
        
        if(isDecimal){
            float multi = 1f;
            float multiplierF = .10f;
             for(int i =0; i < value.length; i++){
                multi = multi *multiplierF;
            }
            output = output*multi;
            
        }
        return output;
    }
    

    
}