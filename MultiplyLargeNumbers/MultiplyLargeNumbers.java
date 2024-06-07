import java.util.*;

import java.util.Arrays;

class MultiplyLargeNumbers
{
  public static void main (String[]args)
  {
	if (args.length != 2)
	  {
		System.out.
		  println ("Usage: java MultiplyLargeNumbers <input1> <input2>");
		return;
	  }
	String input1Str = args[0];
	String input2Str = args[1];
	int[] input1 =
	  Arrays.stream (input1Str.split ("")).mapToInt (Integer::parseInt).
	  toArray ();
	int[] input2 =
	  Arrays.stream (input2Str.split ("")).mapToInt (Integer::parseInt).
	  toArray ();
	if (input1.length == 0 || input2.length == 0)
	  {
		System.out.println ("Invalid input");
		return;
	  }
	try
	{
	  int[] output = getMultiplication (input1, input2);
	  for (int i = 0; i < output.length; i++)
		{
		  System.out.print (output[i]);
		}
	} catch (ArrayIndexOutOfBoundsException e)
	{
	  System.out.println ("Array index out of bounds");
	} catch (Exception e)
	{
	  System.out.println ("An unexpected error occurred");
	  e.printStackTrace ();
	}
  }


  public static int[] getMultiplication (int[]input1, int[]input2)
  {
	if (input1 == null || input2 == null)
	  {
		throw new IllegalArgumentException ("Input arrays cannot be null");
	  }
	int[][] result =
	  new int[input2.length][input2.length + input1.length + 1];
	int resultRow = 0;
	int resultCol = 0;
	for (int input2Idx = input2.length - 1; input2Idx >= 0; input2Idx--)
	  {
		int carry = 0;
		resultCol = input2.length + input1.length - resultRow;
		for (int input1Idx = input1.length - 1; input1Idx >= 0; input1Idx--)
		  {
			int val = input1[input1Idx] * input2[input2Idx];
			carry += val / 10;
			result[resultRow][resultCol] = val % 10;
			resultCol--;
		  }
		result[resultRow][resultCol] = carry;
		resultRow++;
	  }
	int finResult[] = new int[input2.length + input1.length + 1];
	int finResultIdx = finResult.length - 1;
	int carry = 0;
	for (int col = input2.length + input1.length; col > 0; col--)
	  {
		for (int row = 0; row < result.length; row++)
		  {
			carry += result[row][col];
		  }
		finResult[finResultIdx] = carry % 10;
		carry = carry / 10;
		finResultIdx--;
	  }
	while (carry > 0)
	  {
		finResult[finResultIdx] = carry % 10;
		carry = carry / 10;
		finResultIdx--;
	  }
	return finResult;
  }
}s
