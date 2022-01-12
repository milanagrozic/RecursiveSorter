import java.util.Random;

public class RecursiveSorter {
	
	private static void swap(IntegerArray numbers, int indexA, int indexB) {
		int numberA = numbers.read(indexA);
		numbers.write(indexA, numbers.read(indexB));
		numbers.write(indexB, numberA);
	}
	
	private static int partition(IntegerArray numbers, int left, int right) {
		swap(numbers, (new Random()).nextInt((right - left)) + left, right);
		
		int pivot = numbers.read(right);
		int pointerA = left - 1;
		
		for(int pointerB = left;pointerB < right; pointerB++) {
			int numberB = numbers.read(pointerB);
			
			if(numberB <= pivot) {
				pointerA++;
				
				numbers.write(pointerB, numbers.read(pointerA));
				numbers.write(pointerA, numberB);
			}
		}
		pointerA++;
		swap(numbers, pointerA, right);
		return pointerA;
	}
	
	private static void quickSort(IntegerArray numbers, int left, int right) {
		if (left < right) {
			int partitionIndex = partition(numbers, left, right);

			quickSort(numbers, left, partitionIndex - 1);
			quickSort(numbers, partitionIndex + 1, right);
		}
	}
	
	public void doQuickSort(IntegerArray numbers) {
		quickSort(numbers, 0, numbers.length() - 1);
	}
	
	private static void merge(IntegerArray array, IntegerArray left, IntegerArray right) {
		int leftLength = left.length();
		int rightLength = right.length();
		
		int leftPointer = 0;
		int rightPointer = 0;
		int pointer = 0;
		
		while(leftPointer < leftLength && rightPointer < rightLength) {
			int numA = left.read(leftPointer);
			int numB = right.read(rightPointer);
		
			if(numA <= numB) {
				array.write(pointer,  numA);
				leftPointer++;
			} else {
				array.write(pointer,  numB);
				rightPointer++;
			}
	
			pointer++;
		}
		
		while(leftPointer < leftLength) {
			array.write(pointer++,  left.read(leftPointer++));
		}
		while(rightPointer < rightLength) {
			array.write(pointer++,  right.read(rightPointer++));
		}
	}
	
	public static void mergeSort(IntegerArray array) {
		int arrayLength = array.length();
		if(arrayLength < 2) {
			return;
		}
		int center = arrayLength / 2;
		
		IntegerArray left = new IntegerArray(new int[center]);
		IntegerArray right = new IntegerArray(new int[arrayLength - center]);
		
		for(int index = 0; index < center; index++) {
			left.write(index, array.read(index));
		}
		
		for(int index = center; index < arrayLength; index++) {
			right.write(index - center, array.read(index));
		}
		
		mergeSort(left);
		mergeSort(right);
		
		merge(array, left, right);
	}
	
	public void doMergeSort(IntegerArray array) {
		mergeSort(array);
	}
}
