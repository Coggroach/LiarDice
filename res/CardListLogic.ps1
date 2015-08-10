function Get-RollResults([int[]] $list)
{
    [int[]] $FreqTable = @(0, 0, 0, 0, 0, 0);
    [int] $MaxValue = 0;
    
    for([int] $i = 0; $i -lt $list.Length; $i++)
    {
        $FreqTable[$list[$i]-1]++;
        if($MaxValue -lt $list[$i])
        {
            $MaxValue = $list[$i];
        }
    }
    
    [int] $MaxFreq = 0;
    [int] $MaxIndex = 0;
    
    for([int] $i = 0; $i -lt $FreqTable.Length; $i++)
    {
        if($MaxFreq -lt $FreqTable[$i])
        {
            $MaxFreq = $FreqTable[$i];
            $MaxIndex = $i;
        }
    }
    
    [int] $MaxFreqCount = 0;
    [int] $NonZeros = 0;
    [bool] $IsNonZero = $false;
    [int] $ZeroGap = 0;   
    
    for([int] $i = 0; $i -lt $FreqTable.Length; $i++)
    {
        if($FreqTable[$i] -eq $MaxFreq)
        {
            $MaxFreqCount++;
        }
        if($IsNonZero -and $FreqTable[$i] -eq 0)
        {
            $ZeroGap++;
            $IsNonZero = $False;
        }        
        if($FreqTable[$i] -gt 0)
        {
            $NonZeros++;
            $IsNonZero = $True;
        }        
    }
    
    if($FreqTable[5] -eq 0)
    {
        $ZeroGap--;
    }
    return [HashTable] @{
        MaxFreq = $MaxFreq;
        MaxIndex = $MaxIndex;
        MaxValue = $MaxValue;
        MaxFreqCount = $MaxFreqCount;
        NonZeroCount = $NonZeros;
        ZeroGap = $ZeroGap;
    };
}

function Get-LogicResults([Hashtable] $Table)
{
    if($Table.ZeroGap -eq 0 -and $Table.MaxFreqCount -eq 5)
    {
        "Straight";
        return;
    }
    if($Table.NonZeroCount -eq 2 -and $Table.MaxFreq -eq 3)
    {
        "FullHouse";
        return;
    }
    if($Table.MaxFreq -eq 2)
    {
        [String]::concat($Table.MaxFreqCount, " Pair(s)");
        return;
    }
    if($Table.MaxFreq -ge 3)
    {        
        [String]::concat($Table.MaxFreq, " of a Kind");
        return;
    }
    [String]::concat($Table.MaxValue, " High");  #Always be 6
}

function Out-LogicResults([int[]] $list)
{
    return Get-LogicResults (Get-RollResults $list);
}

