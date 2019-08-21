import java.util.*;


public class AttributeData 
{
	private String attName;
	private HashMap<String, String> attVal =  new HashMap<String, String>();
	private ArrayList<String> endList;
	
	public AttributeData(String temp)
	{
		attName = temp;
	}
	
	public String getAttName()
	{
		return attName;
	}
	
	public String getAtt(String val)
	{
		return attVal.get(val);
	}
	
		
	public boolean validAtt(String val)
	{
		if(attVal.containsKey(val))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void addAttVal(String val, String val2)
	{
		if(attVal.containsKey(val))
		{
			attVal.put(val, attVal.get(val2));
		}
		else
		{
			attVal.put(val, val2);
		}
	}
	
	public ArrayList<String> getAttVal()
	{
		endList = new ArrayList<String>();
		PriorityQueue<String> endQueue = new PriorityQueue<String>(1, new Comparator<String>()
		{
			public int compare(String val1, String val2)
			{
				return attVal.get(val1).compareTo(attVal.get(val2));
			}
		});
		
		endList.addAll(endQueue);
		return new ArrayList<>();
	}
}
