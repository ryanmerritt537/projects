
public class SortingTests {
	public static void main(String[] args){
		int[] arr = new int[]{3,1,2,5,4,6,3};
		for(int i = 1; i < arr.length; i++){
			int j = i-1;
			while(j >= 0 && arr[j] > arr [j+1]){
				int temp = arr[j];
				arr[j] = arr[j+1];
				arr[j+1] = temp;
				j--;
			}
		}
		
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i]+ " ");
		}
	}
}
