package com.coggroach.liardice.dice;

import java.util.HashMap;
import java.util.Map;

public enum DiceLogic 
{
	Quintuple(1),
	Flush(10),
	FullHouse(20),
	Quadriple(25),
	DoublePair(40),
	Triple(250),
	Pair(1250),
	High(3125),
	Denominator(7776);
	
	private int probability;
	
	DiceLogic(int i)
	{
		this.probability = i;
	}
	
	public int getProbability()
	{
		return this.probability;
	}
	
	private static Map<String, Integer> getRollResults(DiceList list)
	{
	    int[] freqTable = new int[] {0, 0, 0, 0, 0, 0};
	    int maxValue = 0;
	    
	    for(int i = 0; i < list.size(); i++)
	    {
	        freqTable[list.getDiceValue(i) - 1]++;
	        if(maxValue < list.getDiceValue(i))
	        {
	            maxValue = list.getDiceValue(i);
	        }
	    }
	    
	    int maxFreq = 0;
	    int maxIndex = 0;
	    
	    for(int i = 0; i < freqTable.length; i++)
	    {
	        if(maxFreq < freqTable[i])
	        {
	            maxFreq = freqTable[i];
	            maxIndex = i;
	        }
	    }
	    
	    int maxFreqCount = 0;
	    int nonZeros = 0;
	    boolean isNonZero = false;
	    int zeroGap = 0;   
	    
	    for(int i = 0; i < freqTable.length; i++)
	    {
	        if(freqTable[i] == maxFreq)
	        {
	            maxFreqCount++;
	        }
	        if(isNonZero && freqTable[i] == 0)
	        {
	            zeroGap++;
	            isNonZero = false;
	        }        
	        if(freqTable[i] > 0)
	        {
	            nonZeros++;
	            isNonZero = true;
	        }        
	    }
	    
	    if(freqTable[5] == 0)
	    {
	        zeroGap--;
	    }
	    Map<String, Integer> map = new HashMap<String, Integer>();
	    map.put("MaxFreq", maxFreq);
	    map.put("MaxIndex", maxIndex);
	    map.put("MaxValue", maxValue);
	    map.put("MaxFreqCount", maxFreqCount);
	    map.put("NonZeroCount", nonZeros);
	    map.put("ZeroGap", zeroGap);
		return map;
	}
	
	private static DiceLogic getLogicResult(Map<String, Integer> table)
	{
		if(table.get("ZeroGap") == 0 && table.get("MaxFreqCount") == 5)
			return Flush;
		if(table.get("NonZeroCount") == 2 && table.get("MaxFreq") == 3)
			return FullHouse;
		if(table.get("MaxFreq") == 2 && table.get("MaxFreqCount") == 2)
			return DoublePair;
		if(table.get("MaxFreq") >= 2)
		{
			switch(table.get("MaxFreq"))
			{
				case 2:
					return Pair;
				case 3:
					return Triple;
				case 4:
					return Quadriple;
				case 5:
					return Quintuple;
			}
		}
		return High;
	}
	
	public static DiceLogic getLogicResult(DiceList list)
	{
		return getLogicResult(getRollResults(list));
	}
}
