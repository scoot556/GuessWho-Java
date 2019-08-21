import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class PlayerData 
{
	private String pName;
	private HashMap<String, String> attributeList = new HashMap<>();
	
	public PlayerData(String pName)
	{
		this.pName = pName;
	}
	
	public String getName()
	{
		return pName;
	}
	
	public ArrayList<String> getAttKey()
	{
		ArrayList<String> temp = new ArrayList<String>();
		
		for(Entry<String, String> eSet : attributeList.entrySet())
		{
			String key = eSet.getKey();
			String val = eSet.getValue();
			temp.add(key);
			temp.add(val);
		}
		return temp;
	}
	
	public HashMap<String, String> getAtt()
	{
		return attributeList;
	}
	
	public void attAdd(String attName, String attVal) 
	{
		attributeList.put(attName, attVal);
	}
	
	public boolean attEqual(String attName, String attVal)
	{
		if(attributeList.containsKey(attName) && attributeList.get(attName).equals(attVal))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean attValid(String attName, String attVal)
	{
		if(attributeList.containsKey(attName))
		{
			if(attributeList.get(attName).equals(attVal))
			{
				return true;
			}
		}
		return false;
	}	
	
	public Guess attRand()
	{
		Random rAtt = new Random();
		Object[] val = attributeList.keySet().toArray();
		String rKey = (String) val[rAtt.nextInt(val.length)];
		String rVal = attributeList.get(rKey);
		
		return new Guess(Guess.GuessType.Attribute,rKey,rVal);
	}
	
	
}
