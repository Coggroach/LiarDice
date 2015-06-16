package com.coggroach.dice;

public class DiceList
{
	public static final int QUINTUPLE = 1;
	public static final int FLUSH = 10;
	public static final int FULLHOUSE = 20;
	public static final int QUADRIPLE = 25;
	public static final int DOUBLE_PAIR = 40;
	public static final int TRIPLE = 250;
	public static final int PAIR = 1250;
	public static final int HIGH = 3125;
	private static final int DENOMINATOR = 7776;
	
	private List<Dice> dice;
	
	public DiceList()
	{
		this.dice = new ArrayList<Dice>();
	}
	
	public DiceList(int i)
	{
		this.dice = new ArrayList<Dice>(i);
	}
	
	public void save(DiceList list)
	{
		this.dice.addAll(list.dice);
	}
	
	public void removeAll()
	{
		this.dice.removeAll();
	}
	
	public void rollAll()
	{
		for(Dice d : dice)
		{
			d.roll();
		}
	}
	
	public void roll(int i)
	{
		this.dice.get(i).roll();
	}
	
	public int getLength()
	{
		return this.dice.size();
	}
	
	public int compare()
	{
		return getLogicResult(getRollResults(this));
	}
	
	private static Map<String, Integer> getRollResults(DiceList list)
	{
	    int[] freqTable = new int[] {0, 0, 0, 0, 0, 0};
	    int maxValue = 0;
	    
	    for(int i = 0; i < list.getLength(); i++)
	    {
	        freqTable[list.dice[i]-1]++;
	        if(maxValue < list.dice[i])
	        {
	            maxValue = list.dice[i];
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
	    map.put("NonZeroCount", nonZeroCount);
	    map.put("ZeroGap", zeroGap);
		return map;
	}
	
	private static int getLogicResult(Map<String, Integer> table)
	{
		if(table.get("ZeroGap") == 0 && table.get("MaxFreqCount") == 5)
			return FLUSH;
		if(table.get("NonZeroCount") == 2 && table.get("MaxFreq") == 3)
			return FULLHOUSE;
		if(table.get("MaxFreq") == 2 && table.get("MaxFreqCount"))
			return DOUBLE_PAIR;
		if(table.get("MaxFreq") >= 2)
		{
			switch(table.get("MaxFreq"))
			{
				case 2:
					return PAIR;
				case 3:
					return TRIPLE;
				case 4:
					return QUADRIPLE;
				case 5:
					return QUINTUPLE;
			}
		}
		return HIGH;
	}
}
