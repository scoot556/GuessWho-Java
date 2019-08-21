import java.io.*;
import java.util.*;
import java.util.Map.*;

/**
 * Random guessing player.
 * This player is for task B.
 *
 * You may implement/extend other interfaces or classes, but ensure ultimately
 * that this class implements the Player interface (directly or indirectly).
 */
public class RandomGuessPlayer implements Player
{
	private HashMap<String, PlayerData> pList = new HashMap<String,PlayerData>();
	private ArrayList<String> pRemain = new ArrayList<String>();
	
	private PlayerData pSelect,gPlayer;
	private Guess gAtt;
	
    /**
     * Loads the game configuration from gameFilename, and also store the chosen
     * person.
     *
     * @param gameFilename Filename of game configuration.
     * @param chosenName Name of the chosen person for this player.
     * @throws IOException If there are IO issues with loading of gameFilename.
     *    Note you can handle IOException within the constructor and remove
     *    the "throws IOException" method specification, but make sure your
     *    implementation exits gracefully if an IOException is thrown.
     */
    public RandomGuessPlayer(String gameFilename, String chosenName)
        throws IOException
    {
    	loadAttributes(gameFilename, chosenName);
    } // end of RandomGuessPlayer()


    public Guess guess() 
    {
    	if(pRemain.size() > 1)
    	{
    		Random rnd = new Random();
    		gPlayer = pList.get(pRemain.get(rnd.nextInt(pRemain.size())));
    		gAtt = gPlayer.attRand();
    	}
    	else if(pRemain.size() == 1)
    	{
    		gAtt = new Guess(Guess.GuessType.Person, "", pRemain.get(0));
    	}
    	return gAtt;
    } // end of guess()


    public boolean answer(Guess currGuess) {

        if(currGuess.getType().equals(Guess.GuessType.Attribute))
        {
        	if(pSelect.attEqual(currGuess.getAttribute(), currGuess.getValue())) 
        	{
        		return true;
        	}
        }
        else if(currGuess.getType().equals(Guess.GuessType.Person))
        {
        	if(pSelect.getName().equals(currGuess.getValue()))
        	{
        		return true;
        	}
        }
        return false;
    } // end of answer()


	public boolean receiveAnswer(Guess currGuess, boolean answer) {

        // placeholder, replace
		if(currGuess.getType().equals(Guess.GuessType.Attribute))
		{
			for(Entry<String, PlayerData> find : pList.entrySet())
			{
				String pName = find.getKey();
				PlayerData pData = find.getValue();	
				
				if(answer && !pData.attEqual(currGuess.getAttribute(), currGuess.getValue()))
				{
					pRemain.remove(pName);
				}
				else if(!answer && pData.attEqual(currGuess.getAttribute(), currGuess.getValue()))
				{
					pRemain.remove(pName);
				}
			}
			return false;
		}
        return true;
    } // end of receiveAnswer()
	
	public void loadAttributes(String gameFilename, String chosenName) throws IOException
	{
		try {
    		BufferedReader br = new BufferedReader(new FileReader(gameFilename));
    		String curr = null;
    		String temp;
    		while((temp = br.readLine()) != null)
    		{
    			List<String> field = new ArrayList<String>(Arrays.asList(temp.trim().split(" ")));
    			field.removeAll(Arrays.asList("", null," "));
    			if(field.size() == 2 && curr != null)
    			{
    				if(pList.containsKey(curr)) 
    				{
    					PlayerData pTemp = pList.get(curr);
    					pTemp.attAdd(field.get(0), field.get(1));
    				}
    			}
    			else if(field.size() == 1)
    			{
    				if(!pList.containsKey(field.get(0)))
    				{
    					pList.put(field.get(0), new PlayerData(field.get(0)));
    					curr = field.get(0);
    					pRemain.add(field.get(0));
    				}
    			}
    		}
    		pSelect = pList.get(chosenName);
    		br.close();
    	}
    	catch(Exception e)
    	{
    		
    	}
	}

} // end of class RandomGuessPlayer
