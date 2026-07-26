package util;

import java.util.HashMap;

public class PartyHashMapImpl implements IPartyHashMap {
     HashMap hash;
     public PartyHashMapImpl() {
    	 hash=new HashMap();
     }
     public void setPartyHashMap(HashMap hash) {
    	 this.hash=hash;
     }

	 @Override
	 public HashMap getPartyHashMap() {
		// TODO Auto-generated method stub
		return hash;
	 }
}
