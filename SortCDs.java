package ch6projects;

public class SortCDs {
	public static void sortByTitle (CDCollection collection) {
		int key = 0;
		for(int i = 0; i < collection.getSize()-1; i ++){
			key = i;
			while(key >= 0 && collection.getTitle(key).compareTo(collection.getTitle(key+1)) > 0){
				collection.swap(key+1, key);
				key--;
			}
		}
	}
	public static void sortByArtist(CDCollection collection){
		int key = 0;
		for(int i = 0; i < collection.getSize()-1; i ++){
			key = i;
			while(key >= 0 && collection.getArtist(key).compareTo(collection.getArtist(key+1)) > 0){
				collection.swap(key+1, key);
				key--;
			}
		}
	}
	public static void sortByCost(CDCollection collection){
		int key = 0;
		for(int i = 0; i < collection.getSize()-1; i ++){
			key = i;
			while(key >= 0 && collection.getCost(key) > collection.getCost(key+1)){
				collection.swap(key+1, key);
				key--;
			}
		}
	}
	public static void sortByTracks(CDCollection collection){
		int key = 0;
		for(int i = 0; i < collection.getSize()-1; i ++){
			key = i;
			while(key >= 0 && collection.getTracks(key) > collection.getTracks(key+1)){
				collection.swap(key+1, key);
				key--;
			}
		}
	}
}
