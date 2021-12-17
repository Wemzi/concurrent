	import java.util.Arrays;
	import java.util.ArrayList;
	import java.util.Random;

	public class Task1 {

	private static int[] merge(int[] arr1, int[] arr2) {
	int size1 = arr1.length;
	int size2 = arr2.length;
	int i=0, j=0, k=0;
	int[] ret = new int [arr1.length+ arr2.length];
	while (j<size2 && i<size1)
	{
		if (arr1[i] < arr2[j])
		{
			ret[k++] = arr1[i++];
		}
		else if( arr1[i] >= arr2[j])
		{
			ret[k++]=arr2[j++];
		}
	}
	while(j<size2)
	{
		ret[k++] = arr2[j++];
	}
	while(i<size1)
	{
		ret[k++] = arr1[i++];
	}
	return ret;
	}
	private static int[][] slice(int[] arr, int k) {
	int arrsize =(int)Math.ceil(arr.length / (double)k) ;
	int[][] ret=new int[k][];
	int start = 0;
	if( arr.length % k == 0)
	{
		for(int idx=0; idx<k && start  < arr.length; idx++)
		{
			ret[idx] = Arrays.copyOfRange(arr,start,start+arrsize);
			start += arrsize;
		}
	}
	else
	{
		for(int idx=0; idx<k-1 && start+arrsize < arr.length; idx++)
		{
			ret[idx] = Arrays.copyOfRange(arr,start,start+arrsize);
			start += arrsize;
		}
		ret[k-1] = Arrays.copyOfRange(arr,start,arr.length);
	}

	//System.out.println(Arrays.deepToString(ret));
	return ret;
	}

	public static int[] sort(int[] array) {
	int cores = Runtime.getRuntime().availableProcessors();
	int[][] arrs = slice(array,cores);
	ArrayList<Thread> Threads = new ArrayList<>();
	for(int[] yeah : arrs)
	{
		if(yeah != null)
		{
			Thread sorter = new Thread(()-> Arrays.sort(yeah));
			sorter.start();
			Threads.add(sorter);
		}

	}
	try{
		for(Thread thread : Threads)
		{
			thread.join();
		}
	}
	catch(InterruptedException e)
	{
		System.out.println(e.toString());
	}
	int[] sorted = new int[0];
	for(int[] yeah : arrs)
	{
		if(yeah != null)
		{
			sorted = merge(sorted,yeah);
		}
	}
	return sorted;
	}

	public static void main(String[] args) {
	int[] myarr = new int[10000];
	Random rand = new Random();
	for(int idx=0; idx<myarr.length;idx++)
	{
		myarr[idx] = Math.abs(rand.nextInt())%15000;
	}
	sort(myarr);
	}
	}